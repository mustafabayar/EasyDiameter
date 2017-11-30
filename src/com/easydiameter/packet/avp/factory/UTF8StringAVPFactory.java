package com.easydiameter.packet.avp.factory;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.packet.avp.derived.UTF8StringAVP;

public class UTF8StringAVPFactory extends AVPFactory {

	private static final UTF8StringAVPFactory INSTANCE = new UTF8StringAVPFactory();

	private UTF8StringAVPFactory() {
	}

	public static AVPFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public DiameterAVP createAVP(long avpCode, byte flags, long vendorId) {
		return new UTF8StringAVP(avpCode, flags, vendorId);
	}

	@Override
	public DiameterAVP createAVP(AVPDictionaryData dictData) {
		return new UTF8StringAVP(dictData);
	}
}
