package com.easydiameter.application.server;

import com.easydiameter.application.DiameterApplication;
import com.easydiameter.application.DiameterStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class EasyDiameterServer {

   private static Logger LOGGER = LoggerFactory.getLogger(EasyDiameterServer.class);

   DiameterApplication   application;
   DiameterStack         stack;

   private String        localIP;
   private int           localPort;

   private boolean       sessionStarted;

   public EasyDiameterServer(DiameterApplication application, String ipAddress, int port) {
      this.stack = new DiameterStack(application, ipAddress, port);
      this.application = application;
      this.localIP = ipAddress;
      this.localPort = port;
   }

   public void start() {
      if (!this.sessionStarted) {
         try {
            LOGGER.debug("Starting Server.");
            this.stack.startWithListening();
         } catch (IOException e) {
            LOGGER.error(e.getMessage());
         }
         sessionStarted = true;
      } else {
         LOGGER.warn("Server already started");
      }
   }

   public void destroy() {
      LOGGER.debug("Server Shutdown");
      if (this.stack != null) {
         this.stack.shutdown();
      }
   }

   public String getLocalIP() {
      return localIP;
   }

   public void setLocalIP(String localIP) {
      this.localIP = localIP;
   }

   public int getLocalPort() {
      return localPort;
   }

   public void setLocalPort(int localPort) {
      this.localPort = localPort;
   }

}
