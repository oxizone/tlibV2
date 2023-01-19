package com.ufr.tlib.dataManagementServices;

import com.ufr.tlib.excepetions.ArtisanNotFound;
import com.ufr.tlib.models.Artisan;

import java.util.List;

public interface IArtisanService {
    Artisan addArtisan(Artisan artisan);
    Artisan getArtisanById(Long id) throws ArtisanNotFound;

    void updateArtisan(Artisan artisan);
    void deleteArtisan(Artisan artisan) throws ArtisanNotFound;
    void deleteArtisanById(Long id) throws ArtisanNotFound;
}
