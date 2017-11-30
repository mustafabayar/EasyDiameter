package com.easydiameter.packet.avp.derived;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.OctetStringAVP;

public class IPFilterRuleAVP extends OctetStringAVP {

	public IPFilterRuleAVP(long avpCode, byte flags, long vendorId) {
		super(avpCode, flags, vendorId);
	}
	
	public IPFilterRuleAVP(AVPDictionaryData dictData) {
		super(dictData);
	}

}
