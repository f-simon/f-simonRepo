package com.fiserv.globalapi.nt.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fiserv.globalapi.nt.configuration.Config;
import com.fiserv.globalapi.nt.service.McService;
import com.fiserv.globalapi.nt.serviceIpml.McConfigServiceImpl;
import com.fiserv.globalapi.openapi.GetAssetApi;
import com.fiserv.globalapi.openapi.model.AssetResponseSchema;


@RestController
public class AssetController implements GetAssetApi{
	


	@Autowired
	private McService mcService;
	
	@Autowired
	private Config config;

	@Autowired
	HttpServletRequest request;
	
	@Override
	public ResponseEntity<AssetResponseSchema> getAsset(@PathVariable(value = "AssetId", required=true) String assetId){

		AssetResponseSchema assetResponseSchema = null;
		
		McConfigServiceImpl mcConfigService = new McConfigServiceImpl(config);
		mcConfigService.processConfiguration();
		
		assetResponseSchema = mcService.getRequest(null, AssetResponseSchema.class,
				config.getConsumerKey(), mcConfigService.getPrivateKeyOAuth(), mcConfigService.getFieldLevelconfig(), 
				request.getMethod(), config.getUriGetAsset(), assetId);

		return ResponseEntity.ok().body(assetResponseSchema);

	} 

}
