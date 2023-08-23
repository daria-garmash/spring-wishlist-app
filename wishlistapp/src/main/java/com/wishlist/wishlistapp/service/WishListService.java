package com.wishlist.wishlistapp.service;

import com.wishlist.wishlistapp.exceptions.UserNotFoundException;
import com.wishlist.wishlistapp.exceptions.WishListNotFoundException;
import com.wishlist.wishlistapp.exceptions.WishNotFoundException;
import com.wishlist.wishlistapp.model.AppUser;
import com.wishlist.wishlistapp.model.Wish;
import com.wishlist.wishlistapp.model.WishList;
import com.wishlist.wishlistapp.model.requests.CreateWishListRequest;
import com.wishlist.wishlistapp.model.requests.CreateWishRequest;

import java.util.List;
import java.util.UUID;

public interface WishListService {

    List<WishList> getUserWishList(AppUser user);
    WishList getUserWishListById(AppUser user, UUID wishList);
    List<Wish> getWishesInList(AppUser user, WishList wishList);

    WishList createWishList(AppUser user, CreateWishListRequest request) throws UserNotFoundException;

    Wish createNewWish(WishList wishList, CreateWishRequest wish) throws WishListNotFoundException;

    void deleteWishList(WishList wishList);
    void deleteWishFromList(WishList wishList, Wish toDelete) throws WishNotFoundException, WishListNotFoundException;
}
