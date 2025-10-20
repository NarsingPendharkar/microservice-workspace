package org.school.studentms.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class StudentConfig {
	 String addressUrl = "http://localhost:8081/api/addresses" ;
	@Bean
	public RestTemplate getTemplate() {
		return new RestTemplate();
	}
	
	@Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(addressUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
	
	   // ✅ For RestTemplate (Load-balanced for Eureka)
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // ✅ For WebClient (Load-balanced for Eureka)
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }


}
