package com.ufr.tlib.dataManagementServices.implementation;

import com.ufr.tlib.excepetions.AbsenceNotFound;
import com.ufr.tlib.models.Absence;

public interface IAbsenceService {

    void getAbsenceById(Long id) throws AbsenceNotFound;
    int addAbsence(Absence absence);
    void updateAbsence(Absence absence) throws AbsenceNotFound;
    void deleteAbsence(Absence absence) throws AbsenceNotFound;
    void deleteAbsenceById(Long id) throws AbsenceNotFound;
}
