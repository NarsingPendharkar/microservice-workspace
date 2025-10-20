package org.school.addressms.service;


import java.util.List;
import java.util.stream.Collectors;

import org.school.addressms.entity.Address;
import org.school.addressms.entity.AddressDTO;
import org.school.addressms.exception.ResourceNotFoundException;
import org.school.addressms.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository repository;

    @Override
    public AddressDTO createAddress(AddressDTO dto) {
        Address address = mapToEntity(dto);
        Address saved = repository.save(address);
        return mapToDTO(saved);
    }

    @Override
    public AddressDTO getAddressById(Long id) {
        Address address = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));
        return mapToDTO(address);
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO updateAddress(Long id, AddressDTO dto) {
        Address existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));

        existing.setStreet(dto.getStreet());
        existing.setCity(dto.getCity());
        existing.setState(dto.getState());
        existing.setCountry(dto.getCountry());
        existing.setZipCode(dto.getZipCode());

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
    private Address mapToEntity(AddressDTO dto) {
        return Address.builder()
                .id(dto.getId())
                .street(dto.getStreet())
                .city(dto.getCity())
                .state(dto.getState())
                .country(dto.getCountry())
                .zipCode(dto.getZipCode())
                .build();
    }

    private AddressDTO mapToDTO(Address entity) {
        return AddressDTO.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .city(entity.getCity())
                .state(entity.getState())
                .country(entity.getCountry())
                .zipCode(entity.getZipCode())
                .build();
    }
}
