package com.easydiameter.packet.avp.factory;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.packet.avp.derived.QoSFilterRuleAVP;

public class QoSFilterRuleAVPFactory extends AVPFactory {

   private static final QoSFilterRuleAVPFactory INSTANCE = new QoSFilterRuleAVPFactory();

   private QoSFilterRuleAVPFactory() {
   }

   public static AVPFactory getInstance() {
      return INSTANCE;
   }

   @Override
   public DiameterAVP createAVP(long avpCode, byte flags, long vendorId) {
      return new QoSFilterRuleAVP(avpCode, flags, vendorId);
   }

   @Override
   public DiameterAVP createAVP(AVPDictionaryData dictData) {
      return new QoSFilterRuleAVP(dictData);
   }
}
