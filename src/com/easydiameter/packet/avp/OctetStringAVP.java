package com.easydiameter.packet.avp;

import java.nio.ByteBuffer;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.exception.DiameterParseException;
import com.easydiameter.util.BufferUtilities;

public class OctetStringAVP extends DiameterAVP {
	
	protected String data;

	public OctetStringAVP(long avpCode, byte flags, long vendorId) {
		super(avpCode, flags, vendorId);
	}
	
	public OctetStringAVP(AVPDictionaryData dictData) {
		super(dictData);
	}
	
	@Override
	public void encodeData(ByteBuffer buffer) {
		buffer.put(byteData);
	}
	
	@Override
	public void decodeData(ByteBuffer buffer, int length) throws DiameterParseException {
		byteData = new byte[length];
		buffer.get(byteData);
		data = new String(byteData);
		addDataLength(length);
	}

	public String getData() {
		return data;
	}

	@Override
	public void setData(String data) {
		this.data = data;
		this.byteData = data.getBytes();
		addDataLength(data.length());
	}

	@Override
	public void printData(StringBuilder sb) {
		String dt = data != null ? data : BufferUtilities.byteToHexString(this.byteData, 0, this.dataLength) + "(Hex)";
		sb.append(dt);
	}
	
}
