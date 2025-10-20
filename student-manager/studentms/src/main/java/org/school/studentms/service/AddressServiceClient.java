package org.school.studentms.service;

import org.school.studentms.dto.AddressDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AddressServiceClient {

    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;

    public AddressServiceClient(RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
    }

    // 🔹 Using RestTemplate
    public AddressDTO getAddressByIdRest(Long id) {
        return restTemplate.getForObject("http://addressms/api/addresses/" + id, AddressDTO.class);
    }

    // 🔹 Using WebClient
    public AddressDTO getAddressByIdWeb(Long id) {
        return webClientBuilder.build()
                .get()
                .uri("http://addressms/api/addresses/{id}", id)
                .retrieve()
                .bodyToMono(AddressDTO.class)
                .block();
    }
}
