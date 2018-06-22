package com.easydiameter.packet.avp.factory;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.packet.avp.GroupedAVP;

public class GroupedAVPFactory extends AVPFactory {

   private static final GroupedAVPFactory INSTANCE = new GroupedAVPFactory();

   private GroupedAVPFactory() {
   }

   public static AVPFactory getInstance() {
      return INSTANCE;
   }

   @Override
   public DiameterAVP createAVP(long avpCode, byte flags, long vendorId) {
      return new GroupedAVP(avpCode, flags, vendorId);
   }

   @Override
   public DiameterAVP createAVP(AVPDictionaryData dictData) {
      return new GroupedAVP(dictData);
   }
}
