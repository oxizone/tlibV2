package com.ufr.tlib.dataManagementServices;

import com.ufr.tlib.excepetions.AddressNotFound;
import com.ufr.tlib.models.Address;

public interface IAddressService {

    void getAddressById(Long id) throws AddressNotFound;
    void addAddress(Address address);
    void updateAddress(Address address) throws AddressNotFound;
    void deleteAddress(Address address) throws AddressNotFound;
    void deleteAddressById(Long id) throws AddressNotFound;
}
