package com.ufr.tlib.dataManagementServices.implementation;

import com.ufr.tlib.dataManagementServices.IPrestationService;
import com.ufr.tlib.excepetions.PrestationNotFound;
import com.ufr.tlib.models.Prestation;
import com.ufr.tlib.repository.IPrestationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestationService implements IPrestationService {

    @Autowired
    private IPrestationDao prestationDao;

    @Override
    public Prestation getPrestationById(Long id) throws PrestationNotFound {
        return prestationDao.findById(id).orElseThrow(PrestationNotFound::new);
    }

    @Override
    public void addPrestation(Prestation prestation) {
        prestationDao.save(prestation);
    }

    @Override
    public void updatePrestation(Prestation prestation) throws PrestationNotFound {
        prestationDao.save(prestation);
    }

    @Override
    public void deletePrestation(Prestation prestation) throws PrestationNotFound {
        try {
            prestationDao.delete(prestation);
        }catch (Exception ex){
            throw new PrestationNotFound();
        }

    }

    @Override
    public void deletePrestationById(Long id) throws PrestationNotFound {
        try {
            prestationDao.deleteById(id);
        }catch (Exception ex){
            throw new PrestationNotFound();
        }

    }

    @Override
    public void save(Prestation prestation) {
        prestationDao.save(prestation);
    }
}
