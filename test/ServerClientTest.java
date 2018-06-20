import java.io.IOException;

import org.apache.log4j.Logger;

import com.easydiameter.application.DiameterApplication;
import com.easydiameter.application.client.EasyDiameterClient;
import com.easydiameter.application.server.EasyDiameterServer;
import com.easydiameter.exception.DiameterDictionaryException;
import com.easydiameter.packet.message.DiameterMessage;
import com.easydiameter.packet.message.factory.DiameterMessageFactory;
import com.easydiameter.util.BufferUtilities;
import com.easydiameter.util.ProtocolDefinitions;

public class ServerClientTest implements ProtocolDefinitions {

   public static void main(String[] args) throws DiameterDictionaryException, IOException, InterruptedException {

      final Logger LOGGER = Logger.getLogger(ServerClientTest.class);

      DiameterApplication serverApplication = new SimpleServerApplication();
      EasyDiameterServer diameterServer = new EasyDiameterServer(serverApplication, "127.0.0.1", 3868); // Local Address to bind
      diameterServer.start(); // Start Server

      DiameterApplication clientApplication = new SimpleClientApplication();
      EasyDiameterClient diameterClient = new EasyDiameterClient(clientApplication, "127.0.0.1", 3868); // Remote address to send
      diameterClient.bindToLocalAddress("127.0.0.1", 3870); // Optional, if not set, it will bind to random local port

      // Creating message to sent
      DiameterMessage message = DiameterMessageFactory.createMessage(HEADER_FLAG_R_P, COMMAND_CER_CEA, APP_ID_DIAMETER_COMMON_MESSAGE);
      message.addAVPFromDictionary(AC_SESSION_ID, VENDOR_ID_NONE, "My Session Id");
      message.addAVPFromDictionary(AC_PRODUCT_NAME, VENDOR_ID_NONE, "EasyDiameter");
      message.addAVPFromDictionary(AC_USER_NAME, VENDOR_ID_NONE, "MBcoder");

      // Print Message Content
      StringBuilder sb = new StringBuilder();
      message.printContent(sb);
      LOGGER.debug(sb.toString());

      byte[] packet = message.encodePacket(); // Ready to send

      // Print Byte Packet
      sb.setLength(0); // Clear the previous StringBuilder
      BufferUtilities.printMessageBuffer(sb, packet, 0, message.getMessageLength());
      LOGGER.debug(sb.toString());

      diameterClient.send(packet);

      Thread.sleep(3000);

      diameterServer.destroy();
      diameterClient.destroy();
   }

}
