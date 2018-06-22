package com.easydiameter.packet.avp.factory;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.packet.avp.Unsigned32AVP;

public class Unsigned32AVPFactory extends AVPFactory {

   private static final Unsigned32AVPFactory INSTANCE = new Unsigned32AVPFactory();

   private Unsigned32AVPFactory() {
   }

   public static AVPFactory getInstance() {
      return INSTANCE;
   }

   @Override
   public DiameterAVP createAVP(long avpCode, byte flags, long vendorId) {
      return new Unsigned32AVP(avpCode, flags, vendorId);
   }

   @Override
   public DiameterAVP createAVP(AVPDictionaryData dictData) {
      return new Unsigned32AVP(dictData);
   }
}
