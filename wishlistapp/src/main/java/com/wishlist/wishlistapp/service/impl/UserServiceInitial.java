package com.wishlist.wishlistapp.service.impl;

import com.wishlist.wishlistapp.dao.UserDao;
import com.wishlist.wishlistapp.dao.impl.PostgresUserDao;
import com.wishlist.wishlistapp.model.AppUser;
import com.wishlist.wishlistapp.repository.UserRepository;
import com.wishlist.wishlistapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.UUID;

public class UserServiceInitial implements UserService {
    @Autowired
    UserRepository repository;

    UserDao dao;

    public UserServiceInitial() {
        this.dao = new PostgresUserDao(repository);
    }

    @Override
    public AppUser getUserById(AppUser user) {
        return dao.getUser(user.getId());
    }

    @Override
    public AppUser createUser(String name, String email) {
        AppUser newUser = new AppUser(UUID.randomUUID());
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setDateJoined(Instant.now());
        return dao.insertUser(newUser);
    }

    @Override
    public void deleteUser(AppUser user) {
        dao.deleteUser(user.getId());

    }
}
