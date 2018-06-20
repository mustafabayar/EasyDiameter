package com.easydiameter.util;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ProtocolUtilities implements ProtocolDefinitions {

   public static AtomicInteger receiverCounter = new AtomicInteger(0);

   public static int findAVPHeaderLength(byte flags) {
      return ((flags & AVP_MASK_BIT_V) == 0) ? AVP_HDR_LEN_WITHOUT_VENDOR : AVP_HDR_LEN_WITH_VENDOR;
   }

   public static long createHopByHopId() {
      long time = System.currentTimeMillis();
      return time %= 1000000;
   }

   public static long createEndToEndId() {
      long e2eId = 0;
      long time = System.currentTimeMillis();

      e2eId |= ((new Random().nextLong() & 0xFF) << 24);
      e2eId |= (time & 0xFFFFFF);

      return e2eId;
   }

}
