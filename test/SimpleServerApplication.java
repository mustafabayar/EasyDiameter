import com.easydiameter.application.DiameterApplication;
import com.easydiameter.application.DiameterStack;
import com.easydiameter.exception.DiameterDictionaryException;
import com.easydiameter.packet.message.DiameterMessage;
import com.easydiameter.packet.message.factory.DiameterMessageFactory;
import com.easydiameter.util.ProtocolDefinitions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This is a very basic example of DiameterApplication, implement it as you need
public class SimpleServerApplication implements DiameterApplication, ProtocolDefinitions {

   private static Logger LOGGER = LoggerFactory.getLogger(SimpleServerApplication.class);

   @Override
   public void receiveMessage(DiameterStack stack, DiameterMessage message) {
      LOGGER.info("Message Received from Server Application");

      // Creating message to sent
      DiameterMessage response = DiameterMessageFactory.createMessage(HEADER_FLAG_NONE, COMMAND_CER_CEA, APP_ID_DIAMETER_COMMON_MESSAGE);

      try {
         response.addAVPFromDictionary(AC_PRODUCT_NAME, VENDOR_ID_NONE, "EasyDiameter");
         response.addAVPFromDictionary(AC_SESSION_ID, VENDOR_ID_NONE, "Response Session");
         response.addAVPFromDictionary(AC_USER_NAME, VENDOR_ID_NONE, "MBcoder");
      } catch (DiameterDictionaryException e) {
         LOGGER.error(e.getMessage());
      }

      stack.sendMessage(response.encodePacket());

      /* Do whatever you want here */
   }

   @Override
   public void onConnectionSuccess(String localAddressStr, int localPort, String hostAddress, int remotePort) {
      LOGGER.info("onConnectionSuccess");
      /* Do whatever you want here */
   }

   @Override
   public void onConnectionFail(String hostAddress, int remotePort) {
      LOGGER.info("onConnectionFail");
      /* Do whatever you want here */
   }

   @Override
   public void onSendMessage() {
      LOGGER.info("onSendMessage");
      /* Do whatever you want here */
   }

   @Override
   public void onDisconnect(int i) {
      LOGGER.info("onDisconnect");
      /* Do whatever you want here */
   }

}
