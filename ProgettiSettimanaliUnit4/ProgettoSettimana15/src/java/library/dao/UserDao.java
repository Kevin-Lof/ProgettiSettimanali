package it.epicode.library.dao;

import it.epicode.library.entities.User;

public interface UserDao {
    void addUser(User user);


    User findUserByTessera(int tesseraNumber);
}
