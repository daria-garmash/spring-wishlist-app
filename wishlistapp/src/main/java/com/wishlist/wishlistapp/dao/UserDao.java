package com.wishlist.wishlistapp.dao;

import com.wishlist.wishlistapp.model.AppUser;

import java.util.UUID;

public interface UserDao {

    AppUser insertUser(AppUser user);
    AppUser getUser(UUID user);
    void deleteUser(UUID user);
}
