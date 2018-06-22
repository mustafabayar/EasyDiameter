package com.easydiameter.packet.avp.derived;

import java.nio.ByteBuffer;

import com.easydiameter.dictionary.avp.AVPDictionaryData;
import com.easydiameter.exception.DiameterParseException;
import com.easydiameter.packet.avp.OctetStringAVP;
import com.easydiameter.util.BufferUtilities;

public class TimeAVP extends OctetStringAVP {

   private long                timeData;

   /* 0h on 1 January 1900 */
   protected static final long ntpTimeBegin = -2208988800000L;

   /* 6h 28m 16s UTC, 7 February 2036 */
   protected static final long ntpTimeEnd   = 2085978496000L;

   public TimeAVP(long avpCode, byte flags, long vendorId) {
      super(avpCode, flags, vendorId);
   }

   public TimeAVP(AVPDictionaryData dictData) {
      super(dictData);
   }

   @Override
   public void encodeData(ByteBuffer buffer) {
      long time;
      if (timeData < ntpTimeEnd) {
         time = timeData - ntpTimeBegin;
      } else {
         time = timeData - ntpTimeEnd;
      }

      time = ((time / 1000) & 0xffffffffL);

      buffer.putInt((int) time);
   }

   @Override
   public void decodeData(ByteBuffer buffer, int length) throws DiameterParseException {
      if (length != 4) {
         String errorMessage = "Wrong length for Time data";
         throw new DiameterParseException(RC_DIAMETER_INVALID_AVP_LENGTH, errorMessage);
      }
      long encodedTime = BufferUtilities.get4BytesAsUnsigned32(buffer);

      timeData = encodedTime * 1000;

      if ((encodedTime & 0x80000000L) == 0) {
         timeData += ntpTimeBegin;
      } else {
         timeData += ntpTimeEnd;
      }
      addDataLength(length);
   }

   public long getTimeData() {
      return timeData;
   }

}
