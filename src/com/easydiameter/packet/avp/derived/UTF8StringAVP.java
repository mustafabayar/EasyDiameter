package com.easydiameter.packet.avp.derived;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.OctetStringAVP;

public class UTF8StringAVP extends OctetStringAVP {

	public UTF8StringAVP(long avpCode, byte flags, long vendorId) {
		super(avpCode, flags, vendorId);
	}
	
	public UTF8StringAVP(AVPDictionaryData dictData) {
		super(dictData);
	}
	
	@Override
	public void encodeData(ByteBuffer buffer) {
		buffer.put(byteData);
	}
	
	@Override
	public void decodeData(ByteBuffer buffer, int length) {
		byteData = new byte[length];
		buffer.get(byteData);
		try {
			data = new String(byteData, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		addDataLength(length);
	}

}
