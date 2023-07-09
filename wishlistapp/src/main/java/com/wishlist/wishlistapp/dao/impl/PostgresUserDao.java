package com.wishlist.wishlistapp.dao.impl;

import com.wishlist.wishlistapp.dao.UserDao;
import com.wishlist.wishlistapp.model.AppUser;
import com.wishlist.wishlistapp.repository.UserRepository;

import java.util.UUID;

public class PostgresUserDao implements UserDao {

    private final UserRepository repo;

    public PostgresUserDao(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public AppUser insertUser(AppUser user) {
        return repo.save(user);
    }

    @Override
    public AppUser getUser(UUID id) {
        return repo.findById(id).get();
    }

    @Override
    public void deleteUser(UUID id) {
        repo.deleteById(id);
    }
}
