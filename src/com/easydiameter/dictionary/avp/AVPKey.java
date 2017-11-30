package com.easydiameter.dictionary.avp;

public class AVPKey {

	private long	code;
	private long	vendorId;

	public AVPKey(long code, long vendorId) {
		this.code = code;
		this.vendorId = vendorId;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}

		AVPKey key = (AVPKey) obj;
		if ((this.code == key.getCode()) && (this.vendorId == key.getVendorId())) {
			return true;
		} else {
			return false;
		}

	}

	public int hashCode() {
		return (int) (code + vendorId);
	}

	public long getCode() {
		return code;
	}

	public long getVendorId() {
		return vendorId;
	}

}
