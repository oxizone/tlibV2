package com.ufr.tlib.repository;

import com.ufr.tlib.models.Absence;
import com.ufr.tlib.models.Artisan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IAbsenceDao extends JpaRepository<Absence, Long> {

    @Query(value = "select a from Absence a where a.artisan.id = ?1 and ((?2 >= a.startDate and ?2 < a.endDate) or (?3 > a.startDate and ?3 <= a.endDate) or (?2 < a.startDate and ?3 > a.endDate) )")
    List<Absence> findAbsenceInIntersectionalPeriod(Long id, Date start, Date end);
}
