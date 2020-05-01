package com.fiserv.globalapi.nt.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Config {
	@Value("${mdes.keystore.path}")
	private String pkcs12KeyFilePath;

	@Value("${mdes.keystore.keyAlias}")
	private String keyalias;

	@Value("${mdes.keystore.keyPassword}")
	private String keyPassword;

	@Value("${mdes.oauth.consumerKey}")
	private String consumerKey;

	@Value("${mdes.keystore.privateKeyFilePath}")
	private String privateKeyFilePathDecrypt;

	@Value("${mdes.keystore.certificatePath}")
	private String certificatePath;
	
	@Value("${mdes.oauth.uriToken}")
	private String uriToken;
	
	@Value("${mdes.get.uriGetAsset}")
	private String uriGetAsset;
	
	@Value("${mdes.oauth.uriGetToken}")
	private String uriGetToken;
	
	@Value("${mdes.oauth.uriSuspend}")
	private String uriSuspend;
	
	@Value("${mdes.oauth.uriUnSuspend}")
	private String uriUnSuspend;
	
	@Value("${mdes.oauth.uriDelete}")
	private String uriDelete;
	
	@Value("${mdes.oauth.uriGetTaskStatus}")
	private String uriGetTaskStatus;
	
	@Value("${mdes.oauth.uriTransact}")
	private String uriGetTransact;

	@Value("${mdes.oauth.uriSearchTokens}")
	private String uriSearchTokens;
	

	@Value("${mdes.oauth.uriNotifyTokenUpdated}")
	private String uriNotifyTokenUpdated;
	
	
	public String getUriGetTransact() {
		return uriGetTransact;
	}

	public void setUriGetTransact(String uriGetTransact) {
		this.uriGetTransact = uriGetTransact;
	}

	public String getPkcs12KeyFilePath() {
		return pkcs12KeyFilePath;
	}

	public void setPkcs12KeyFilePath(String pkcs12KeyFilePath) {
		this.pkcs12KeyFilePath = pkcs12KeyFilePath;
	}

	public String getKeyalias() {
		return keyalias;
	}

	public void setKeyalias(String keyalias) {
		this.keyalias = keyalias;
	}

	public String getKeyPassword() {
		return keyPassword;
	}

	public void setKeyPassword(String keyPassword) {
		this.keyPassword = keyPassword;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getPrivateKeyFilePathDecrypt() {
		return privateKeyFilePathDecrypt;
	}

	public void setPrivateKeyFilePathDecrypt(String privateKeyFilePathDecrypt) {
		this.privateKeyFilePathDecrypt = privateKeyFilePathDecrypt;
	}

	public String getCertificatePath() {
		return certificatePath;
	}

	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}

	public String getUriToken() {
		return uriToken;
	}

	public void setUriToken(String uriToken) {
		this.uriToken = uriToken;
	}

	public String getUriGetAsset() {
		return uriGetAsset;
	}

	public void setUriGetAsset(String uriGetAsset) {
		this.uriGetAsset = uriGetAsset;
	}


	public String getUriSuspend() {
		return uriSuspend;
	}

	public void setUriSuspend(String uriSuspend) {
		this.uriSuspend = uriSuspend;
	}

	public String getUriUnSuspend() {
		return uriUnSuspend;
	}

	public String getUriGetToken() {
		return uriGetToken;
	}

	public void setUriGetToken(String uriGetToken) {
		this.uriGetToken = uriGetToken;
	}

	public String getUriGetTaskStatus() {
		return uriGetTaskStatus;
	}

	public void setUriGetTaskStatus(String uriGetTaskStatus) {
		this.uriGetTaskStatus = uriGetTaskStatus;
	}

	public void setUriUnSuspend(String uriUnSuspend) {
		this.uriUnSuspend = uriUnSuspend;
	}

	public String getUriDelete() {
		return uriDelete;
	}

	public void setUriDelete(String uriDelete) {
		this.uriDelete = uriDelete;
	}

	public String getUriNotifyTokenUpdated() {
		return uriNotifyTokenUpdated;
	}

	public void setUriNotifyTokenUpdated(String uriNotifyTokenUpdated) {
		this.uriNotifyTokenUpdated = uriNotifyTokenUpdated;
	}

	public String getUriSearchTokens() {
		return uriSearchTokens;
	}

	public void setUriSearchTokens(String uriSearchTokens) {
		this.uriSearchTokens = uriSearchTokens;
	}

}
