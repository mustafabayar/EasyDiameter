package com.easydiameter.packet.message;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import com.easydiameter.exception.DiameterDictionaryException;
import com.easydiameter.exception.DiameterParseException;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.packet.avp.Float32AVP;
import com.easydiameter.packet.avp.Float64AVP;
import com.easydiameter.packet.avp.GroupedAVP;
import com.easydiameter.packet.avp.Integer32AVP;
import com.easydiameter.packet.avp.Integer64AVP;
import com.easydiameter.packet.avp.OctetStringAVP;
import com.easydiameter.packet.avp.Unsigned32AVP;
import com.easydiameter.packet.avp.Unsigned64AVP;
import com.easydiameter.packet.avp.derived.AddressAVP;
import com.easydiameter.packet.avp.derived.DiameterIdentityAVP;
import com.easydiameter.packet.avp.derived.DiameterURIAVP;
import com.easydiameter.packet.avp.derived.EnumeratedAVP;
import com.easydiameter.packet.avp.derived.IPFilterRuleAVP;
import com.easydiameter.packet.avp.derived.QoSFilterRuleAVP;
import com.easydiameter.packet.avp.derived.TimeAVP;
import com.easydiameter.packet.avp.derived.UTF8StringAVP;
import com.easydiameter.packet.avp.factory.AVPFactory;
import com.easydiameter.util.BufferUtilities;
import com.easydiameter.util.ProtocolDefinitions;

public class DiameterMessage implements ProtocolDefinitions {

	private String				name;
	private DiameterHeader		header;
	private List<DiameterAVP>	avpList;
	private byte[]				packet;

	public DiameterMessage(DiameterHeader header) {
		this.header = header;
		this.avpList = new ArrayList<DiameterAVP>();
	}

	public DiameterMessage() {
		this.header = new DiameterHeader();
		this.avpList = new ArrayList<DiameterAVP>();
	}

	public byte[] encodePacket() {
		packet = new byte[getMessageLength()];
		ByteBuffer buffer = ByteBuffer.wrap(packet);

		header.encode(buffer);
		for (DiameterAVP avp : avpList) {
			avp.encode(buffer);
		}
		return packet;
	}

	public static DiameterMessage decodePacket(byte[] packet) throws DiameterParseException {

 		ByteBuffer buffer = ByteBuffer.wrap(packet);

		DiameterHeader header = decodePacketForHeader(buffer);

		DiameterMessage message = new DiameterMessage(header);

		decodePacketForAVPs(buffer, message);

		return message;
	}

	public static DiameterHeader decodePacketForHeader(ByteBuffer buffer) throws DiameterParseException {

        /*
	        0                   1                   2                   3
	        0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
	       +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	       |    Version    |                 Message Length                |
	       +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	       | command flags |                  Command-Code                 |
	       +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	       |                         Application-ID                        |
	       +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	       |                      Hop-by-Hop Identifier                    |
	       +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	       |                      End-to-End Identifier                    |
	       +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	       |  AVPs ...
	       +-+-+-+-+-+-+-+-+-+-+-+-+-

	       Command Flags
	           0 1 2 3 4 5 6 7
	          +-+-+-+-+-+-+-+-+  R(equest), P(roxiable), E(rror)
	          |R P E T r r r r|  T(Potentially re-transmitted message), r(eserved)
	          +-+-+-+-+-+-+-+-+
        */

		byte version;
		int length;
		byte flags;
		int commandCode;
		long applicationId;
		long hopByHopId;
		long endToEndId;

		version = buffer.get();

		length = BufferUtilities.get3BytesFromBuffer(buffer);

		flags = buffer.get();

		commandCode = BufferUtilities.get3BytesFromBuffer(buffer);

		applicationId = BufferUtilities.get4BytesAsUnsigned32(buffer);

		hopByHopId = BufferUtilities.get4BytesAsUnsigned32(buffer);

		endToEndId = BufferUtilities.get4BytesAsUnsigned32(buffer);

		DiameterHeader header = new DiameterHeader(version, flags, commandCode, applicationId, hopByHopId, endToEndId);
		header.setMessageLength(length);

		if (version != DIAMETER_VERSION) {
			String errorMessage = "Unsupported version = " + version;
			throw new DiameterParseException(RC_DIAMETER_UNSUPPORTED_VERSION, header, errorMessage);
		}

		if (length > buffer.limit()) {
			String errorMessage = "Parsed message length value is higher than the received packet size";
			throw new DiameterParseException(RC_DIAMETER_INVALID_MESSAGE_LENGTH, header, errorMessage);
		}

		if ((flags & HEADER_MASK_RESERVED) != 0) {
			String errorMessage = "There are no reserved bits in the header with the value zero (0)";
			throw new DiameterParseException(RC_DIAMETER_INVALID_HDR_BITS, header, errorMessage);
		}

		if (((flags & HEADER_MASK_BIT_R) != 0) && ((flags & HEADER_MASK_BIT_E) != 0)) {
			String errorMessage = "Error bit is set for the Request message";
			throw new DiameterParseException(RC_DIAMETER_INVALID_HDR_BITS, header, errorMessage);
		}

		return header;
	}

	public int getMessageLength() {
		return this.header.getMessageLength();
	}

	public static void decodePacketForAVPs(ByteBuffer buffer, DiameterMessage message) throws DiameterParseException {

		/*
		 * 0                   1                   2                   3
		 * 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
		 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
		 * |                           AVP Code                            |
		 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
		 * |V M P r r r r r|                  AVP Length                   |
		 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
		 * |                        Vendor-ID (opt)                        |
		 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
		 * |    Data ...
		 * +-+-+-+-+-+-+-+-+
		 * 
		 * AVP Flags V(endor-specific), M(andatory), 'P' bit indicates the need
		 * for encryption for end-to-end security
		 * 
		 */

		long avpCode;
		int avpLength;
		byte flags;
		long vendorId;
		int dataLength;

		while (buffer.hasRemaining()) {
			avpCode = BufferUtilities.get4BytesAsUnsigned32(buffer);

			flags = buffer.get();
			if ((flags & AVP_MASK_RESERVED) != 0) {
				String errorMessage = "Invalid AVP bits for AVP = " + avpCode + " with flags = " + flags;
				throw new DiameterParseException(RC_DIAMETER_INVALID_AVP_BITS, message, errorMessage);
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
				throw new DiameterParseException(RC_DIAMETER_INVALID_AVP_LENGTH, failedAVPData, message, errorMessage);
			}

			int padding = BufferUtilities.calculatePadding(avpLength);

			AVPFactory factory = AVPFactory.getAVPFactory(avpCode, vendorId);

			DiameterAVP avp = factory.createAVP(avpCode, flags, vendorId);

			avp.decodeData(buffer, dataLength);

			buffer.position(buffer.position() + padding);

			message.avpList.add(avp);

		}
	}

	public DiameterHeader getHeader() {
		return header;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addAVPIntoList(DiameterAVP avp) {
		this.avpList.add(avp);
		this.header.addLengthToMessage(avp.getAvpLength());
		if(avp instanceof OctetStringAVP || avp instanceof UTF8StringAVP || avp instanceof DiameterIdentityAVP) {
			this.header.addLengthToMessage(BufferUtilities.calculatePadding(avp.getAvpLength()));
		}
	}

	public void addAVP(DiameterAVP avp) {
		addAVPIntoList(avp);
	}

	public void addAVPFromDictionary(long avpCode, long vendorId, String data) throws DiameterDictionaryException {
		DiameterAVP avp = AVPFactory.createAVPFromDictionary(avpCode, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	public void addAVPFromDictionary(long avpCode, long vendorId, byte[] data) throws DiameterDictionaryException {
		DiameterAVP avp = AVPFactory.createAVPFromDictionary(avpCode, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	public void addFloat32AVP(long avpCode, byte flags, long vendorId, float data) {
		Float32AVP avp = new Float32AVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	public void addFloat64AVP(long avpCode, byte flags, long vendorId, double data) {
		Float64AVP avp = new Float64AVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	public void addGroupedAVP(long avpCode, byte flags, long vendorId, ArrayList<DiameterAVP> avpList) {
		GroupedAVP avp = new GroupedAVP(avpCode, flags, vendorId);
		avp.setList(avpList);
		addAVPIntoList(avp);
	}

	public void addInteger32AVP(long avpCode, byte flags, long vendorId, int data) {
		Integer32AVP avp = new Integer32AVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	public void addInteger64AVP(long avpCode, byte flags, long vendorId, long data) {
		Integer64AVP avp = new Integer64AVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	public void addOctetStringAVP(long avpCode, byte flags, long vendorId, String data) {
		OctetStringAVP avp = new OctetStringAVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	public void addOctetStringAVP(long avpCode, byte flags, long vendorId, byte[] data) {
		OctetStringAVP avp = new OctetStringAVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	public void addUnsigned32AVP(long avpCode, byte flags, long vendorId, long data) {
		Unsigned32AVP avp = new Unsigned32AVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	public void addUnsigned64AVP(long avpCode, byte flags, long vendorId, long data) {
		Unsigned64AVP avp = new Unsigned64AVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	public void addAddressAVP(long avpCode, byte flags, long vendorId, InetAddress data) {
		AddressAVP avp = new AddressAVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	public void addAddressAVP(long avpCode, byte flags, long vendorId, byte[] data) {
		AddressAVP avp = new AddressAVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	public void addAddressAVP(long avpCode, byte flags, long vendorId, String data) {
		AddressAVP avp = new AddressAVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	// TODO: To be completed
	public void addDiameterIdentityAVP(long avpCode, byte flags, long vendorId, String data) {
		DiameterIdentityAVP avp = new DiameterIdentityAVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	// TODO: To be completed
	public void addDiameterURIAVP(long avpCode, byte flags, long vendorId, String data) {
		DiameterURIAVP avp = new DiameterURIAVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	// TODO: To be completed
	public void addEnumeratedAVP(long avpCode, byte flags, long vendorId, int data) {
		EnumeratedAVP avp = new EnumeratedAVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	// TODO: To be completed
	public void addIPFilterRuleAVP(long avpCode, byte flags, long vendorId, String data) {
		IPFilterRuleAVP avp = new IPFilterRuleAVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	// TODO: To be completed
	public void addQoSFilterRuleAVP(long avpCode, byte flags, long vendorId, String data) {
		QoSFilterRuleAVP avp = new QoSFilterRuleAVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	// TODO: To be completed
	public void addTimeAVP(long avpCode, byte flags, long vendorId, String data) {
		TimeAVP avp = new TimeAVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	// TODO: To be completed
	public void addTimeAVP(long avpCode, byte flags, long vendorId, byte[] data) {
		TimeAVP avp = new TimeAVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	// TODO: To be completed
	public void addTimeAVP(long avpCode, byte flags, long vendorId, long data) {
		TimeAVP avp = new TimeAVP(avpCode, flags, vendorId);
		// avp.setData(data);
		addAVPIntoList(avp);
	}

	public void addUTF8StringAVP(long avpCode, byte flags, long vendorId, String data) {
		UTF8StringAVP avp = new UTF8StringAVP(avpCode, flags, vendorId);
		avp.setData(data);
		addAVPIntoList(avp);
	}

	public void printContent(StringBuilder sb) {
		sb.append("Message Command Code: " + header.getCommandCode() + ", Application Id: " + header.getApplicationId()
				+ ", HopByHop Id: " + header.getHopByHopId() + ", EndToEnd Id: " + header.getEndToEndId() + "\n");
		for (DiameterAVP avp : avpList) {
			sb.append("   ");
			avp.printContent(sb);
			sb.append("\n");
		}
	}
}
