import com.easydiameter.application.DiameterApplication;
import com.easydiameter.application.DiameterStack;
import com.easydiameter.packet.message.DiameterMessage;
import com.easydiameter.util.ProtocolDefinitions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This is a very basic example of DiameterApplication, implement it as you need
public class SimpleClientApplication implements DiameterApplication, ProtocolDefinitions {

   private static Logger LOGGER = LoggerFactory.getLogger(SimpleClientApplication.class);

   @Override
   public void receiveMessage(DiameterStack stack, DiameterMessage message) {
      LOGGER.info("Message Received from Client Application");
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
