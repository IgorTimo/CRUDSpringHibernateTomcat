package com.timofeenko.dao;

import com.timofeenko.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    void saveUser(User user);
    User getUserById(int id);
    void deleteUserById(int id);

}
