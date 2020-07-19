package com.easydiameter.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Receiver implements Runnable {

   private static Logger LOGGER      = LoggerFactory.getLogger(Receiver.class);

   static final int      buffer_size = 4096;

   Socket                sock;
   DiameterStack         stack;

   AtomicBoolean         stopWorking = new AtomicBoolean(false);

   InputStream           in;

   public Receiver(Socket connectionSocket, DiameterStack diameterStack) {
      this.sock = connectionSocket;
      this.stack = diameterStack;
   }

   @Override
   public void run() {
      byte[] buff = new byte[buffer_size];
      int startOff = 0;
      try {
         this.in = sock.getInputStream();
         while (!this.stopWorking.get()) {
            // LOGGER.debug("first--Receiver starting read with startOff = " + startOff);
            int len = this.in.read(buff, startOff, (buffer_size - startOff));

            if (len < 0) {
               break;
            }

            byte[] newbuff = stack.messageReceived(buff, len + startOff);

            /*
             * since TCP is a stream-based protocol no message boundaries are
             * followed/provided. Therefore, after the processing there either - message is
             * not completed yet with the received part, or - there are byte more than a
             * message (that is related to the another message) In both cases, the message
             * handler gives back all/some part of the message. We should put it into the
             * buffer back to wait it to be completed. Note that, message handler is
             * responsible for removing the bytes that are not related to a message that is
             * expected (such as an unknown protocol message)
             */
            if (newbuff == null) {
               /* assume all part of buffer processed */
               startOff = 0;
               // LOGGER.debug("Receiver starting read with startOff = " + startOff);
            } else {
               /*
                * there is a remaining part, put it into buffer and wait for remaining part
                */
               System.arraycopy(newbuff, 0, buff, 0, newbuff.length);
               startOff = newbuff.length + 1;
               // LOGGER.debug("Receiver starting read with startOff = " + startOff);
            }
         }
      } catch (IOException e) {
         if (stopWorking.get() == false) {
            LOGGER.error("Exception on receiver: " + e + sock.toString());
            if (e instanceof SocketException) {
               stack.connectionDisconnected(sock);
            }
         }
      } catch (StringIndexOutOfBoundsException e) {
         LOGGER.error("Exception on receiver");
         e.printStackTrace();
      }
      try {
         this.sock.close();
      } catch (IOException e) {
         throw new RuntimeException("Error closing receiver socket", e);
      }
   }

   public synchronized void stop() {
      this.stopWorking.set(true);
      LOGGER.debug("Stoping the receiver ... " + this.stopWorking.get());
      LOGGER.debug("Stoping the receiver ... " + this.sock.toString());
      if ((this.sock != null) && (!this.sock.isClosed())) {
         try {
            this.sock.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

}
