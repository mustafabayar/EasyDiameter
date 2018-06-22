package com.easydiameter.packet.avp.derived;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.OctetStringAVP;

public class QoSFilterRuleAVP extends OctetStringAVP {

   public QoSFilterRuleAVP(long avpCode, byte flags, long vendorId) {
      super(avpCode, flags, vendorId);
   }

   public QoSFilterRuleAVP(AVPDictionaryData dictData) {
      super(dictData);
   }

}
