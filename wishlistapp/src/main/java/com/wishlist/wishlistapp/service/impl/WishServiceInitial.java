package com.wishlist.wishlistapp.service.impl;

import com.wishlist.wishlistapp.dao.WishDao;
import com.wishlist.wishlistapp.exceptions.WishNotFoundException;
import com.wishlist.wishlistapp.model.Wish;
import com.wishlist.wishlistapp.model.requests.CreateWishRequest;
import com.wishlist.wishlistapp.service.WishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Slf4j
public class WishServiceInitial implements WishService {
    @Autowired
    WishDao dao;

    @Override
    public Wish getWishById(UUID id) {
        return dao.getWishById(id);
    }

   /* @Override
    public Wish createWish(CreateWishRequest wish, UUID listId) {
        Wish newWish = new Wish(UUID.randomUUID());
        newWish.setName(wish.getName());
        newWish.setDescription(wish.getDescription());
        newWish.setImage(wish.getImage());
        newWish.setLink(wish.getLink());
        newWish.setAddedAt(Instant.now());

        dao.insertWish(newWish);
        return null;
    }*/

   /* @Override
    public void deleteWish(Wish toDelete) throws WishNotFoundException {
        if (toDelete == null) {
            throw new WishNotFoundException("Wish is of null value");

        }
        log.info(String.format("Deleting wish %s", toDelete.getId().toString()));

        dao.deleteWishById(toDelete.getId());

        log.info(String.format("Deletion of %s was successful", toDelete.getId().toString()));
    }*/
}
