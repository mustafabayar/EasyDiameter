package com.easydiameter.packet.avp.factory;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.packet.avp.derived.TimeAVP;

public class TimeAVPFactory extends AVPFactory {

   private static final TimeAVPFactory INSTANCE = new TimeAVPFactory();

   private TimeAVPFactory() {
   }

   public static AVPFactory getInstance() {
      return INSTANCE;
   }

   @Override
   public DiameterAVP createAVP(long avpCode, byte flags, long vendorId) {
      return new TimeAVP(avpCode, flags, vendorId);
   }

   @Override
   public DiameterAVP createAVP(AVPDictionaryData dictData) {
      return new TimeAVP(dictData);
   }
}
