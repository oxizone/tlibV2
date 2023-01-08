package com.ufr.tlib.dataManagementServices;

import com.ufr.tlib.excepetions.PrestationNotFound;
import com.ufr.tlib.models.Prestation;

public interface IPrestationService {

    void getPrestationById(Long id) throws PrestationNotFound;
    void addPrestation(Prestation prestation);
    void updatePrestation(Prestation prestation) throws PrestationNotFound;
    void deletePrestation(Prestation prestation) throws PrestationNotFound;
    void deletePrestationById(Long id) throws PrestationNotFound;
}
