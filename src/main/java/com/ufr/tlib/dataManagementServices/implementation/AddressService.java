package com.ufr.tlib.dataManagementServices.implementation;

import com.ufr.tlib.dataManagementServices.IAddressService;
import com.ufr.tlib.excepetions.AddressNotFound;
import com.ufr.tlib.excepetions.RDVNotFound;
import com.ufr.tlib.models.Address;
import com.ufr.tlib.repository.IAddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private IAddressDao addressDao;


    @Override
    public void getAddressById(Long id) throws AddressNotFound {
        addressDao.findById(id).orElseThrow(AddressNotFound::new);
    }

    @Override
    public void addAddress(Address address) {
        addressDao.save(address);
    }

    @Override
    public void updateAddress(Address address) throws AddressNotFound {
        addressDao.save(address);
    }

    @Override
    public void deleteAddress(Address address) throws AddressNotFound {
        try{
            addressDao.delete(address);
        }catch (Exception ex){
            throw new AddressNotFound();
        }
    }

    @Override
    public void deleteAddressById(Long id) throws AddressNotFound {
        try{
            addressDao.deleteById(id);
        }catch (Exception ex){
            throw new AddressNotFound();
        }
    }
}
