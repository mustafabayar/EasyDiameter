package com.easydiameter.packet.avp.factory;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.packet.avp.Integer32AVP;

public class Integer32AVPFactory extends AVPFactory {

	private static final Integer32AVPFactory INSTANCE = new Integer32AVPFactory();

	private Integer32AVPFactory() {
	}

	public static AVPFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public DiameterAVP createAVP(long avpCode, byte flags, long vendorId) {
		return new Integer32AVP(avpCode, flags, vendorId);
	}

	@Override
	public DiameterAVP createAVP(AVPDictionaryData dictData) {
		return new Integer32AVP(dictData);
	}
}
