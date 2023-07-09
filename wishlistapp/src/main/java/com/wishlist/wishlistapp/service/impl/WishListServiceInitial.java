package com.wishlist.wishlistapp.service.impl;

import com.wishlist.wishlistapp.dao.impl.PostgresWishListDao;
import com.wishlist.wishlistapp.dao.WishListDao;
import com.wishlist.wishlistapp.model.AppUser;
import com.wishlist.wishlistapp.model.Wish;
import com.wishlist.wishlistapp.model.WishList;
import com.wishlist.wishlistapp.repository.WishListRepository;
import com.wishlist.wishlistapp.service.WishListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class WishListServiceInitial implements WishListService {

    @Autowired
    WishListRepository listRepository;
//
//    @Autowired
//    WishInListRepository wishRepository;

    WishListDao dao = new PostgresWishListDao(listRepository);

    @Override
    public List<WishList> getUserWishList(AppUser user) {
        return dao.getAllWishLists(user);
    }

    @Override
    public WishList getUserWishListById(AppUser user, WishList wishList) {
        return dao.getWishListById(user, wishList.getId());
    }

    @Override
    public List<Wish> getWishesInList(AppUser user, WishList wishList) {
        return null;
    }

    @Override
    public WishList createWishList(AppUser user) {
        log.info("Creating new wish list in service");
        WishList newList = new WishList(UUID.randomUUID());
        return dao.insertList(newList);
    }

    @Override
    public Wish createNewWish(AppUser user, WishList wishList) {
        return null;
    }

    @Override
    public void deleteWishList(AppUser user, WishList wishList) {
        WishList toDelete = getUserWishListById(user, wishList);

       if (toDelete != null) {
           log.info(String.format("Deleting %s"), wishList.getId().toString());
           dao.deleteListById(user, toDelete.getId());
       }
    }
}
