package com.mastercard.developer.signers;

import com.mastercard.developer.oauth.OAuth;
import feign.RequestTemplate;

import java.net.URI;
import java.nio.charset.Charset;
import java.security.PrivateKey;

public class OpenFeignSigner extends AbstractSigner {

    private final String baseUri;

    public OpenFeignSigner(String consumerKey, PrivateKey signingKey, String baseUri) {
        super(consumerKey, signingKey);
        this.baseUri = baseUri;
    }

    public OpenFeignSigner(Charset charset, String consumerKey, PrivateKey signingKey, String baseUri) {
        super(charset, consumerKey, signingKey);
        this.baseUri = baseUri;
    }

    public void sign(RequestTemplate requestTemplate) {
        URI uri = URI.create(baseUri.replaceAll("/$", "") + requestTemplate.request().url());
        String method = requestTemplate.method();
        byte[] bodyBytes = requestTemplate.body();
        String payload = bodyBytes != null ? new String(bodyBytes, charset) : null;
        String authHeader = OAuth.getAuthorizationHeader(uri, method, payload, charset, consumerKey, signingKey);
        requestTemplate.header(OAuth.AUTHORIZATION_HEADER_NAME, authHeader);
    }
}
