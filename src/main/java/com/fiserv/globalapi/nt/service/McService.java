package com.fiserv.globalapi.nt.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.PrivateKey;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiserv.globalapi.nt.util.MTRGContants;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfig;
import com.mastercard.developer.interceptors.OkHttpFieldLevelEncryptionInterceptor;
import com.mastercard.developer.interceptors.OkHttpOAuth1Interceptor;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public interface McService {
	
    default <S, T>  T getRequest(S request, Class<T> type,String consumerKey, PrivateKey signingKey, 
    		FieldLevelEncryptionConfig config, String requestMethod, String uri, String uriParam){
    	
    	OkHttpClient client;
    	
    	T returnType = null;
    	switch(requestMethod) {
    	
    	
    	// associate POST request with OAuth1 and encryption interceptors
    	case "POST":
    		client = new OkHttpClient().newBuilder()
			.addInterceptor(new OkHttpFieldLevelEncryptionInterceptor(config))
			.addInterceptor(new OkHttpOAuth1Interceptor(consumerKey, signingKey))
			.build();
	
    		break;
    		
    	default:	// GET request -- all other are POST
    		client = new OkHttpClient().newBuilder()
			.addInterceptor(new OkHttpOAuth1Interceptor(consumerKey, signingKey))
			.build();
    		
    	}
    
    	returnType = getRequest(request, type, client, uri, uriParam);
	    return returnType;	
	}
	
	
   
	default <S, T> T getRequest(S request, Class<T> type, OkHttpClient client, String uri, String assetId){
    	
		ObjectMapper json = new ObjectMapper();
		String payload = null;
		try {
			payload = json.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}

		MediaType jsonMediaType = MediaType.parse("application/json; charset=" + MTRGContants.UTF8_CHARSET.name());
        
		if(null != assetId) {
			// build the get request
			uri = uri.replace("{AssetId}", assetId);      // update uri with parameter value
		}
		RequestBody body = RequestBody.create(payload, jsonMediaType);
		Request requestBuilder = new Request.Builder()
				.url(MTRGContants.MC_BASE_ADDRESS + uri)
				.post(body)
				.build();
		
		Response response = null;;
		try {
				response = client.newCall(requestBuilder).execute();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		   ObjectMapper objectMapper = new ObjectMapper();
		   objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
		T responseSchema = null;
		
		try {
				responseSchema = objectMapper.readValue(response.body().string(),type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return responseSchema;
    }
}
	
