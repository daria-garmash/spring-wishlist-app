package com.wishlist.wishlistapp.service;

import com.wishlist.wishlistapp.model.AppUser;
import com.wishlist.wishlistapp.model.Wish;
import com.wishlist.wishlistapp.model.WishList;
import com.wishlist.wishlistapp.model.requests.CreateWishListRequest;

import java.util.List;
import java.util.UUID;

public interface WishListService {

    List<WishList> getUserWishList(AppUser user);
    WishList getUserWishListById(AppUser user, UUID wishList);
    List<Wish> getWishesInList(AppUser user, WishList wishList);

    WishList createWishList(AppUser user, CreateWishListRequest request);
    Wish createNewWish(AppUser user, WishList wishList);
     void deleteWishList(WishList wishList);
}
