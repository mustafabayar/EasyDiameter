package com.easydiameter.application;

import com.easydiameter.packet.message.DiameterMessage;

public interface DiameterApplication {

   public void receiveMessage(DiameterStack stack, DiameterMessage message);

   public void onConnectionSuccess(String localAddressStr, int localPort, String hostAddress, int remotePort);

   public void onConnectionFail(String hostAddress, int remotePort);

   public void onSendMessage();

   public void onDisconnect(int i);

}
