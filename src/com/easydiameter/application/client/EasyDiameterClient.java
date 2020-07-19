package com.easydiameter.application.client;

import com.easydiameter.application.DiameterApplication;
import com.easydiameter.application.DiameterStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class EasyDiameterClient {

   private static Logger LOGGER = LoggerFactory.getLogger(EasyDiameterClient.class);

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
