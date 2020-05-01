package com.fiserv.globalapi.nt.util;

import java.util.UUID;

public class CorrelationIdGenerator {
	
	private static String uuid = UUID.randomUUID().toString();

	public static String getUuid() {
		return uuid;
	}

}
