package com.easydiameter.packet.message.factory;

import com.easydiameter.packet.message.DiameterHeader;
import com.easydiameter.packet.message.DiameterMessage;
import com.easydiameter.util.ProtocolDefinitions;

public class DiameterMessageFactory implements ProtocolDefinitions {

	public static DiameterMessage createMessage(byte commandFlags, long commandCode, long applicationId) {
		DiameterHeader header = new DiameterHeader(DIAMETER_VERSION, commandFlags, commandCode, applicationId);
		DiameterMessage msg = new DiameterMessage(header);
		return msg;
	}

}
