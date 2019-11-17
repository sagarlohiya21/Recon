package com.comviva.reconciliation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ReconciliationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReconciliationApplication.class);

	}
}
