package com.wishlist.wishlistapp.service.impl;

import com.wishlist.wishlistapp.dao.WishDao;
import com.wishlist.wishlistapp.dao.WishListDao;
import com.wishlist.wishlistapp.exceptions.UserNotFoundException;
import com.wishlist.wishlistapp.exceptions.WishListNotFoundException;
import com.wishlist.wishlistapp.exceptions.WishNotFoundException;
import com.wishlist.wishlistapp.model.*;
import com.wishlist.wishlistapp.model.requests.CreateWishListRequest;
import com.wishlist.wishlistapp.model.requests.CreateWishRequest;
import com.wishlist.wishlistapp.repository.WishInListRepository;
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

    @Autowired
    WishDao wishDao;

    @Autowired
    WishInListRepository repo;

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
    public WishList createWishList(AppUser user, CreateWishListRequest request) throws UserNotFoundException {
        log.info("Creating new wish list in service");

        if(user == null) {
            log.error("User not found");
            throw new UserNotFoundException("Null value was passed as user");
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
    public Wish createNewWish(WishList wishList, CreateWishRequest wish) throws WishListNotFoundException {

        if (wishList == null) {
            log.error("Wish list is null or not found");
            throw new WishListNotFoundException("Null value was passed as a wishlist");
        }

        Wish newWish = new Wish(UUID.randomUUID());
        newWish.setName(wish.getName());
        newWish.setDescription(wish.getDescription());
        newWish.setImage(wish.getImage());
        newWish.setLink(wish.getLink());
        newWish.setAddedAt(Instant.now());
        newWish.setCategory(Category.valueOf(wish.getCategory()));

        log.info("Inserting wish: " + newWish);
        wishDao.insertWish(newWish);

        WishInList newWishInList = new WishInList(new WishInListKey(newWish.getId(), wishList.getId()));
        newWishInList.setList(wishList);
        newWishInList.setWish(newWish);
        repo.save(newWishInList);

        return newWish;
    }

    @Override
    @Transactional
    public void deleteWishFromList(WishList wishList, Wish toDelete) throws WishNotFoundException, WishListNotFoundException {
        if (toDelete == null) {
            throw new WishNotFoundException("Wish is of null value");

        }
        if (wishList == null) {
            throw new WishListNotFoundException("Wish list is of null value");
        }

        log.info(String.format("Deleting wish %s from list %s", toDelete.getId().toString(), wishList.getId().toString()));

        WishInListKey key = new WishInListKey(toDelete.getId(), wishList.getId());
        WishInList wishInList = repo.findById(key).orElse(null);

        if (wishInList == null) {
            throw new WishNotFoundException("Wish is not found in this list");
        }

        repo.deleteById(key);
        wishDao.deleteWishById(toDelete.getId());


        log.info(String.format("Deletion of %s was successful", toDelete.getId().toString()));
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
