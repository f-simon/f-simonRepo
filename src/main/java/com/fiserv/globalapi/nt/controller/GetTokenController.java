package com.fiserv.globalapi.nt.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fiserv.globalapi.nt.configuration.Config;
import com.fiserv.globalapi.nt.service.McService;
import com.fiserv.globalapi.nt.serviceIpml.McConfigServiceImpl;
import com.fiserv.globalapi.openapi.GetTokenApi;
import com.fiserv.globalapi.openapi.model.GetTokenRequestSchema;
import com.fiserv.globalapi.openapi.model.GetTokenResponseSchema;



@RestController
public class GetTokenController implements GetTokenApi{
	
	@Autowired
	private McService mcService;
	
	@Autowired
	private Config config;

	@Autowired
	HttpServletRequest request;
	
	
	public GetTokenController() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public ResponseEntity<GetTokenResponseSchema> getToken(@Valid @RequestBody GetTokenRequestSchema getTokenRequestSchema) {
		
	
		GetTokenResponseSchema GetTokenResponseSchema = null;
	
	McConfigServiceImpl mcConfigService = new McConfigServiceImpl(config);
	mcConfigService.processConfiguration();
	
	GetTokenResponseSchema = mcService.getRequest(getTokenRequestSchema, GetTokenResponseSchema.class,
			config.getConsumerKey(), mcConfigService.getPrivateKeyOAuth(), mcConfigService.getFieldLevelconfig(), 
			request.getMethod(), config.getUriGetToken(), null);

	return ResponseEntity.ok().body(GetTokenResponseSchema);

} 

}
