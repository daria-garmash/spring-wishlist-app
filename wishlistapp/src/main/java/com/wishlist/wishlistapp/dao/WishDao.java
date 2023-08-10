package com.wishlist.wishlistapp.dao;

import com.wishlist.wishlistapp.model.Wish;
import com.wishlist.wishlistapp.model.WishList;

import java.util.List;
import java.util.UUID;

public interface WishDao {

    Wish insertWish(Wish wish);

    List<Wish> getAllWishes(WishList wishList);

    Wish getWishById(UUID id);

    void deleteWishById(UUID id);
}
