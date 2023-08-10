package com.wishlist.wishlistapp.dao.impl;

import com.wishlist.wishlistapp.dao.UserDao;
import com.wishlist.wishlistapp.model.AppUser;
import com.wishlist.wishlistapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostgresUserDao implements UserDao {

    @Autowired
    private UserRepository repo;

    @Override
    public AppUser insertUser(AppUser user) {
        return repo.save(user);
    }

    @Override
    public AppUser getUser(UUID id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(UUID id) {
        repo.deleteById(id);
    }
}
