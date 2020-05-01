package com.fiserv.globalapi.nt.serviceIpml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiserv.globalapi.nt.configuration.Config;
import com.fiserv.globalapi.nt.service.McConfigService;
import com.jayway.jsonpath.Configuration;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfig;

@Service
public class McConfigServiceImpl implements McConfigService {

	private Config config = null;

	@Autowired
	public McConfigServiceImpl(final Config config) {
		this.config = config;
	}

	public McConfigServiceImpl() {
	}

	PrivateKey privateKeyOAuth;
	PrivateKey decryptionKey;

	Certificate encryptionCertificate;

	private FieldLevelEncryptionConfig fieldLevelconfig;
	private Configuration jsonPathConfig;

	public void processConfiguration() {

		try { // signing key
			privateKeyOAuth = loadSigningKey(config.getPkcs12KeyFilePath(), config.getKeyalias(),
					config.getKeyPassword());
		} catch (UnrecoverableKeyException e) {
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

		try {

			encryptionCertificate = loadEncryptionCertificate(config.getCertificatePath());
			
			PublicKey publickey =  encryptionCertificate.getPublicKey();
		
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// get JacksonJsonEngine
		jsonPathConfig = setUpJsonProvider();

		try {
			decryptionKey = loadDecryptionKey(config.getPrivateKeyFilePathDecrypt());
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fieldLevelconfig = getMdesApiFieldLevelConfig(encryptionCertificate, decryptionKey);

	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public PrivateKey getPrivateKeyOAuth() {
		return privateKeyOAuth;
	}

	public void setPrivateKeyOAuth(PrivateKey privateKeyOAuth) {
		this.privateKeyOAuth = privateKeyOAuth;
	}

	public PrivateKey getDecryptionKey() {
		return decryptionKey;
	}

	public void setDecryptionKey(PrivateKey decryptionKey) {
		this.decryptionKey = decryptionKey;
	}

	public Certificate getEncryptionCertificate() {
		return encryptionCertificate;
	}

	public void setEncryptionCertificate(Certificate encryptionCertificate) {
		this.encryptionCertificate = encryptionCertificate;
	}

	public FieldLevelEncryptionConfig getFieldLevelconfig() {
		return fieldLevelconfig;
	}

	public void setFieldLevelconfig(FieldLevelEncryptionConfig fieldLevelconfig) {
		this.fieldLevelconfig = fieldLevelconfig;
	}

	public Configuration getJsonPathConfig() {
		return jsonPathConfig;
	}

	public void setJsonPathConfig(Configuration jsonPathConfig) {
		this.jsonPathConfig = jsonPathConfig;
	}

}
