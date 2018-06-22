package com.easydiameter.packet.avp;

import java.nio.ByteBuffer;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.util.BufferUtilities;

public class Unsigned32AVP extends DiameterAVP {

   private long data;

   public Unsigned32AVP(long avpCode, byte flags, long vendorId) {
      super(avpCode, flags, vendorId);
   }

   public Unsigned32AVP(AVPDictionaryData dictData) {
      super(dictData);
   }

   public void encodeData(ByteBuffer buffer) {
      buffer.putInt((int) data);
   }

   public void decodeData(ByteBuffer buffer, int length) {
      data = BufferUtilities.get4BytesAsUnsigned32(buffer);
      addDataLength(length);
   }

   public long getData() {
      return data;
   }

   public void setData(long data) {
      this.data = data;
      addDataLength(4);
   }

   @Override
   public void setData(String data) {
      this.data = Long.parseLong(data);
      addDataLength(4);
   }

   @Override
   public void printData(StringBuilder sb) {
      sb.append(data);
   }

}
