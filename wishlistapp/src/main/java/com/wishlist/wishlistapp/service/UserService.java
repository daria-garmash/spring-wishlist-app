package com.wishlist.wishlistapp.service;

import com.wishlist.wishlistapp.model.AppUser;

public interface UserService {
    AppUser getUserById(AppUser user);
    AppUser createUser(String name, String email);
    void deleteUser(AppUser user);
}
