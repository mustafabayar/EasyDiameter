package com.easydiameter.packet.avp.derived;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.OctetStringAVP;

public class DiameterURIAVP extends OctetStringAVP {

	public DiameterURIAVP(long avpCode, byte flags, long vendorId) {
		super(avpCode, flags, vendorId);
	}

	public DiameterURIAVP(AVPDictionaryData dictData) {
		super(dictData);
	}
}
