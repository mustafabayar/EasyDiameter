package com.easydiameter.dictionary.avp;

import java.util.HashMap;
import java.util.Map;

public class GroupedAVPDictionaryData extends AVPDictionaryData {

	boolean								containsAny;
	int									minAny;
	private Map<AVPKey, AVPOccurrence>	avpList;

	public GroupedAVPDictionaryData(String name, long code, byte flags, long vendorId, int dataType) {
		super(name, code, flags, vendorId, dataType);
		avpList = new HashMap<AVPKey, AVPOccurrence>();
		containsAny = false;
	}

	public void add(long code, long vendorId, int occType, int min, int max) {
		AVPKey key = new AVPKey(code, vendorId);
		AVPOccurrence occurance = new AVPOccurrence(min, max, occType);

		avpList.put(key, occurance);
	}

	public void containsAny(boolean any, int minAny) {
		this.containsAny = any;
		this.minAny = minAny;
	}

}
