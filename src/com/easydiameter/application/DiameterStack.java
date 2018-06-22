package com.easydiameter.application;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.easydiameter.exception.DiameterParseException;
import com.easydiameter.packet.message.DiameterMessage;
import com.easydiameter.util.BufferUtilities;
import com.easydiameter.util.ProtocolDefinitions;
import com.easydiameter.util.ProtocolUtilities;

public class DiameterStack {

   /* return codes from the stack */
   public static final int       RC_SUCCESS         = 0;
   public static final int       RC_CONNECT_TIMEOUT = 1;
   public static final int       RC_UNKNOWN_HOST    = 2;
   public static final int       RC_IO_EXCEPTION    = 3;

   /* stack working mode */
   public static final int       WM_NONE            = 0;
   public static final int       WM_SERVER          = 1;
   public static final int       WM_CLIENT          = 2;

   private static Logger         LOGGER             = Logger.getLogger(DiameterStack.class);

   protected int                 connectTimeout     = 2000;
   protected int                 workingMode        = WM_NONE;

   /* local IP */
   protected String              localAddressStr;
   protected int                 listenPort;

   protected Acceptor            acceptor;
   protected Receiver            receiver;

   /* after connection establishment */
   protected Socket              connectionSocket;
   protected int                 localPort;
   protected int                 remotePort;
   protected InetAddress         localAddress;
   protected InetAddress         remoteAddress;
   protected DiameterApplication application;

   public DiameterStack(DiameterApplication application, String ipAddress, int listenPort) {
      this.application = application;
      this.localAddressStr = ipAddress;
      this.listenPort = listenPort;
   }

   public DiameterStack(DiameterApplication application) {
      this.application = application;
   }

   public void setConnectTimeout(int timeout) {
      this.connectTimeout = timeout;
   }

   public int getConnectTimeout() {
      return this.connectTimeout;
   }

   public void shutdown() {
      if (this.acceptor != null) {
         this.acceptor.stop();
      }
      if (this.receiver != null) {
         this.receiver.stop();
      }
   }

   public void startWithListening() throws IOException {
      this.acceptor = new Acceptor(this.localAddressStr, this.listenPort, true, this);
      this.localPort = this.acceptor.bind();
      this.workingMode = WM_SERVER;
      new Thread(this.acceptor, "Acceptor").start();
   }

   /* Accepts remote-address and port Return 0 if success Return 0 if success */
   public int startWithAttempt(String remoteAddress, int remotePort, String localAddress, int localPort) {
      int result = RC_SUCCESS;
      long start = 0;
      try {
         this.remoteAddress = InetAddress.getByName(remoteAddress);
         this.remotePort = remotePort;
         start = System.currentTimeMillis();

         SocketAddress sockaddr = new InetSocketAddress(this.remoteAddress, this.remotePort);
         SocketAddress sockaddr2 = new InetSocketAddress(localAddress, localPort);

         this.connectionSocket = new Socket();
         if (localAddress != null && localPort != 0) {
            this.connectionSocket.bind(sockaddr2);
         }
         this.connectionSocket.connect(sockaddr, this.connectTimeout);

         this.workingMode = WM_CLIENT;

         this.localAddress = this.connectionSocket.getLocalAddress();
         this.localPort = this.connectionSocket.getLocalPort();

         LOGGER.info("Connection is attempted successfuly: Local Address = " + this.localAddress.getHostAddress() + ":" + this.localPort
               + " - " + "Remote Address = " + this.remoteAddress.getHostAddress() + ":" + this.remotePort);
         if (this.application != null) {
            this.application.onConnectionSuccess(this.localAddressStr, this.localPort, this.remoteAddress.getHostAddress(),
                  this.remotePort);
         }
         this.receiver = new Receiver(this.connectionSocket, this);
         new Thread(this.receiver, ("Receiver-" + ProtocolUtilities.receiverCounter.getAndIncrement())).start();
      } catch (SocketTimeoutException sot) {
         LOGGER.error("ConnectTimeout: Time = " + (System.currentTimeMillis() - start));
         result = RC_CONNECT_TIMEOUT;
      } catch (UnknownHostException e) {
         if (this.application != null) {
            this.application.onConnectionFail(this.remoteAddress.getHostAddress(), this.remotePort);
         }
         LOGGER.error("Unknown-Host --> " + e);
         result = RC_UNKNOWN_HOST;
      } catch (IOException e) {
         if (this.application != null) {
            this.application.onConnectionFail(this.remoteAddress.getHostAddress(), this.remotePort);
         }
         LOGGER.error("IO Problem --> " + e);
         result = RC_IO_EXCEPTION;
      }
      return result;
   }

   /*
    * Called from application to send message through the established
    * connection/socket established connection/socket
    */
   public int sendMessage(byte[] msg, int len) {
      int result = 0;

      if (this.connectionSocket == null) {
         result = startWithAttempt(this.remoteAddress.getHostAddress(), this.remotePort, null, 0);
         if (result != 0) {
            LOGGER.error("Cannot connect to remote with address = " + this.remoteAddress.getHostAddress() + ":" + this.remotePort);
         }
      }
      try {
         OutputStream output = this.connectionSocket.getOutputStream();
         output.write(msg);
         /* TODO: Call back onMessageSend from owner/application */
         if (this.application != null) {
            this.application.onSendMessage();
         }
      } catch (IOException e) {
         LOGGER.error("IO Problem: " + e);
      }
      return result;
   }

   public int sendMessage(byte[] msg) {
      return sendMessage(msg, msg.length);
   }

   /*
    * Callbacks from Acceptor and Receiver
    */

   /* called from Acceptor in the case of a connection accepted */
   public void connectionAccepted(Socket sock) {
      this.connectionSocket = sock;
      this.localPort = sock.getLocalPort();
      this.remotePort = sock.getPort();
      this.localAddress = sock.getLocalAddress();
      this.remoteAddress = sock.getInetAddress();

      LOGGER.debug("Connection is accepted: Local Address = " + this.localAddress.getHostAddress() + ":" + this.localPort + " | "
            + "Remote Address = " + this.remoteAddress.getHostAddress() + ":" + this.remotePort);
      if (this.application != null) {
         this.application.onConnectionSuccess(this.localAddressStr, this.listenPort, this.remoteAddress.getHostAddress(), this.remotePort);
      }
      this.receiver = new Receiver(this.connectionSocket, this);
      new Thread(this.receiver, "Receiver").start();
   }

   /* called from receiver */
   public byte[] messageReceived(byte[] buffer, int length) {
      byte[] rawmsg = buffer;
      byte[] remaining = null;
      int rawLength = length;

      StringBuilder sb = new StringBuilder();
      BufferUtilities.printMessageBuffer(sb, rawmsg, 0, rawLength);
      LOGGER.debug("Received DIAMETER RAW message --> \n" + sb.toString());

      while (true) {

         // Message Checks

         if (rawmsg[0] != 1) {
            LOGGER.warn("Unexpected version --> " + rawmsg[0]);
            byte[] rem = new byte[rawmsg.length - 1];
            System.arraycopy(rawmsg, 1, rem, 0, rawmsg.length - 1);
            return rem;
         }

         if (rawLength < ProtocolDefinitions.DIAMETER_MSG_HDR_LEN) {
            /* no enough bytes even for header */
            remaining = rawmsg;
            break;
         }

         int msgLength = BufferUtilities.get3Bytes(rawmsg, 1); // First index is Diameter Version
         if (rawLength < msgLength) {
            /* no enough bytes for message */
            remaining = rawmsg;
            break;
         }

         int rl = rawLength - msgLength;
         if (rl > 0) {
            /*
             * we have some additional bytes which are belong to another message. Keep them
             * in remaining to retry in next loop
             */
            remaining = new byte[rl];
            System.arraycopy(rawmsg, msgLength, remaining, 0, rl);
            LOGGER.debug("There are remaining bytes in Diameter messageReceived with length = " + remaining.length);
         }

         DiameterMessage message = null;

         try {
            message = DiameterMessage.decodePacket(rawmsg);
         } catch (DiameterParseException e) {
            e.printStackTrace();
         }

         if (message != null) {
            // Pass the message to application level
            application.receiveMessage(this, message);
         } else {
            StringBuilder sb1 = new StringBuilder();
            BufferUtilities.printMessageBuffer(sb1, rawmsg, 0, rawLength);
            LOGGER.warn("Cannot produce a Diameter Message from received bytes ===> \n" + sb1.toString());
         }

         if (remaining == null) {
            // LOGGER.debug("Breaking from Diameter messageReceived since there is no
            // remaining.");
            break;
         } else {
            /* continue with the remaining bytes */
            rawmsg = remaining;
            rawLength = rawmsg.length;
            remaining = null;
            LOGGER.debug("Continue with remaining bytes with length = " + rawLength);
         }
      }
      return remaining;
   }

   /* called from receiver */
   public void connectionDisconnected(Socket sock) {
      LOGGER.info("Connection disconnected");
      if (this.application != null) {
         this.application.onDisconnect(0);
      }
   }

   public void setRemoteIp(String remIP) {
      try {
         this.remoteAddress = InetAddress.getByName(remIP);
      } catch (UnknownHostException e) {
         LOGGER.error("Unknown host problem: " + e);
      }
   }

   public String getRemoteIP() {
      return this.remoteAddress.getHostAddress();
   }

   public void setRemotePort(int port) {
      this.remotePort = port;
   }

   public int getRemotePort() {
      return this.remotePort;
   }

   public int getLocalPort() {
      return localPort;
   }

   public Socket getConnectionSocket() {
      return this.connectionSocket;
   }

   public boolean isConnected() {
      return (this.connectionSocket != null);
   }

   public void setReceiver(Receiver receiver) {
      this.receiver = receiver;
   }

   public int getWorkingMode() {
      return this.workingMode;
   }

}
