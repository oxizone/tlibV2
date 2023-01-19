package com.ufr.tlib.dataManagementServices;

import com.ufr.tlib.excepetions.UserNotFoundException;
import com.ufr.tlib.models.Local;
import com.ufr.tlib.models.User;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public interface IUserService {

    public void addUser(User user) throws DataIntegrityViolationException;
    public void updateUser(User user) throws DataIntegrityViolationException;
    public void updateProfil(User user) throws UserNotFoundException;
    public void deleteUserById(int id) throws UserNotFoundException;
    public User getUserById(long id) throws UserNotFoundException;
    public User getUserByUserName(String username) throws UserNotFoundException;
    public boolean isUsernameExists(User user);
    public List<User> getListUsers();
    public void enableUser(long id) throws UserNotFoundException;
    public void disableUser(long id) throws UserNotFoundException;
}


