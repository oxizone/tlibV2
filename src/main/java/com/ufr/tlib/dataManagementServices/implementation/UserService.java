package com.ufr.tlib.dataManagementServices.implementation;

import com.ufr.tlib.dataManagementServices.IUserService;
import com.ufr.tlib.excepetions.UserNotFoundException;
import com.ufr.tlib.models.User;
import com.ufr.tlib.repository.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    public static final String ROLE_USER = "ROLE_USER";
    @Autowired
    private IUserDao userDao;

    @Override
    public void addUser(User user) throws DataIntegrityViolationException {
        userDao.save(user);
    }

    @Override
    public void updateProfil(User olduser) throws UserNotFoundException {

    }

    @Override
    public void updateUser(User user) throws DataIntegrityViolationException {
        userDao.save(user);
    }

    @Override
    public void deleteUserById(int id) throws UserNotFoundException {
        User user = this.getUserById(id);
        userDao.delete(user);
    }

    @Override
    public User getUserById(long userId) throws UserNotFoundException {
        return userDao.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserByUserName(String username) throws UserNotFoundException {
        return userDao.getUserByUsername(username);
    }

    @Override
    public boolean isUsernameExists(User user) {
        return userDao.getUserByUsername(user.getUsername()) != null;
    }
}
