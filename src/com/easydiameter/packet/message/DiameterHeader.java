package com.easydiameter.packet.message;

import java.nio.ByteBuffer;

import com.easydiameter.util.BufferUtilities;
import com.easydiameter.util.ProtocolDefinitions;
import com.easydiameter.util.ProtocolUtilities;

public class DiameterHeader implements ProtocolDefinitions {

	private byte	version;
	private int		messageLength;
	private byte	commandFlags;
	private long	commandCode;
	private long	applicationId;
	private long	hopByHopId;
	private long	endToEndId;

	public DiameterHeader(byte version, byte commandFlags, long commandCode, long applicationId, long hopByHopId,
			long endToEndId) {
		this.messageLength = DIAMETER_MSG_HDR_LEN;
		this.version = version;
		this.commandCode = commandCode;
		this.commandFlags = commandFlags;
		this.applicationId = applicationId;
		this.hopByHopId = hopByHopId;
		this.endToEndId = endToEndId;
	}

	public DiameterHeader(byte version, byte commandFlags, long commandCode, long applicationId) {
		this.messageLength = DIAMETER_MSG_HDR_LEN;
		this.version = version;
		this.commandCode = commandCode;
		this.commandFlags = commandFlags;
		this.applicationId = applicationId;
		this.hopByHopId = ProtocolUtilities.createHopByHopId();
		this.endToEndId = ProtocolUtilities.createEndToEndId();
	}

	public DiameterHeader(long commandCode, byte commandFlags, long applicationId) {
		this.version = DIAMETER_VERSION;
		this.messageLength = DIAMETER_MSG_HDR_LEN;
		this.commandCode = commandCode;
		this.commandFlags = commandFlags;
		this.applicationId = applicationId;
		this.hopByHopId = ProtocolUtilities.createHopByHopId();
		this.endToEndId = ProtocolUtilities.createEndToEndId();
	}

	public DiameterHeader(long commandCode, long applicationId) {
		this.messageLength = DIAMETER_MSG_HDR_LEN;
		this.commandCode = commandCode;
		this.applicationId = applicationId;
		this.hopByHopId = ProtocolUtilities.createHopByHopId();
		this.endToEndId = ProtocolUtilities.createEndToEndId();
	}

	public DiameterHeader() {

	}

	public void encode(ByteBuffer buffer) {
		buffer.put(version);
		BufferUtilities.set3BytesToBuffer(buffer, messageLength);
		buffer.put(commandFlags);
		BufferUtilities.set3BytesToBuffer(buffer, commandCode);
		BufferUtilities.set4BytesToBuffer(buffer, applicationId);
		BufferUtilities.set4BytesToBuffer(buffer, hopByHopId);
		BufferUtilities.set4BytesToBuffer(buffer, endToEndId);
	}

	public boolean isRequest() {
		return (commandFlags & HEADER_MASK_BIT_R) != 0;
	}

	public boolean isProxiable() {
		return (commandFlags & HEADER_MASK_BIT_P) != 0;
	}

	public boolean isError() {
		return (commandFlags & HEADER_MASK_BIT_E) != 0;
	}

	public boolean isRetransmit() {
		return (commandFlags & HEADER_MASK_BIT_T) != 0;
	}

	public DiameterHeader setRequest(boolean isRequest) {
		if (isRequest) {
			this.commandFlags |= HEADER_MASK_BIT_R;
		} else {
			this.commandFlags &= ~HEADER_MASK_BIT_R;
		}
		return this;
	}

	public DiameterHeader setProxiable(boolean isProxiable) {
		if (isProxiable) {
			this.commandFlags |= HEADER_MASK_BIT_P;
		} else {
			this.commandFlags &= ~HEADER_MASK_BIT_P;
		}
		return this;
	}

	public DiameterHeader setError(boolean isError) {
		if (isError) {
			commandFlags |= HEADER_MASK_BIT_E;
		} else {
			commandFlags &= ~HEADER_MASK_BIT_E;
		}
		return this;
	}

	public DiameterHeader setRetransmit(boolean isRetransmit) {
		if (isRetransmit) {
			this.commandFlags |= HEADER_MASK_BIT_T;
		} else {
			this.commandFlags &= ~HEADER_MASK_BIT_T;
		}
		return this;
	}

	public byte getVersion() {
		return version;
	}

	public void setVersion(byte version) {
		this.version = version;
	}

	public long getCommandCode() {
		return commandCode;
	}

	public void setCommandCode(long commandCode) {
		this.commandCode = commandCode;
	}

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public int getMessageLength() {
		return messageLength;
	}

	public void setMessageLength(int messageLength) {
		this.messageLength = messageLength;
	}

	public byte getCommandFlags() {
		return commandFlags;
	}

	public void setCommandFlags(byte commandFlags) {
		this.commandFlags = commandFlags;
	}

	public long getHopByHopId() {
		return hopByHopId;
	}

	public void setHopByHopId(long hopByHopId) {
		this.hopByHopId = hopByHopId;
	}

	public long getEndToEndId() {
		return endToEndId;
	}

	public void setEndToEndId(long endToEndId) {
		this.endToEndId = endToEndId;
	}

	public void addLengthToMessage(int length) {
		this.messageLength += length;
	}

}
