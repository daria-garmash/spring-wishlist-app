package com.wishlist.wishlistapp.service.impl;

import com.wishlist.wishlistapp.dao.WishListDao;
import com.wishlist.wishlistapp.model.AppUser;
import com.wishlist.wishlistapp.model.Wish;
import com.wishlist.wishlistapp.model.WishList;
import com.wishlist.wishlistapp.model.requests.CreateWishListRequest;
import com.wishlist.wishlistapp.service.WishListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class WishListServiceInitial implements WishListService {

//    @Autowired
//    WishListRepository listRepository;
//
//    @Autowired
//    WishInListRepository wishRepository;

    @Autowired
    WishListDao dao;

    @Override
    public List<WishList> getUserWishList(AppUser user) {
        return dao.getAllWishLists(user);
    }

    @Override
    public WishList getUserWishListById(AppUser user, UUID wishListId) {
        return dao.getWishListById(user, wishListId);
    }

    @Override
    public List<Wish> getWishesInList(AppUser user, WishList wishList) {
        return null;
    }

    @Override
    @Transactional
    public WishList createWishList(AppUser user, CreateWishListRequest request) {
        log.info("Creating new wish list in service");

        if(user == null) {
            log.error("User not found");
        }
        WishList newList = new WishList(UUID.randomUUID());

        newList.setName(request.getName());
        newList.setDescription(request.getDescription());
        newList.setCreatedAt(Instant.now());
        newList.setUser(user);
        log.info(newList.toString());
        return dao.insertList(newList);
    }

    @Override
    public Wish createNewWish(AppUser user, WishList wishList) {
        return null;
    }

    @Override
    @Transactional
    public void deleteWishList(WishList wishList) {
       // WishList toDelete = getUserWishListById(user, wishList.getId());

       if (wishList != null) {
           log.info(String.format("Deleting %s", wishList.getId().toString()));
           dao.deleteListById(wishList.getId());
       }
    }
}
