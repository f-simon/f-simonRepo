package com.fiserv.globalapi.nt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiserv.globalapi.nt.configuration.Config;
import com.fiserv.globalapi.nt.service.McService;
import com.fiserv.globalapi.nt.serviceIpml.McConfigServiceImpl;
import com.fiserv.globalapi.openapi.SearchTokensApi;
import com.fiserv.globalapi.openapi.model.SearchTokensRequestSchema;
import com.fiserv.globalapi.openapi.model.SearchTokensResponseSchema;

@RestController
public class SearchTokensController  implements  SearchTokensApi {
	@Autowired
	private McService mcService;
	
	@Autowired
	private Config config;

	@Autowired
	HttpServletRequest request;
	
	@Override
	public ResponseEntity<SearchTokensResponseSchema> searchTokens(@Valid @RequestBody SearchTokensRequestSchema searchTokensRequestSchema) {
		SearchTokensResponseSchema searchTokensResponseSchema = null;
		
		String exampleString = "{ \"responseHost\" : \"site1.mastercard.com\", \"tokens\" : [ { \"statusTimestamp\" : \"statusTimestamp\", \"suspendedBy\" : [ \"CARDHOLDER\", \"CARDHOLDER\" ], \"productConfig\" : { \"longDescription\" : \"Bank Rewards MasterCard with the super duper rewards program\", \"issuerProductConfigCode\" : \"123456\", \"termsAndConditionsUrl\" : \"https://bank.com/termsAndConditions\", \"issuerMobileApp\" : \"{}\", \"issuerName\" : \"Issuing Bank\", \"cardBackgroundCombinedAssetId\" : \"739d27e5-629d-11e3-949a-0800200c9a66\", \"iconAssetId\" : \"739d87e5-629d-11e3-949a-0800200c9a66\", \"foregroundColor\" : \"0\", \"issuerLogoAssetId\" : \"dbc55444-986a-4896-b41c-5d5e2dd431e2\", \"shortDescription\" : \"Bank Rewards MasterCard\", \"coBrandName\" : \"Co brand partner\", \"onlineBankingLoginUrl\" : \"bank.com\", \"privacyPolicyUrl\" : \"https://bank.com/privacy\", \"customerServiceEmail\" : \"customerservice@bank.com\", \"customerServicePhoneNumber\" : \"1234567891\", \"isCoBranded\" : \"true\", \"brandLogoAssetId\" : \"800200c9-629d-11e3-949a-0739d27e5a66\", \"customerServiceUrl\" : \"https://bank.com/customerservice\", \"coBrandLogoAssetId\" : \"dbc55444-496a-4896-b41c-5d5e2dd431e2\", \"cardBackgroundAssetId\" : \"456d27e5-629d-11e3-949a-0800200c9a66\" }, \"tokenUniqueReference\" : \"DWSPMC000000000132d72d4fcb2f4136a0532d3093ff1a45\", \"tokenInfo\" : { \"accountPanExpiry\" : \"0921\", \"accountPanSuffix\" : \"0011\", \"tokenPanSuffix\" : \"0001\", \"tokenExpiry\" : \"0921\", \"tokenAssuranceLevel\" : \"3\", \"dsrpCapable\" : \"true\", \"productCategory\" : \"CREDIT\" }, \"status\" : \"SUSPENDED\" }, { \"statusTimestamp\" : \"statusTimestamp\", \"suspendedBy\" : [ \"CARDHOLDER\", \"CARDHOLDER\" ], \"productConfig\" : { \"longDescription\" : \"Bank Rewards MasterCard with the super duper rewards program\", \"issuerProductConfigCode\" : \"123456\", \"termsAndConditionsUrl\" : \"https://bank.com/termsAndConditions\", \"issuerMobileApp\" : \"{}\", \"issuerName\" : \"Issuing Bank\", \"cardBackgroundCombinedAssetId\" : \"739d27e5-629d-11e3-949a-0800200c9a66\", \"iconAssetId\" : \"739d87e5-629d-11e3-949a-0800200c9a66\", \"foregroundColor\" : \"0\", \"issuerLogoAssetId\" : \"dbc55444-986a-4896-b41c-5d5e2dd431e2\", \"shortDescription\" : \"Bank Rewards MasterCard\", \"coBrandName\" : \"Co brand partner\", \"onlineBankingLoginUrl\" : \"bank.com\", \"privacyPolicyUrl\" : \"https://bank.com/privacy\", \"customerServiceEmail\" : \"customerservice@bank.com\", \"customerServicePhoneNumber\" : \"1234567891\", \"isCoBranded\" : \"true\", \"brandLogoAssetId\" : \"800200c9-629d-11e3-949a-0739d27e5a66\", \"customerServiceUrl\" : \"https://bank.com/customerservice\", \"coBrandLogoAssetId\" : \"dbc55444-496a-4896-b41c-5d5e2dd431e2\", \"cardBackgroundAssetId\" : \"456d27e5-629d-11e3-949a-0800200c9a66\" }, \"tokenUniqueReference\" : \"DWSPMC000000000132d72d4fcb2f4136a0532d3093ff1a45\", \"tokenInfo\" : { \"accountPanExpiry\" : \"0921\", \"accountPanSuffix\" : \"0011\", \"tokenPanSuffix\" : \"0001\", \"tokenExpiry\" : \"0921\", \"tokenAssuranceLevel\" : \"3\", \"dsrpCapable\" : \"true\", \"productCategory\" : \"CREDIT\" }, \"status\" : \"SUSPENDED\" } ], \"responseId\" : \"123456\" }";
        
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			searchTokensResponseSchema = mapper.readValue(exampleString, SearchTokensResponseSchema.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(searchTokensResponseSchema);
		
		// unique here
	//	SearchTokensResponseSchema searchTokensResponseSchema = null;

				McConfigServiceImpl mcConfigService = new McConfigServiceImpl(config);
				mcConfigService.processConfiguration();

				searchTokensResponseSchema = mcService.getRequest(searchTokensRequestSchema, SearchTokensResponseSchema.class,
						config.getConsumerKey(), mcConfigService.getPrivateKeyOAuth(), mcConfigService.getFieldLevelconfig(),
						request.getMethod(), config.getUriSearchTokens(), null);

				return ResponseEntity.ok().body(searchTokensResponseSchema);
		
	//	return null;
        
	}
}
