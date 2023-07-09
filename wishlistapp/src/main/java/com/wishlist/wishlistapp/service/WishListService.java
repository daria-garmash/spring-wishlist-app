package com.wishlist.wishlistapp.service;

import com.wishlist.wishlistapp.model.AppUser;
import com.wishlist.wishlistapp.model.Wish;
import com.wishlist.wishlistapp.model.WishList;

import java.util.List;

public interface WishListService {

    List<WishList> getUserWishList(AppUser user);
    WishList getUserWishListById(AppUser user, WishList wishList);
    List<Wish> getWishesInList(AppUser user, WishList wishList);

    WishList createWishList(AppUser user);
    Wish createNewWish(AppUser user, WishList wishList);
     void deleteWishList(AppUser user, WishList wishList);
}
