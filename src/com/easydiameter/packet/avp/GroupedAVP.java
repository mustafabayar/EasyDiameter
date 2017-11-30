package com.easydiameter.packet.avp;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.exception.DiameterParseException;
import com.easydiameter.packet.avp.factory.AVPFactory;
import com.easydiameter.util.BufferUtilities;

public class GroupedAVP extends DiameterAVP {

	List<DiameterAVP> avpList;

	public GroupedAVP(long avpCode, byte flags, long vendorId) {
		super(avpCode, flags, vendorId);
		avpList = new ArrayList<DiameterAVP>();
	}

	public GroupedAVP(AVPDictionaryData dictData) {
		super(dictData);
		avpList = new ArrayList<DiameterAVP>();
	}

	@Override
	public void encodeData(ByteBuffer buffer) {
		for (DiameterAVP avp : avpList) {
			avp.encode(buffer);
		}
	}

	@Override
	public void decodeData(ByteBuffer buffer, int length) throws DiameterParseException {

		int index = 0;

		long avpCode;
		int avpLength;
		byte flags;
		long vendorId;
		int dataLength;

		while (index < length) {
			avpCode = BufferUtilities.get4BytesAsUnsigned32(buffer);

			flags = buffer.get();
			if ((flags & AVP_MASK_RESERVED) != 0) {
				String errorMessage = "Invalid AVP bits for the AVP = " + avpCode;
				throw new DiameterParseException(RC_DIAMETER_INVALID_AVP_BITS, errorMessage);
			}

			avpLength = BufferUtilities.get3BytesFromBuffer(buffer);

			if ((flags & AVP_MASK_BIT_V) != 0) {
				vendorId = BufferUtilities.get4BytesAsUnsigned32(buffer);
				dataLength = avpLength - AVP_HDR_LEN_WITH_VENDOR;
			} else {
				vendorId = 0;
				dataLength = avpLength - AVP_HDR_LEN_WITHOUT_VENDOR;
			}

			if ((dataLength > avpLength) || (buffer.limit() - buffer.position()) < dataLength) {
				int len = ((buffer.limit() - buffer.position()) < dataLength ? buffer.limit() - buffer.position()
						: avpLength);

				byte[] failedAVPData = new byte[len];
				buffer.get(failedAVPData, 0, len);

				String errorMessage = "Not enough AVP data remaining";
				throw new DiameterParseException(RC_DIAMETER_INVALID_AVP_LENGTH, errorMessage);
			}

			int padding = BufferUtilities.calculatePadding(avpLength);

			AVPFactory factory = AVPFactory.getAVPFactory(avpCode, vendorId);

			DiameterAVP avp = factory.createAVP(avpCode, flags, vendorId);

			avp.decodeData(buffer, dataLength);

			buffer.position(buffer.position() + padding);

			avpList.add(avp);
			
			index += avp.avpLength;
		}

	}

	public void setList(ArrayList<DiameterAVP> avpList) {
		this.avpList = avpList;
		for (DiameterAVP avp : avpList) {
			this.dataLength += avp.avpLength;
		}
		this.avpLength += this.dataLength;
	}

	public void addAVP(DiameterAVP avp) {
		this.avpList.add(avp);
		this.dataLength += avp.avpLength;
		this.avpLength += avp.avpLength;
	}

	@Override
	public void printData(StringBuilder sb) {
		for (DiameterAVP avp : avpList) {
			sb.append("\n      ");
			avp.printContent(sb);
		}
	}

	@Override
	public void setData(String data) {
		// Useless, can't be used for Grouped AVP
	}

}
