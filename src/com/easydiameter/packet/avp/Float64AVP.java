package com.easydiameter.packet.avp;

import java.nio.ByteBuffer;

import com.easydiameter.dictionary.avp.AVPDictionaryData;

public class Float64AVP extends DiameterAVP {

	private double data;

	public Float64AVP(long avpCode, byte flags, long vendorId) {
		super(avpCode, flags, vendorId);
	}

	public Float64AVP(AVPDictionaryData dictData) {
		super(dictData);
	}

	@Override
	public void encodeData(ByteBuffer buffer) {
		buffer.putDouble(data);
	}

	@Override
	public void decodeData(ByteBuffer buffer, int length) {
		data = buffer.getDouble();
		addDataLength(length);
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
		addDataLength(8);
	}

	@Override
	public void setData(String data) {
		this.data = Double.parseDouble(data);
		addDataLength(8);
	}

	@Override
	public void printData(StringBuilder sb) {
		sb.append(data);
	}

}
