package com.ufr.tlib.repository;

import com.ufr.tlib.models.Local;
import com.ufr.tlib.models.Service;
import com.ufr.tlib.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILocalDao extends JpaRepository<Local, Long> {

    public List<Local> getLocalByManager(User manager);
    public Page<Local> findLocalByNameContains(String keyword, Pageable page);
    public Page<Local> findLocalByNameContainsAndService(String keyword, Service service, Pageable page);
    public Page<Local> findLocalByNameContainsAndAdresse_City(String keyword,String city, Pageable page);
    public Page<Local> findLocalByServiceAndAdresse_City(Service service,String city, Pageable page);
    public Page<Local> findLocalByNameContainsAndServiceAndAdresse_City(String keyword,Service service,String city, Pageable page);
    public Page<Local> findLocalByAdresse_City(String city, Pageable page);
    public Page<Local> findLocalByService(Service service, Pageable page);



}
