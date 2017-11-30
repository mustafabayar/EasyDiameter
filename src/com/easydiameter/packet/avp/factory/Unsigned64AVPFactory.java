package com.easydiameter.packet.avp.factory;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.packet.avp.Unsigned64AVP;

public class Unsigned64AVPFactory extends AVPFactory {

	private static final Unsigned64AVPFactory INSTANCE = new Unsigned64AVPFactory();

	private Unsigned64AVPFactory() {
	}

	public static AVPFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public DiameterAVP createAVP(long avpCode, byte flags, long vendorId) {
		return new Unsigned64AVP(avpCode, flags, vendorId);
	}

	@Override
	public DiameterAVP createAVP(AVPDictionaryData dictData) {
		return new Unsigned64AVP(dictData);
	}
}
