package com.easydiameter.packet.avp.derived;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.Integer32AVP;

public class EnumeratedAVP extends Integer32AVP {

   public EnumeratedAVP(long avpCode, byte flags, long vendorId) {
      super(avpCode, flags, vendorId);
   }

   public EnumeratedAVP(AVPDictionaryData dictData) {
      super(dictData);
   }

}
