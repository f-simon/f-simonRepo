package com.fiserv.globalapi.nt.service;

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

import com.jayway.jsonpath.Configuration;
import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.developer.encryption.FieldLevelEncryption;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfig;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfigBuilder;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfig.FieldValueEncoding;
import com.mastercard.developer.json.JacksonJsonEngine;
import com.mastercard.developer.utils.AuthenticationUtils;
import com.mastercard.developer.utils.EncryptionUtils;


public interface McConfigService {
	
	default PrivateKey loadSigningKey(String pkcs12KeyFilePath, String signingKeyAlias,String signingKeyPassword)
			throws IOException, NoSuchProviderException, KeyStoreException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
		
		
		
		try {
			return AuthenticationUtils.loadSigningKey(pkcs12KeyFilePath, signingKeyAlias, signingKeyPassword);
		}catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	default Certificate loadEncryptionCertificate(String certificatePath) throws CertificateException, FileNotFoundException {
		
		
		try {
				return EncryptionUtils.loadEncryptionCertificate(certificatePath);
		} catch(CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	default PrivateKey loadDecryptionKey(String keyFilePath) throws GeneralSecurityException, IOException{
		
		try {
			return EncryptionUtils.loadDecryptionKey(keyFilePath);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	default Configuration setUpJsonProvider() {
		return (FieldLevelEncryption.withJsonEngine(new JacksonJsonEngine()));
		
	}
	
	default FieldLevelEncryptionConfig getMdesApiFieldLevelConfig(Certificate encryptionCertificate, PrivateKey decryptionKey) {
		
		FieldLevelEncryptionConfig config = null;
		try {
			config = FieldLevelEncryptionConfigBuilder.aFieldLevelEncryptionConfig()
			        .withEncryptionPath("$.cardInfo.encryptedData", "$.cardInfo") // Before version 1.2.9
			        .withEncryptionPath("$.fundingAccountInfo.encryptedPayload.encryptedData", "$.fundingAccountInfo.encryptedPayload")
			        .withEncryptionPath("$.encryptedPayload.encryptedData", "$.encryptedPayload")
			        .withDecryptionPath("$.tokenDetail", "$.tokenDetail.encryptedData")
			        .withDecryptionPath("$.encryptedPayload", "$.encryptedPayload.encryptedData")
			        .withEncryptionCertificate(encryptionCertificate)
			        .withDecryptionKey(decryptionKey)
			        .withOaepPaddingDigestAlgorithm("SHA-512")
			        .withEncryptedValueFieldName("encryptedData")
			        .withEncryptedKeyFieldName("encryptedKey")
			        .withIvFieldName("iv")
			        .withOaepPaddingDigestAlgorithmFieldName("oaepHashingAlgorithm")
			        .withEncryptionCertificateFingerprintFieldName("publicKeyFingerprint")
			        .withFieldValueEncoding(FieldValueEncoding.HEX)
			        .build();
		} catch (EncryptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return config;
		
	}
	
	public void processConfiguration();
}
