package com.ufr.tlib.dataManagementServices.implementation;

import com.ufr.tlib.excepetions.AbsenceNotFound;
import com.ufr.tlib.excepetions.AddressNotFound;
import com.ufr.tlib.models.Absence;
import com.ufr.tlib.repository.IAbsenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceService implements IAbsenceService{

    @Autowired
    private IAbsenceDao absenceDao;


    @Override
    public void getAbsenceById(Long id) throws AbsenceNotFound {
        absenceDao.findById(id).orElseThrow(AbsenceNotFound::new);
    }

    @Override
    public int addAbsence(Absence absence) {
        List<Absence> absences = absenceDao.findAbsenceInIntersectionalPeriod(absence.getArtisan().getId(), absence.getStartDate(), absence.getEndDate());
        if(absences.size() == 0)
        {
            absenceDao.save(absence);
            return 1;
        }
        return -1;

    }

    @Override
    public void updateAbsence(Absence absence) throws AbsenceNotFound {
        absenceDao.save(absence);
    }

    @Override
    public void deleteAbsence(Absence absence) throws AbsenceNotFound {
        try{
            absenceDao.delete(absence);
        }catch (Exception ex){
            throw new AbsenceNotFound();
        }
    }

    @Override
    public void deleteAbsenceById(Long id) throws AbsenceNotFound {
        try{
            absenceDao.deleteById(id);
        }catch (Exception ex){
            throw new AbsenceNotFound();
        }
    }
}
