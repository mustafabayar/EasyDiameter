package com.easydiameter.packet.avp;

import java.nio.ByteBuffer;

import com.easydiameter.dictionary.avp.AVPDictionary;
import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.exception.DiameterParseException;
import com.easydiameter.util.BufferUtilities;
import com.easydiameter.util.ProtocolDefinitions;
import com.easydiameter.util.ProtocolUtilities;

public abstract class DiameterAVP implements ProtocolDefinitions {

   /* Header part of the AVP */
   protected long   code;
   protected byte   flags;
   protected int    avpLength;
   protected long   vendorId;

   protected int    dataLength;

   protected byte[] byteData;

   protected String name;

   public DiameterAVP(long code, byte flags, long vendorId) {
      this.code = code;
      this.flags = flags;
      this.vendorId = vendorId;
      this.avpLength = ProtocolUtilities.findAVPHeaderLength(flags);
   }

   public DiameterAVP(AVPDictionaryData dictData) {
      this.code = dictData.getCode();
      this.flags = dictData.getFlags();
      this.vendorId = dictData.getVendorId();
      this.avpLength = ProtocolUtilities.findAVPHeaderLength(flags);
   }

   public abstract void setData(String data);

   public void setData(byte[] data) {
      this.byteData = data;
      this.dataLength = data.length;
      this.avpLength += this.dataLength;
   }

   public void encode(ByteBuffer buffer) {
      BufferUtilities.set4BytesToBuffer(buffer, code);
      buffer.put(flags);
      BufferUtilities.set3BytesToBuffer(buffer, avpLength);
      if ((flags & AVP_MASK_BIT_V) != 0) {
         BufferUtilities.set4BytesToBuffer(buffer, vendorId);
      }
      encodeData(buffer);
      buffer.position(buffer.position() + BufferUtilities.calculatePadding(avpLength));
   }

   public abstract void encodeData(ByteBuffer buffer);

   public abstract void decodeData(ByteBuffer buffer, int length) throws DiameterParseException;

   public boolean isVendorSpecific() {
      return (flags & AVP_MASK_BIT_V) != 0;
   }

   public boolean isMandatory() {
      return (flags & AVP_MASK_BIT_M) != 0;
   }

   public boolean isPrivate() {
      return (flags & AVP_MASK_BIT_P) != 0;
   }

   /*
    * Normally this bit will be set according to vendorId value but here I allow
    * user to be able to change it for testing purposes
    */
   public DiameterAVP setVBit(boolean isVendor) {
      if (isVendor) {
         flags |= AVP_MASK_BIT_V;
      } else {
         flags &= ~AVP_MASK_BIT_V;
      }
      return this;
   }

   public DiameterAVP setMBit(boolean isMandatory) {
      if (isMandatory) {
         flags |= AVP_MASK_BIT_M;
      } else {
         flags &= ~AVP_MASK_BIT_M;
      }
      return this;
   }

   public DiameterAVP setPBit(boolean isPrivate) {
      if (isPrivate) {
         flags |= AVP_MASK_BIT_P;
      } else {
         flags &= ~AVP_MASK_BIT_P;
      }
      return this;
   }

   public void addDataLength(int length) {
      this.dataLength = length;
      this.avpLength += this.dataLength;
   }

   public int getAvpLength() {
      return avpLength;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void printContent(StringBuilder sb) {

      if (name == null) {
         AVPDictionaryData dictData = AVPDictionary.getDictionaryData(code, vendorId);
         name = dictData.getName();
      }

      sb.append(name + " AVP" + "(" + code + "): ");
      this.printData(sb);
   }

   public abstract void printData(StringBuilder sb);

}
