package com.currency.app;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CurrencyReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyReportApplication.class, args);
	}
	
	@Bean
	public HttpClient getHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
	    SSLContext sslContext = SSLContexts.custom().build();
	    SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
	    		sslContext,
	            new String[] { "TLSv1.3", "TLSv1.2", "TLSv1.1" }, 
	            null, 
	            SSLConnectionSocketFactory.getDefaultHostnameVerifier());
	    return HttpClients.custom()
	            .setSSLSocketFactory(sslConnectionSocketFactory)
	            .build();
	}
	
	@Bean
	public RestTemplate restTemplate() throws Exception {
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory(getHttpClient()));
	}
}
