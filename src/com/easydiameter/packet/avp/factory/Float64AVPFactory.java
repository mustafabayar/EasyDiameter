package com.easydiameter.packet.avp.factory;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.packet.avp.Float64AVP;

public class Float64AVPFactory extends AVPFactory {

   private static final Float64AVPFactory INSTANCE = new Float64AVPFactory();

   private Float64AVPFactory() {
   }

   public static AVPFactory getInstance() {
      return INSTANCE;
   }

   @Override
   public DiameterAVP createAVP(long avpCode, byte flags, long vendorId) {
      return new Float64AVP(avpCode, flags, vendorId);
   }

   @Override
   public DiameterAVP createAVP(AVPDictionaryData dictData) {
      return new Float64AVP(dictData);
   }
}
