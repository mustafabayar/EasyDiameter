package com.easydiameter.packet.avp.factory;

import com.easydiameter.dictionary.avp.AVPDictionary;
import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.exception.DiameterDictionaryException;
import com.easydiameter.exception.DiameterException;
import com.easydiameter.packet.avp.DiameterAVP;
import com.easydiameter.util.ProtocolDefinitions;

public abstract class AVPFactory implements ProtocolDefinitions {

   public abstract DiameterAVP createAVP(long avpCode, byte flags, long vendorId);

   public abstract DiameterAVP createAVP(AVPDictionaryData dictData);

   public static DiameterAVP createAVPFromDictionary(long avpCode, long vendorId) throws DiameterDictionaryException {
      AVPDictionaryData dictData = AVPDictionary.getDictionaryData(avpCode, vendorId);
      if (dictData.getDataType() == DT_UNKNOWN) {
         String errorMessage = "No such AVP in dictionary";
         throw new DiameterDictionaryException(DiameterException.DIAMETER_DICTIONARY_EXCEPTION, errorMessage);
      }
      DiameterAVP avp = dictData.getFactory().createAVP(dictData);
      avp.setName(dictData.getName());
      return avp;
   }

   public static AVPFactory getAVPFactory(long code, long vendorId) {
      AVPDictionaryData dictData = AVPDictionary.getDictionaryData(code, vendorId);
      return getAVPFactory(dictData.getDataType());

   }

   public static AVPFactory getAVPFactory(int dataType) {
      AVPFactory factory = null;
      switch (dataType) {
      case DT_OCTET_STRING:
         factory = OctetStringAVPFactory.getInstance();
         break;
      case DT_INTEGER_32:
         factory = Integer32AVPFactory.getInstance();
         break;
      case DT_INTEGER_64:
         factory = Integer64AVPFactory.getInstance();
         break;
      case DT_UNSIGNED_32:
         factory = Unsigned32AVPFactory.getInstance();
         break;
      case DT_UNSIGNED_64:
         factory = Unsigned64AVPFactory.getInstance();
         break;
      case DT_FLOAT_32:
         factory = Float32AVPFactory.getInstance();
         break;
      case DT_FLOAT_64:
         factory = Float64AVPFactory.getInstance();
         break;
      case DT_GROUPED:
         factory = GroupedAVPFactory.getInstance();
         break;
      case DT_ADDRESS:
         factory = AddressAVPFactory.getInstance();
         break;
      case DT_TIME:
         factory = TimeAVPFactory.getInstance();
         break;
      case DT_UTF8STRING:
         factory = UTF8StringAVPFactory.getInstance();
         break;
      case DT_DIAMETER_IDENTITY:
         factory = DiameterIdentityAVPFactory.getInstance();
         break;
      case DT_DIAMETER_URI:
         factory = DiameterURIAVPFactory.getInstance();
         break;
      case DT_ENUMERATED:
         factory = EnumeratedAVPFactory.getInstance();
         break;
      case DT_IP_FILTER_RULE:
         factory = IPFilterRuleAVPFactory.getInstance();
         break;
      case DT_QoS_FILTER_RULE:
         factory = QoSFilterRuleAVPFactory.getInstance();
         break;
      default:
         factory = GenericAVPFactory.getInstance();
         break;
      }
      return factory;
   }
}
