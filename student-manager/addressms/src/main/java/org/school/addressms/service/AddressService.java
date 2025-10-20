package org.school.addressms.service;

import java.util.List;

import org.school.addressms.entity.Address;

public interface AddressService {
    Address createAddress(Address dto);
    Address getAddressById(Long id);
    List<Address> getAllAddresses();
    Address updateAddress(Long id, Address dto);
    void deleteAddress(Long id);
}
