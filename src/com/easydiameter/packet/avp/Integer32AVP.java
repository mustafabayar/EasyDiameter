package com.easydiameter.packet.avp;

import java.nio.ByteBuffer;

import com.easydiameter.dictionary.avp.AVPDictionaryData;

public class Integer32AVP extends DiameterAVP {

   private int data;

   public Integer32AVP(long avpCode, byte flags, long vendorId) {
      super(avpCode, flags, vendorId);
   }

   public Integer32AVP(AVPDictionaryData dictData) {
      super(dictData);
   }

   @Override
   public void encodeData(ByteBuffer buffer) {
      buffer.putInt(data);
   }

   @Override
   public void decodeData(ByteBuffer buffer, int length) {
      data = buffer.getInt();
      addDataLength(length);
   }

   public int getData() {
      return data;
   }

   public void setData(int data) {
      this.data = data;
      addDataLength(4);
   }

   @Override
   public void setData(String data) {
      this.data = Integer.parseInt(data);
      addDataLength(4);
   }

   @Override
   public void printData(StringBuilder sb) {
      sb.append(data);
   }

}
