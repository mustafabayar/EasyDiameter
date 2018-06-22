package com.easydiameter.exception;

public class DiameterInvalidLengthException extends DiameterException {

   private static final long serialVersionUID = 1L;

   public DiameterInvalidLengthException(long error, String message) {
      super(error, message);
   }

}
