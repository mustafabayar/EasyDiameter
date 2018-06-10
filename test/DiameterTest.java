import com.easydiameter.util.ProtocolDefinitions;
import com.easydiameter.exception.DiameterDictionaryException;
import com.easydiameter.exception.DiameterParseException;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.packet.avp.GroupedAVP;
import com.easydiameter.packet.avp.Integer32AVP;
import com.easydiameter.packet.avp.Unsigned32AVP;
import com.easydiameter.packet.avp.derived.EnumeratedAVP;
import com.easydiameter.packet.avp.factory.AVPFactory;
import com.easydiameter.packet.avp.factory.EnumeratedAVPFactory;
import com.easydiameter.packet.message.DiameterHeader;
import com.easydiameter.packet.message.DiameterMessage;
import com.easydiameter.packet.message.factory.DiameterMessageFactory;
import com.easydiameter.util.BufferUtilities;

public class DiameterTest implements ProtocolDefinitions {

	public static void main(String[] args) throws DiameterParseException, DiameterDictionaryException {

		////////////////////////////////////
		/* How to create Diameter Message */
		////////////////////////////////////
		
		// Method 1 - Create Header first and create the message using header.

		// A)
		DiameterHeader header1 = new DiameterHeader(COMMAND_STR_STA, APP_ID_DIAMETER_COMMON_MESSAGE);
		header1.setRequest(true);
		header1.setProxiable(true);
		DiameterMessage msg1 = new DiameterMessage(header1);

		// B) 
		DiameterHeader header2 = new DiameterHeader(DIAMETER_VERSION, HEADER_FLAG_R_P, COMMAND_STR_STA,
				APP_ID_DIAMETER_COMMON_MESSAGE);
		DiameterMessage msg2 = new DiameterMessage(header2);

		// Method 2 - Use Message factory

		DiameterMessage msg3 = DiameterMessageFactory.createMessage(HEADER_FLAG_R_P, COMMAND_STR_STA,
				APP_ID_DIAMETER_COMMON_MESSAGE);
		
		/////////////////////////////////////////////
		/* How to create AVP and add it to message */
		/////////////////////////////////////////////
		
		// Method 1 - Use created message to add your AVP
		
		msg1.addOctetStringAVP(AC_ORIGIN_REALM, AVP_FLAG_M, VENDOR_ID_NONE, "My_Realm_1111");
		
		msg1.addAVPFromDictionary(AC_SESSION_ID, VENDOR_ID_NONE, "My Session Id");
		
		msg1.addInteger32AVP(AC_PORT, AVP_FLAG_M, VENDOR_ID_NONE, 3868);
		
		msg1.addAddressAVP(AC_IP_ADDRESS, AVP_FLAG_NONE, VENDOR_ID_NONE, "127.0.0.1"); // Accepts Inet Address too
		
		String hex = "52e636f6d0";
		byte[] raw = BufferUtilities.hexStringToByteArray(hex);
		msg1.addOctetStringAVP(AC_CC_SUB_SESSION_ID, AVP_FLAG_NONE, VENDOR_ID_NONE, raw);

		msg1.addUTF8StringAVP(AC_BORDER_ROUTER_NAME, AVP_FLAG_NONE, VENDOR_ID_NONE, "Router X");
		
		// For the Enumerated AVP values, look into ProtocolDefinitions.java, 
		// I have tried to add values for common Enumerated AVPs. You can also simply give the integer value of the AVP.
		// In the below example I have added Auth-Request-Type AVP with the value of 3(Authorize_Authenticate).
		// I have tried to name Enumerated AVP values with initials of the AVP, for example for the Disconnect-Cause AVP,
		// It's corresponding values are;
		//		DC_REBOOTING										= 0;					/* [RFC6733] */
		//		DC_BUSY												= 1;					/* [RFC6733] */
		//		DC_DO_NOT_WANT_TO_TALK_TO_YOU						= 2;					/* [RFC6733] */
		msg1.addEnumeratedAVP(AC_AUTH_REQUEST_TYPE, AVP_FLAG_M, VENDOR_ID_NONE, ART_AUTHORIZE_AUTHENTICATE);
		//or
		EnumeratedAVP disconnectCause = new EnumeratedAVP(AC_DISCONNECT_CAUSE, AVP_FLAG_M, VENDOR_ID_NONE);
		disconnectCause.setData(DC_BUSY);
		msg1.addAVP(disconnectCause);
		
		// Grouped AVP - Example of Group AVP with depth of 3 inner AVP
		GroupedAVP multipleServicesCreditControl = new GroupedAVP(AC_MULTIPLE_SERVICES_CREDIT_CONTROL, AVP_FLAG_M, VENDOR_ID_NONE);
		
		GroupedAVP grantedServiceUnit = new GroupedAVP(AC_GRANTED_SERVICE_UNIT, AVP_FLAG_M, VENDOR_ID_NONE);
			
		Unsigned32AVP ccTime = new Unsigned32AVP(AC_CC_TIME, AVP_FLAG_M, VENDOR_ID_NONE);
		ccTime.setData(10);
		
		grantedServiceUnit.addAVP(ccTime);
		
		multipleServicesCreditControl.addAVP(grantedServiceUnit);
		
		msg1.addAVP(multipleServicesCreditControl);
		
		///////////////////////////////////////////////////////////////////////////////////////////

		// Method 2 - Create AVP first, and add it to the message
		
		// A) -Throws error if there is no such avp in dictionary
		DiameterAVP avp1 = AVPFactory.createAVPFromDictionary(AC_EAP_PAYLOAD, VENDOR_ID_NONE);
		String hex2 = "000000000000000000000000000000000000000000000001";
		byte[] eapPayload = BufferUtilities.hexStringToByteArray(hex2);
		avp1.setData(eapPayload); // You can set data as byte array
		msg1.addAVP(avp1);
		
		// B)
		AVPFactory factory = AVPFactory.getAVPFactory(AC_RESULT_CODE, VENDOR_ID_NONE);
		DiameterAVP avp2 = factory.createAVP(AC_RESULT_CODE, AVP_FLAG_NONE, VENDOR_ID_NONE);
		avp2.setData("2"); // or you can set data as string and it will convert it to actual data format according to AVP data type
		msg1.addAVP(avp2);
		
		// C)
		Integer32AVP avp3 = new Integer32AVP(AC_CC_REQUEST_TYPE, AVP_FLAG_NONE, VENDOR_ID_NONE);
		avp3.setData(1);
		msg1.addAVP(avp3);
		
		StringBuilder sb1 = new StringBuilder();
		msg1.printContent(sb1);
		System.out.println(sb1.toString());
		
		byte[] packet = msg1.encodePacket(); // Ready to send
		
		StringBuilder sb2 = new StringBuilder();
		BufferUtilities.printMessageBuffer(sb2, packet, 0, msg1.getMessageLength());
		System.out.println(sb2.toString());
	
		// Now assume we received a message, how to parse it ?
		
		String hexData = "010000e08000010100000000000001eb01a2cb5b000001084000000f4d42636f6465720000000128400000124f70656e536f757263650000000001014000000e00017f00000100000000010a4000000c000028af0000010d00000014456173794469616d65746572000001094000000c000028af00000104400000200000010a4000000c000028af000001024000000c0100003000000104400000200000010a4000000c000028af000001024000000c0100002200000104400000200000010a4000000c000028af000001024000000c010000380000010b0000000c00000001";
		byte[] rawData = BufferUtilities.hexStringToByteArray(hexData); // rawData is the data we will receive from socket
		
		DiameterMessage message = DiameterMessage.decodePacket(rawData);
		
		StringBuilder sb3 = new StringBuilder();
		message.printContent(sb3);
		System.out.println(sb3.toString());
		

	}
}
