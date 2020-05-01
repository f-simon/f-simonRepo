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
import com.fiserv.globalapi.openapi.NotifyTokenUpdatedApi;
import com.fiserv.globalapi.openapi.model.NotifyTokenUpdatedRequestSchema;
import com.fiserv.globalapi.openapi.model.NotifyTokenUpdatedResponseSchema;
import com.fiserv.globalapi.openapi.model.TransactResponseSchema;


@RestController
public class NotifyTokenUpdatedController implements NotifyTokenUpdatedApi{
	  
	@Autowired
	private McService mcService;
	
	@Autowired
	private Config config;

	@Autowired
	HttpServletRequest request;
	
	@Override
	public ResponseEntity<NotifyTokenUpdatedResponseSchema> notifyTokenUpdateForTokenStateChange(
			@Valid @RequestBody NotifyTokenUpdatedRequestSchema notifyTokenUpdatedRequestSchema) {
		
		NotifyTokenUpdatedResponseSchema notifyTokenUpdatedResponseSchema = null;
		
		McConfigServiceImpl mcConfigService = new McConfigServiceImpl(config);
		mcConfigService.processConfiguration();
		
		notifyTokenUpdatedResponseSchema = mcService.getRequest(notifyTokenUpdatedRequestSchema, NotifyTokenUpdatedResponseSchema.class,
				config.getConsumerKey(), mcConfigService.getPrivateKeyOAuth(), mcConfigService.getFieldLevelconfig(), 
				request.getMethod(), config.getUriNotifyTokenUpdated(), null);

		return ResponseEntity.ok().body(notifyTokenUpdatedResponseSchema);

		
		
		
	}
	     
	
}
