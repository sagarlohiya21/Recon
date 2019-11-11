package com.comviva.reconciliation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ReconciliationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReconciliationApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		/*
		  HttpClient httpClient = HttpClientBuilder.create().build();
		  HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		  requestFactory.setConnectTimeout(1000);
		  return new RestTemplate(requestFactory);
		 */
		return new RestTemplate();
	}

}
