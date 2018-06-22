package com.easydiameter.packet.avp.factory;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.packet.avp.derived.EnumeratedAVP;

public class EnumeratedAVPFactory extends AVPFactory {

   private static final EnumeratedAVPFactory INSTANCE = new EnumeratedAVPFactory();

   private EnumeratedAVPFactory() {
   }

   public static AVPFactory getInstance() {
      return INSTANCE;
   }

   @Override
   public DiameterAVP createAVP(long avpCode, byte flags, long vendorId) {
      return new EnumeratedAVP(avpCode, flags, vendorId);
   }

   @Override
   public DiameterAVP createAVP(AVPDictionaryData dictData) {
      return new EnumeratedAVP(dictData);
   }
}
