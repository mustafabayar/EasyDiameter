package com.easydiameter.application;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;

public class Acceptor implements Runnable {

   private static Logger LOGGER      = Logger.getLogger(Acceptor.class);

   DiameterStack         stack;

   /* exit after a connection establishment or not */
   boolean               exitOnComplete;

   /* address and port to listen */
   String                ipAddrStr;
   int                   listenPort;

   AtomicBoolean         stopWorking = new AtomicBoolean(false);

   ServerSocket          serverSocket;
   boolean               isSecure    = false;

   public Acceptor(String localAddressStr, int listenPort, boolean exitAfter, DiameterStack diameterStack) {
      this.ipAddrStr = localAddressStr;
      this.listenPort = listenPort;
      this.exitOnComplete = exitAfter;
      this.stack = diameterStack;
   }

   /* Binds the socket and returns actual local port */
   public int bind() throws IOException {
      InetAddress inetaddr = InetAddress.getByName(this.ipAddrStr);

      this.serverSocket = new ServerSocket(this.listenPort, 0, inetaddr);

      return this.serverSocket.getLocalPort();
   }

   @Override
   public void run() {
      while (!this.stopWorking.get()) {
         try {
            Socket clientSocket = null;
            clientSocket = this.serverSocket.accept();
            stack.connectionAccepted(clientSocket);
         } catch (IOException e) {
            if (this.stopWorking.get()) {
               LOGGER.info("Acceptor Stopped.");
               return;
            }
            throw new RuntimeException("Error accepting client connection", e);
         }
      }
      LOGGER.info("Acceptor Stopped.");
      try {
         this.serverSocket.close();
      } catch (IOException e) {
         throw new RuntimeException("Error closing acceptor socket", e);
      }
   }

   public synchronized void stop() {
      this.stopWorking.set(true);
      if (this.serverSocket != null) {
         try {
            this.serverSocket.close();
         } catch (IOException e) {
            LOGGER.error("IOException on Acceptor.stop..." + e);
         }
      }
   }

}
