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
import com.fiserv.globalapi.openapi.GetTaskStatusApi;
import com.fiserv.globalapi.openapi.model.GetTaskStatusRequestSchema;
import com.fiserv.globalapi.openapi.model.GetTaskStatusResponseSchema;
import com.fiserv.globalapi.openapi.model.TransactResponseSchema;

import io.swagger.annotations.ApiParam;


@RestController
public class GetTaskStatusController implements GetTaskStatusApi {

	@Autowired
	private McService mcService;
	
	@Autowired
	private Config config;

	@Autowired
	HttpServletRequest request;
	public ResponseEntity<GetTaskStatusResponseSchema> getTaskStatus(
			@Valid @RequestBody GetTaskStatusRequestSchema getTaskStatusRequestSchema) {
		
		
		// unique here
		GetTaskStatusResponseSchema getTaskStatusResponseSchema = null;
				
				McConfigServiceImpl mcConfigService = new McConfigServiceImpl(config);
				mcConfigService.processConfiguration();
				
				getTaskStatusResponseSchema = mcService.getRequest(getTaskStatusRequestSchema, GetTaskStatusResponseSchema.class,
						config.getConsumerKey(), mcConfigService.getPrivateKeyOAuth(), mcConfigService.getFieldLevelconfig(), 
						request.getMethod(), config.getUriGetTaskStatus(), null);

				return ResponseEntity.ok().body(getTaskStatusResponseSchema);


		
	}
	       
}
