package com.easydiameter.packet.avp.factory;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.packet.avp.derived.DiameterURIAVP;

public class DiameterURIAVPFactory extends AVPFactory {

   private static final DiameterURIAVPFactory INSTANCE = new DiameterURIAVPFactory();

   private DiameterURIAVPFactory() {
   }

   public static AVPFactory getInstance() {
      return INSTANCE;
   }

   @Override
   public DiameterAVP createAVP(long avpCode, byte flags, long vendorId) {
      return new DiameterURIAVP(avpCode, flags, vendorId);
   }

   @Override
   public DiameterAVP createAVP(AVPDictionaryData dictData) {
      return new DiameterURIAVP(dictData);
   }
}
