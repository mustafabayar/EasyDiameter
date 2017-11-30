package com.easydiameter.packet.avp;

import java.nio.ByteBuffer;

import com.easydiameter.dictionary.avp.AVPDictionaryData;

public class Integer64AVP extends DiameterAVP {

	private long data;

	public Integer64AVP(long avpCode, byte flags, long vendorId) {
		super(avpCode, flags, vendorId);
	}

	public Integer64AVP(AVPDictionaryData dictData) {
		super(dictData);
	}

	@Override
	public void encodeData(ByteBuffer buffer) {
		buffer.putLong(data);
	}

	@Override
	public void decodeData(ByteBuffer buffer, int length) {
		data = buffer.getLong();
		addDataLength(length);
	}

	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
		addDataLength(8);
	}

	@Override
	public void setData(String data) {
		this.data = Long.parseLong(data);
		addDataLength(8);
	}

	@Override
	public void printData(StringBuilder sb) {
		sb.append(data);
	}

}
