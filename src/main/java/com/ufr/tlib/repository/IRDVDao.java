package com.ufr.tlib.repository;

import com.ufr.tlib.models.RDV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRDVDao extends JpaRepository<RDV, Long> {
}
