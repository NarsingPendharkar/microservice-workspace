package org.school.studentms.feignClient;

import org.school.studentms.dto.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component




//use this with eureka server
//@FeignClient(value = "ADDRESSMS", path = "/api/addresses")

//below used when we are not using eureka server
@FeignClient(
	    name = "addressms",                              // logical name of the client
	    url = "http://localhost:8081",                   // direct base URL of the Address microservice
	    path = "/api/addresses"                          // common API path prefix
	)
public interface AddressFeignClient {

    @GetMapping("/{id}")
    public AddressDTO getById(@PathVariable Long id);
}
