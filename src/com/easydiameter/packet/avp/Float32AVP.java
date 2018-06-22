package com.easydiameter.packet.avp;

import java.nio.ByteBuffer;

import com.easydiameter.dictionary.avp.AVPDictionaryData;

public class Float32AVP extends DiameterAVP {

   private float data;

   public Float32AVP(long avpCode, byte flags, long vendorId) {
      super(avpCode, flags, vendorId);
   }

   public Float32AVP(AVPDictionaryData dictData) {
      super(dictData);
   }

   @Override
   public void encodeData(ByteBuffer buffer) {
      buffer.putFloat(data);
   }

   @Override
   public void decodeData(ByteBuffer buffer, int length) {
      data = buffer.getFloat();
      addDataLength(length);
   }

   public float getData() {
      return data;
   }

   public void setData(float data) {
      this.data = data;
      addDataLength(4);
   }

   @Override
   public void setData(String data) {
      this.data = Float.parseFloat(data);
      addDataLength(4);
   }

   @Override
   public void printData(StringBuilder sb) {
      sb.append(data);
   }

}
