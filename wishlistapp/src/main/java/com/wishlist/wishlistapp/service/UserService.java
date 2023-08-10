package com.wishlist.wishlistapp.service;

import com.wishlist.wishlistapp.model.AppUser;

import java.util.UUID;

public interface UserService {
    AppUser getUserById(UUID user);
    AppUser createUser(String name, String email);
    void deleteUser(AppUser user);
}
