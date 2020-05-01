package com.fiserv.globalapi.nt.controller;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fiserv.globalapi.nt.configuration.Config;
import com.fiserv.globalapi.nt.service.McService;
import com.fiserv.globalapi.nt.serviceIpml.McConfigServiceImpl;
import com.fiserv.globalapi.openapi.TokenizeApi;
import com.fiserv.globalapi.openapi.model.TokenizeRequestSchema;
import com.fiserv.globalapi.openapi.model.TokenizeResponseSchema;

/*
 * Submit a Tokenize request with encrypted data using the sandbox key (Certificate -public key)
 * Decrypt the encrypted data in the Tokenize response user the Private key
 */
@RestController
public class TokenizeController implements TokenizeApi {

	@Autowired
	private McService mcService;
	
	@Autowired
	private Config config;

	@Autowired
	HttpServletRequest request;
	
	
	public TokenizeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@GetMapping(("/hello"))
	public String sayHello() {
		return "Hello Simon";
	}

	@Override
	public ResponseEntity<TokenizeResponseSchema> createTokenize(
			@Valid @RequestBody TokenizeRequestSchema tokenizeRequestSchema) {

		// unique here
		TokenizeResponseSchema tokenizeResponseSchema = null;

		McConfigServiceImpl mcConfigService = new McConfigServiceImpl(config);
		mcConfigService.processConfiguration();

		tokenizeResponseSchema = mcService.getRequest(tokenizeRequestSchema, TokenizeResponseSchema.class,
				config.getConsumerKey(), mcConfigService.getPrivateKeyOAuth(), mcConfigService.getFieldLevelconfig(),
				request.getMethod(), config.getUriToken(), null);

		return ResponseEntity.ok().body(tokenizeResponseSchema);

	}

}
