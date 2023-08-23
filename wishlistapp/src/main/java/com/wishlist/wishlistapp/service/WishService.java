package com.wishlist.wishlistapp.service;

import com.wishlist.wishlistapp.exceptions.WishNotFoundException;
import com.wishlist.wishlistapp.model.Wish;
import com.wishlist.wishlistapp.model.requests.CreateWishRequest;

import java.util.UUID;

public interface WishService {
    Wish getWishById(UUID id);
   // Wish createWish(CreateWishRequest wish, UUID listId);
    //void deleteWish(Wish id) throws WishNotFoundException;
}
