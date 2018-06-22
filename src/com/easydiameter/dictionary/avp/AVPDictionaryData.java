package com.easydiameter.dictionary.avp;

import com.easydiameter.packet.avp.factory.AVPFactory;

public class AVPDictionaryData {
   private String     name;
   private long       code;
   private byte       flags;
   private long       vendorId;
   private int        dataType;
   private AVPFactory factory;

   public AVPDictionaryData(String name, long code, byte flags, long vendorId, int dataType) {
      this.name = name;
      this.code = code;
      this.flags = flags;
      this.vendorId = vendorId;
      this.dataType = dataType;
      this.setFactory(findFactory());
   }

   private AVPFactory findFactory() {
      return AVPFactory.getAVPFactory(this.dataType);
   }

   public String getName() {
      return name;
   }

   public long getCode() {
      return code;
   }

   public byte getFlags() {
      return flags;
   }

   public long getVendorId() {
      return vendorId;
   }

   public int getDataType() {
      return dataType;
   }

   public AVPFactory getFactory() {
      return factory;
   }

   public void setFactory(AVPFactory factory) {
      this.factory = factory;
   }

}
