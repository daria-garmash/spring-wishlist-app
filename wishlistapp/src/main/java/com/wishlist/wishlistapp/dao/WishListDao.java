package com.wishlist.wishlistapp.dao;

import com.wishlist.wishlistapp.model.AppUser;
import com.wishlist.wishlistapp.model.WishList;

import java.util.List;
import java.util.UUID;

public interface WishListDao {
    WishList insertList(WishList list);

//    default WishList addList(WishList list) {
//        UUID id = UUID.randomUUID();
//        return insertList(id, list);
//    }

    List<WishList> getAllWishLists(AppUser user);
    void deleteListById(AppUser user, UUID listId);

    WishList getWishListById(AppUser user, UUID listId);
}
