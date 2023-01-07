package com.ufr.tlib.dataManagementServices.implementation;

import com.ufr.tlib.dataManagementServices.IArtisanService;
import com.ufr.tlib.excepetions.ArtisanNotFound;
import com.ufr.tlib.models.Artisan;
import com.ufr.tlib.repository.IArtisanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtisanService implements IArtisanService {
    @Autowired
    private IArtisanDao artisanDao;

    @Override
    public void addArtisan(Artisan artisan) {
        artisanDao.save(artisan);
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
    public void deleteArtisan(Artisan artisan) throws ArtisanNotFound {
        try{
            artisanDao.delete(artisan);
        }catch (Exception ex){
            throw new ArtisanNotFound();
        }
    }

    @Override
    public void deleteArtisanById(Long id) throws ArtisanNotFound {
        try{
            artisanDao.deleteById(id);
        }catch (Exception ex){
            throw new ArtisanNotFound();
        }
    }


}
