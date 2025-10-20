package org.school.addressms.controller;


import java.util.List;

import org.school.addressms.entity.Address;
import org.school.addressms.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService service;

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody Address dto) {
    	System.out.println(dto);
        return ResponseEntity.ok(service.createAddress(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAddressById(id));
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAll() {
        return ResponseEntity.ok(service.getAllAddresses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody Address dto) {
        return ResponseEntity.ok(service.updateAddress(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteAddress(id);
        return ResponseEntity.ok("Address deleted successfully!");
    }
}
