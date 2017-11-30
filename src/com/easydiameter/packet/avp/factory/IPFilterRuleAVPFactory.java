package com.easydiameter.packet.avp.factory;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.packet.avp.derived.IPFilterRuleAVP;

public class IPFilterRuleAVPFactory extends AVPFactory {

	private static final IPFilterRuleAVPFactory INSTANCE = new IPFilterRuleAVPFactory();

	private IPFilterRuleAVPFactory() {
	}

	public static AVPFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public DiameterAVP createAVP(long avpCode, byte flags, long vendorId) {
		return new IPFilterRuleAVP(avpCode, flags, vendorId);
	}

	@Override
	public DiameterAVP createAVP(AVPDictionaryData dictData) {
		return new IPFilterRuleAVP(dictData);
	}
}
