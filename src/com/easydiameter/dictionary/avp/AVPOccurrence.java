package com.easydiameter.dictionary.avp;

public class AVPOccurrence {
	
	public static final int	AVPOCC_OPTIONAL		= 0;
	public static final int	AVPOCC_MANDATORY	= 1;
	public static final int	AVPOCC_FIXED		= 2;

	private String avpName;
	private int	min;
	private int	max;
	private int	occType;

	public AVPOccurrence(int min, int max, int occType) {
		this.min = min;
		this.max = max;
		this.occType = occType;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}

		AVPOccurrence key = (AVPOccurrence) obj;
		if ((this.min == key.min) && (this.max == key.max) && (this.occType == key.occType)) {
			return true;
		} else {
			return false;
		}
	}

	public String getAvpName() {
		return avpName;
	}

	public void setAvpName(String avpName) {
		this.avpName = avpName;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getOccType() {
		return occType;
	}

	public void setOccType(int occType) {
		this.occType = occType;
	}
}
