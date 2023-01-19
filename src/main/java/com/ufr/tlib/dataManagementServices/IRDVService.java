package com.ufr.tlib.dataManagementServices;

import com.ufr.tlib.excepetions.RDVNotFound;
import com.ufr.tlib.models.RDV;

public interface IRDVService {

    void getRDVById(Long id) throws RDVNotFound;
    void addRDV(RDV rdv);
    void updateRDV(RDV rdv) throws RDVNotFound;
    void deleteRDV(RDV rdv) throws RDVNotFound;
    void deleteRDVById(Long id) throws RDVNotFound;
}
