package com.ufr.tlib.repository;

import com.ufr.tlib.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressDao extends JpaRepository<Address, Long> {
}
