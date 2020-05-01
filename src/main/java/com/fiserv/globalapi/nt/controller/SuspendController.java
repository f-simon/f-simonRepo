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
import com.fiserv.globalapi.openapi.SuspendApi;
import com.fiserv.globalapi.openapi.model.SuspendRequestSchema;
import com.fiserv.globalapi.openapi.model.SuspendResponseSchema;


@RestController
public class SuspendController implements SuspendApi {
	
	@Autowired
	private McService mcService;
	
	@Autowired
	private Config config;

	@Autowired
	HttpServletRequest request;
	
	
	@Override
	public ResponseEntity<SuspendResponseSchema> createSuspend(@Valid @RequestBody SuspendRequestSchema suspendRequestSchema) {
		
		
			// unique here
		SuspendResponseSchema suspendResponseSchema = null;
			
			McConfigServiceImpl mcConfigService = new McConfigServiceImpl(config);
			mcConfigService.processConfiguration();
			
			suspendResponseSchema = mcService.getRequest(suspendRequestSchema, SuspendResponseSchema.class,
					config.getConsumerKey(), mcConfigService.getPrivateKeyOAuth(), mcConfigService.getFieldLevelconfig(), 
					request.getMethod(), config.getUriSuspend(), null);

			return ResponseEntity.ok().body(suspendResponseSchema);

		} 
			
}
