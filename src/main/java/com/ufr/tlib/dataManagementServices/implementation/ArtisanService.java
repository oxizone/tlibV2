package com.ufr.tlib.dataManagementServices.implementation;

import com.ufr.tlib.dataManagementServices.IArtisanService;
import com.ufr.tlib.excepetions.ArtisanNotFound;
import com.ufr.tlib.models.Artisan;
import com.ufr.tlib.repository.IArtisanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtisanService implements IArtisanService {
    @Autowired
    private IArtisanDao artisanDao;

    @Override
    public Artisan addArtisan(Artisan artisan) {
       return artisanDao.save(artisan);
    }

    @Override
    public Artisan getArtisanById(Long id) throws ArtisanNotFound {
        return artisanDao.findById(id).orElseThrow(ArtisanNotFound::new);
    }

    @Override
    public void updateArtisan(Artisan artisan) {
        artisanDao.save(artisan);
    }

    @Override
    public void deleteArtisan(Artisan artisan) throws ArtisanNotFound, DataIntegrityViolationException {
        try{
            artisanDao.delete(artisan);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException(ex.getMessage());
        }
    }

    @Override
    public void deleteArtisanById(Long id) throws ArtisanNotFound, DataIntegrityViolationException {
        try{
            artisanDao.deleteById(id);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException(ex.getMessage());
        }
    }


}
