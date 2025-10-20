package org.school.addressms.service;


import java.util.List;
import java.util.stream.Collectors;

import org.school.addressms.entity.Address;
import org.school.addressms.exception.ResourceNotFoundException;
import org.school.addressms.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository repository;

    @Override
    public Address createAddress(Address dto) {
        Address address = mapToEntity(dto);
        Address saved = repository.save(address);
        return mapToDTO(saved);
    }

    @Override
    public Address getAddressById(Long id) {
        Address address = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));
        return mapToDTO(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Address updateAddress(Long id, Address dto) {
        Address existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));

        existing.setStreet(dto.getStreet());
        existing.setCity(dto.getCity());
        existing.setState(dto.getState());
        existing.setCountry(dto.getCountry());
        existing.setZipCode(dto.getZipCode());
        existing.setStudentId(dto.getStudentId());

        Address updated = repository.save(existing);
        return mapToDTO(updated);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));
        repository.delete(address);
    }

    // Utility Mappers
    private Address mapToEntity(Address dto) {
        return Address.builder()
                .id(dto.getId())
                .street(dto.getStreet())
                .city(dto.getCity())
                .state(dto.getState())
                .country(dto.getCountry())
                .zipCode(dto.getZipCode())
                .studentId(dto.getStudentId())
                .build();
    }

    private Address mapToDTO(Address entity) {
        return Address.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .city(entity.getCity())
                .state(entity.getState())
                .country(entity.getCountry())
                .zipCode(entity.getZipCode())
                .studentId(entity.getStudentId())
                .build();
    }
}
