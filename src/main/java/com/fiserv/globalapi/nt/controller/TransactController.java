package com.fiserv.globalapi.nt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fiserv.globalapi.nt.configuration.Config;
import com.fiserv.globalapi.nt.service.McConfigService;
import com.fiserv.globalapi.nt.service.McService;
import com.fiserv.globalapi.nt.serviceIpml.McConfigServiceImpl;
import com.fiserv.globalapi.openapi.TransactApi;
import com.fiserv.globalapi.openapi.model.TokenizeResponseSchema;
import com.fiserv.globalapi.openapi.model.TransactRequestSchema;
import com.fiserv.globalapi.openapi.model.TransactResponseSchema;


@RestController
public class TransactController implements TransactApi {
    
	@Autowired
	private McService mcService;
	
	@Autowired
	private Config config;

	@Autowired
	HttpServletRequest request;
	
	public ResponseEntity<TransactResponseSchema> createTransact(
			@Valid @RequestBody TransactRequestSchema transactRequestSchema) {

		// unique here
		TransactResponseSchema transactResponseSchema = null;
		
		McConfigServiceImpl mcConfigService = new McConfigServiceImpl(config);
		mcConfigService.processConfiguration();
		
		transactResponseSchema = mcService.getRequest(transactRequestSchema, TransactResponseSchema.class,
				config.getConsumerKey(), mcConfigService.getPrivateKeyOAuth(), mcConfigService.getFieldLevelconfig(), 
				request.getMethod(), config.getUriGetTransact(), null);

		return ResponseEntity.ok().body(transactResponseSchema);

		
	} 

}
