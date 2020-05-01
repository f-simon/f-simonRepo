package com.fiserv.globalapi.nt.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

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
import com.fiserv.globalapi.openapi.DeleteApi;
import com.fiserv.globalapi.openapi.model.DeleteRequestSchema;
import com.fiserv.globalapi.openapi.model.DeleteResponseSchema;
import com.fiserv.globalapi.openapi.model.SuspendResponseSchema;
import com.fiserv.globalapi.openapi.model.UnSuspendResponseSchema;
import com.jayway.jsonpath.Configuration;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfig;

@RestController
public class DeleteController implements DeleteApi {
	
	@Autowired
	private McService mcService;
	
	@Autowired
	private Config config;

	@Autowired
	HttpServletRequest request;
	
	
	@Override

	public ResponseEntity<DeleteResponseSchema> deleteDigitization(
			@Valid @RequestBody DeleteRequestSchema deleteRequestSchema) {

		// unique here
		DeleteResponseSchema deleteResponseSchema = null;

		McConfigServiceImpl mcConfigService = new McConfigServiceImpl(config);
		mcConfigService.processConfiguration();

		deleteResponseSchema = mcService.getRequest(deleteRequestSchema, DeleteResponseSchema.class,
				config.getConsumerKey(), mcConfigService.getPrivateKeyOAuth(), mcConfigService.getFieldLevelconfig(),
				request.getMethod(), config.getUriSuspend(), null);

		return ResponseEntity.ok().body(deleteResponseSchema);
	}
	
}