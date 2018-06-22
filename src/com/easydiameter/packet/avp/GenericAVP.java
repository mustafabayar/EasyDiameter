package com.easydiameter.packet.avp;

import java.nio.ByteBuffer;

import com.easydiameter.dictionary.avp.AVPDictionaryData;

public class GenericAVP extends DiameterAVP {

   public GenericAVP(long avpCode, byte flags, long vendorId) {
      super(avpCode, flags, vendorId);
      this.name = "Unknown";
   }

   public GenericAVP(AVPDictionaryData dictData) {
      super(dictData);
      this.name = "Unknown";
   }

   public void encodeData(ByteBuffer buffer) {
      buffer.put(byteData);
   }

   public void decodeData(ByteBuffer buffer, int length) {
      byteData = new byte[length];
      buffer.get(byteData, 0, length);
      addDataLength(length);
   }

   @Override
   public void setData(String data) {
      byteData = data.getBytes();
      addDataLength(data.length());
   }

   @Override
   public void printData(StringBuilder sb) {
      sb.append(byteData);
   }

}
