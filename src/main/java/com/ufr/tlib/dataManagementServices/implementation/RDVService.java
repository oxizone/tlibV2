package com.ufr.tlib.dataManagementServices.implementation;

import com.ufr.tlib.dataManagementServices.IRDVService;
import com.ufr.tlib.excepetions.RDVNotFound;
import com.ufr.tlib.models.RDV;
import com.ufr.tlib.repository.IRDVDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RDVService implements IRDVService {

    @Autowired
    private IRDVDao rdvDao;

    @Override
    public void getRDVById(Long id) throws RDVNotFound {
        rdvDao.findById(id).orElseThrow(RDVNotFound::new);
    }

    @Override
    public void addRDV(RDV rdv) {
        rdvDao.save(rdv);
    }

    @Override
    public void updateRDV(RDV rdv) throws RDVNotFound {
        rdvDao.save(rdv);
    }

    @Override
    public void deleteRDV(RDV rdv) throws RDVNotFound {
        try{
            rdvDao.delete(rdv);
        }catch (Exception ex){
            throw new RDVNotFound();
        }
    }

    @Override
    public void deleteRDVById(Long id) throws RDVNotFound {
        try{
            rdvDao.deleteById(id);
        }catch (Exception ex){
            throw new RDVNotFound();
        }
    }
}
