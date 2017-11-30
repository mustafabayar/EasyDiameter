package com.easydiameter.packet.avp.derived;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.packet.avp.OctetStringAVP;
import com.easydiameter.util.BufferUtilities;

public class AddressAVP extends OctetStringAVP {

	private short		addressType;
	private InetAddress	address;

	public AddressAVP(long avpCode, byte flags, long vendorId) {
		super(avpCode, flags, vendorId);
	}

	public AddressAVP(AVPDictionaryData dictData) {
		super(dictData);
	}

	@Override
	public void encodeData(ByteBuffer buffer) {
		buffer.put(byteData);
	}

	@Override
	public void decodeData(ByteBuffer buffer, int length) {
		addDataLength(length);
		addressType = buffer.getShort();
		byte[] addr = new byte[length - 2];
		buffer.get(addr);
		try {
			this.address = InetAddress.getByAddress(addr);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		byteData = new byte[length - 2];
		byteData = addr;
	}

	public void setData(InetAddress data) {
		if (data instanceof Inet4Address) {
			this.addressType = ADDRESS_TYPE_IPv4;
			this.address = data;
			byteData = new byte[6];
			byteData[1] = ADDRESS_TYPE_IPv4;
			System.arraycopy(data.getAddress(), 0, byteData, 2, 4);
			addDataLength(6);
		} else if (data instanceof Inet6Address) {
			this.addressType = ADDRESS_TYPE_IPv6;
			this.address = data;
			byteData = new byte[18];
			byteData[1] = ADDRESS_TYPE_IPv6;
			System.arraycopy(data.getAddress(), 0, byteData, 2, 16);
			addDataLength(18);
		} else {
			// TODO: Throw Custom Error
		}
	}
	
	@Override
	public void setData(String data) {
		this.data = data;
		InetAddress addr = null;
		try {
			addr = InetAddress.getByName(data);
		} catch (UnknownHostException e) {
			// TODO: Throw Custom Error
			e.printStackTrace();
		}
		setData(addr);
	}

	public short getAddressType() {
		return addressType;
	}

	public void setAddressType(short addressType) {
		this.addressType = addressType;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}
	
	@Override
	public void printData(StringBuilder sb) {
		String dt = data != null ? data : BufferUtilities.byteToDottedIp(this.byteData);
		sb.append(dt);
	}

}
