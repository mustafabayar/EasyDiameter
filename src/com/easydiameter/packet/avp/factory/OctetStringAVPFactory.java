package com.easydiameter.packet.avp.factory;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.packet.avp.OctetStringAVP;

public class OctetStringAVPFactory extends AVPFactory {

   private static final OctetStringAVPFactory INSTANCE = new OctetStringAVPFactory();

   private OctetStringAVPFactory() {
   }

   public static AVPFactory getInstance() {
      return INSTANCE;
   }

   @Override
   public DiameterAVP createAVP(long avpCode, byte flags, long vendorId) {
      return new OctetStringAVP(avpCode, flags, vendorId);
   }

   @Override
   public DiameterAVP createAVP(AVPDictionaryData dictData) {
      return new OctetStringAVP(dictData);
   }
}
