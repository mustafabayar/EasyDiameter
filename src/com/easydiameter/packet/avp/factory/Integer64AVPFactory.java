package com.easydiameter.packet.avp.factory;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.packet.avp.Integer64AVP;

public class Integer64AVPFactory extends AVPFactory {

	private static final Integer64AVPFactory INSTANCE = new Integer64AVPFactory();

	private Integer64AVPFactory() {
	}

	public static AVPFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public DiameterAVP createAVP(long avpCode, byte flags, long vendorId) {
		return new Integer64AVP(avpCode, flags, vendorId);
	}

	@Override
	public DiameterAVP createAVP(AVPDictionaryData dictData) {
		return new Integer64AVP(dictData);
	}
}
