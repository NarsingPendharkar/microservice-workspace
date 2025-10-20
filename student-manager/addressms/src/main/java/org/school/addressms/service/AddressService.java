package org.school.addressms.service;

import java.util.List;

import org.school.addressms.entity.AddressDTO;

public interface AddressService {
    AddressDTO createAddress(AddressDTO dto);
    AddressDTO getAddressById(Long id);
    List<AddressDTO> getAllAddresses();
    AddressDTO updateAddress(Long id, AddressDTO dto);
    void deleteAddress(Long id);
}
