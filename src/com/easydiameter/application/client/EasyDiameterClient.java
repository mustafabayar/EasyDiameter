package com.easydiameter.application.client;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.easydiameter.application.DiameterApplication;
import com.easydiameter.application.DiameterStack;

public class EasyDiameterClient {

   private static Logger LOGGER = Logger.getLogger(EasyDiameterClient.class);

   DiameterApplication   application;
   private DiameterStack stack;

   private String        remoteIP;
   private int           remotePort;
   private String        localIp;
   private int           localPort;

   public EasyDiameterClient(DiameterApplication application, String remoteIp, int remotePort) {
      this.stack = new DiameterStack(application);
      this.remoteIP = remoteIp;
      this.remotePort = remotePort;
   }

   public void bindToLocalAddress(String localIp, int localPort) {
      this.localIp = localIp;
      this.localPort = localPort;
   }

   public void send(byte[] message) throws IOException {
      LOGGER.info("Sending Diameter Message");
      if (!this.stack.isConnected()) {
         this.stack.startWithAttempt(this.remoteIP, this.remotePort, this.localIp, this.localPort);
      }
      this.stack.sendMessage(message, message.length);
   }

   public void destroy() {
      LOGGER.debug("Client Shutdown");
      if (this.stack != null) {
         this.stack.shutdown();
      }
   }

}
