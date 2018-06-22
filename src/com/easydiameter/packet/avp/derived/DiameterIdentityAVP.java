package com.easydiameter.packet.avp.derived;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.OctetStringAVP;

public class DiameterIdentityAVP extends OctetStringAVP {

   public DiameterIdentityAVP(long avpCode, byte flags, long vendorId) {
      super(avpCode, flags, vendorId);
   }

   public DiameterIdentityAVP(AVPDictionaryData dictData) {
      super(dictData);
   }

}
