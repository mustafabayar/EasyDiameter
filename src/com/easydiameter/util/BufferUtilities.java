package com.easydiameter.util;

import java.nio.ByteBuffer;
import java.util.StringTokenizer;

public class BufferUtilities {

	public static void set3BytesToBuffer(ByteBuffer buffer, long data) {
		buffer.put((byte) (data & 0xFF));
		buffer.put((byte) ((data & 0xFF00) >>> 8));
		buffer.put((byte) ((data & 0xFF0000) >>> 16));
	}

	public static void set4BytesToBuffer(ByteBuffer buffer, long data) {
		buffer.put((byte) (data & 0xFF));
		buffer.put((byte) ((data & 0xFF00) >>> 8));
		buffer.put((byte) ((data & 0xFF0000) >>> 16));
		buffer.put((byte) ((data & 0xFF000000) >>> 24));
	}

	public static int get3BytesFromBuffer(ByteBuffer buffer) {
		return ((buffer.get() & 0xFF) << 16) | ((buffer.get() & 0xFF) << 8) | (buffer.get() & 0xFF);
	}

	public static long get4BytesAsUnsigned32(ByteBuffer buffer) {
		return ((((long) buffer.get() & 0xFF) << 24) | (((long) buffer.get() & 0xFF) << 16)
				| (((long) buffer.get() & 0xFF) << 8) | ((long) buffer.get() & 0xFF));
	}

	public static int calculatePadding(int length) {
		return (((length & 3) != 0) ? (4 - (length & 3)) : 0);
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}
	
	public static String byteToHexString(byte[] data, int start, int length) {
		final char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		StringBuffer buffer = new StringBuffer();
		for (int i = start; i < start + length; i++) {
			buffer.append(digits[(0xF0 & data[i]) >>> 4]);
			buffer.append(digits[0x0F & data[i]]);
		}
		return buffer.toString();
	}
	
	public static String hexToAscii(String data) {
		int n = data.length();
		StringBuilder sb = new StringBuilder(n / 2);
		for (int i = 0; i < n; i += 2) {
			char a = data.charAt(i);
			char b = data.charAt(i + 1);
			sb.append((char) ((hexToInt(a) << 4) | hexToInt(b)));
		}
		return sb.toString();
	}
	  
	public static int hexToInt(char ch) {
		if ('a' <= ch && ch <= 'f') {
			return ch - 'a' + 10;
		}
		if ('A' <= ch && ch <= 'F') {
			return ch - 'A' + 10;
		}
		if ('0' <= ch && ch <= '9') {
			return ch - '0';
		}
		throw new IllegalArgumentException(String.valueOf(ch));
	}
	
	public static void printMessageBuffer(StringBuilder buffer, byte[] msg, int start, int length) {
		final char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		buffer.append("Message Buffer Output :");
		for (int i = start; i < start + length; i++) {
			if (i % 16 == 0) {
				buffer.append("\n");
			} else if (i % 4 == 0) {
				buffer.append(" ");
			}
			buffer.append(digits[(0xF0 & msg[i]) >>> 4]);
			buffer.append(digits[0x0F & msg[i]]);
		}
	}
	
	public static byte[] dottedIpToBytes(String ipAddress) {
		byte[] addr = new byte[4];
		StringTokenizer str = new StringTokenizer(ipAddress, ".");

		addr[0] = (byte) Integer.parseInt(str.nextToken());
		addr[1] = (byte) Integer.parseInt(str.nextToken());
		addr[2] = (byte) Integer.parseInt(str.nextToken());
		addr[3] = (byte) Integer.parseInt(str.nextToken());

		return addr;
	}
	
	public static String byteToDottedIp(byte[] ipAddress) {
		String addressStr = "";
		for (int i = 0; i < 4; ++i)
		{
		    int t = 0xFF & ipAddress[i];
		    addressStr += "." + t;
		}
		addressStr = addressStr.substring(1);
		return addressStr;
	}
}
