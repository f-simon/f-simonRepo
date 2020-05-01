package com.fiserv.globalapi.nt.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.squareup.okhttp.MediaType;

public class MTRGContants {
	public final static String MC_BASE_ADDRESS = "https://sandbox.api.mastercard.com/mdes";
	public static final Charset UTF8_CHARSET = StandardCharsets.UTF_8;
	
	public static final MediaType JSON = MediaType.parse("application/json; charset=" + UTF8_CHARSET.name());

}
