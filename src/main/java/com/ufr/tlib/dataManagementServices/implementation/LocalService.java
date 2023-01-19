package com.ufr.tlib.dataManagementServices.implementation;

import com.ufr.tlib.dataManagementServices.ILocalService;
import com.ufr.tlib.dataManagementServices.IUserService;
import com.ufr.tlib.excepetions.LocalNotFoundException;
import com.ufr.tlib.excepetions.UserNotFoundException;
import com.ufr.tlib.models.Etat;
import com.ufr.tlib.models.Local;
import com.ufr.tlib.models.User;
import com.ufr.tlib.repository.ILocalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalService implements ILocalService {

    @Autowired
    private ILocalDao localDao;

    @Autowired
    private IUserService userService;

    @Override
    public Local getLocalById(long id) throws LocalNotFoundException {
        return localDao.findById(id).orElseThrow(LocalNotFoundException::new);
    }

    @Override
    public void addLocal(Local local, String username) throws UserNotFoundException {
        User manager = userService.getUserByUserName(username);
        local.setManager(manager);
        local.setEtat(Etat.INWAITING);
        localDao.save(local);
    }

    @Override
    public List<Local> getListLocal() {
        return localDao.findAll();
    }

    @Override
    public Page<Local> getAllLocalPage(int page, int size) {
        return localDao.findAll(PageRequest.of(page,size));
    }

    @Override
    public List<Local> getListEnableLocal() {
        return localDao.findLocalByEtat(Etat.ENABLE);
    }

    @Override
    public List<Local> getListDisableLocal() {
        return localDao.findLocalByEtat(Etat.DISABLE);
    }

    @Override
    public List<Local> getListWaitingLocal() {
        return localDao.findLocalByEtat(Etat.INWAITING);
    }


    @Override
    public Page<Local> getLocalPageByKeyword(String keyword, int page, int size) {
        return localDao.findLocalByNameContains(keyword, PageRequest.of(page,size));
    }

    @Override
    public Page<Local> getLocalPageByKeywordAndServiceType(String keyword, com.ufr.tlib.models.Service service, int page, int size) {
        return localDao.findLocalByNameContainsAndService(keyword,service,PageRequest.of(page,size));
    }

    @Override
    public Page<Local> getLocalPageByKeywordAndServiceTypeAndCity(String keyword, com.ufr.tlib.models.Service service, String city, int page, int size) {
        Page<Local> locauxPage = null;

        //tous est null
        if(keyword.equals("") && city.equals("") && service == null){
            locauxPage = localDao.findAll(PageRequest.of(page,size));
            System.out.println("tous est null");
        }

        //keyword - city - service
        if(!keyword.equals("") && !city.equals("") && service != null){
            locauxPage = localDao.findLocalByNameContainsAndServiceAndAdresse_City(keyword,service,city,PageRequest.of(page,size));
        }

        //keyword - city
        if(!keyword.equals("") && !city.equals("") && service == null){
            locauxPage = localDao.findLocalByNameContainsAndAdresse_City(keyword,city,PageRequest.of(page,size));
        }

        //keyword - service
        if(!keyword.equals("") && city.equals("") && service != null){
            locauxPage = localDao.findLocalByNameContainsAndService(keyword,service,PageRequest.of(page,size));
        }

        //city - service
        if(keyword.equals("") && !city.equals("") && service != null){
            locauxPage = localDao.findLocalByServiceAndAdresse_City(service,city,PageRequest.of(page,size));
        }

        //keyword
        if(!keyword.equals("") && city.equals("") && service == null){
            locauxPage = localDao.findLocalByNameContains(keyword,PageRequest.of(page,size));
        }

        //city
        if(keyword.equals("") && !city.equals("") && service == null){
            locauxPage = localDao.findLocalByAdresse_City(city,PageRequest.of(page,size));
        }

        //service
        if(keyword.equals("") && city.equals("") && service != null){
            locauxPage = localDao.findLocalByService(service,PageRequest.of(page,size));
        }

       return locauxPage;
    }

    @Override
    public List<Local> getListLocalByManager(String username) throws UserNotFoundException {
        User manager = userService.getUserByUserName(username);
        return localDao.getLocalByManager(manager);
    }

    @Override
    public Local getLocal(long id) {
        return localDao.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void updateLocal(Local local) {
        localDao.save(local);
    }

    @Override
    public void disableLocal(long id) {
        Local local = getLocal(id);
        local.setEtat(Etat.DISABLE);
        localDao.save(local);
    }

    @Override
    public void enableLocal(long id) {
        Local local = getLocal(id);
        local.setEtat(Etat.ENABLE);
        localDao.save(local);
    }

    @Override
    public void deleteLocal(long id) {
        localDao.deleteById(id);
    }

}
