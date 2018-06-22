package com.easydiameter.packet.avp.factory;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.packet.avp.derived.DiameterIdentityAVP;

public class DiameterIdentityAVPFactory extends AVPFactory {

   private static final DiameterIdentityAVPFactory INSTANCE = new DiameterIdentityAVPFactory();

   private DiameterIdentityAVPFactory() {
   }

   public static AVPFactory getInstance() {
      return INSTANCE;
   }

   @Override
   public DiameterAVP createAVP(long avpCode, byte flags, long vendorId) {
      return new DiameterIdentityAVP(avpCode, flags, vendorId);
   }

   @Override
   public DiameterAVP createAVP(AVPDictionaryData dictData) {
      return new DiameterIdentityAVP(dictData);
   }
}
