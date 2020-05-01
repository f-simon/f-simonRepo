package com.fiserv.globalapi.nt.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fiserv.globalapi.nt.configuration.Config;
import com.fiserv.globalapi.nt.service.McService;
import com.fiserv.globalapi.nt.serviceIpml.McConfigServiceImpl;

import com.fiserv.globalapi.openapi.UnsuspendApi;


import com.fiserv.globalapi.openapi.model.UnSuspendRequestSchema;
import com.fiserv.globalapi.openapi.model.UnSuspendResponseSchema;

@RestController
public class UnsuspendController  implements UnsuspendApi {

	@Autowired
	private McService mcService;
	
	@Autowired
	private Config config;

	@Autowired
	HttpServletRequest request;
	
	@Override
	public ResponseEntity<UnSuspendResponseSchema> createUnsuspend(@RequestBody UnSuspendRequestSchema unSuspendRequestSchema) {
		
		// unique here
		UnSuspendResponseSchema unSuspendResponseSchema = null;

		McConfigServiceImpl mcConfigService = new McConfigServiceImpl(config);
		mcConfigService.processConfiguration();

		unSuspendResponseSchema = mcService.getRequest(unSuspendRequestSchema, UnSuspendResponseSchema.class,
				config.getConsumerKey(), mcConfigService.getPrivateKeyOAuth(), mcConfigService.getFieldLevelconfig(),
				request.getMethod(), config.getUriUnSuspend(), null);

		return ResponseEntity.ok().body(unSuspendResponseSchema);

	}
	
}
