package com.ufr.tlib.repository;

import com.ufr.tlib.models.Artisan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArtisanDao extends JpaRepository<Artisan, Long> {
}
