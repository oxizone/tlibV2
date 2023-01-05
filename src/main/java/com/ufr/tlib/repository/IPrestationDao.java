package com.ufr.tlib.repository;

import com.ufr.tlib.models.Prestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrestationDao extends JpaRepository<Prestation, Long> {
}
