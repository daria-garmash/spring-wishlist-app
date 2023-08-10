package com.wishlist.wishlistapp.dao.impl;

import com.wishlist.wishlistapp.dao.WishListDao;
import com.wishlist.wishlistapp.model.AppUser;
import com.wishlist.wishlistapp.model.WishList;
import com.wishlist.wishlistapp.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PostgresWishListDao implements WishListDao {

    @Autowired
    private WishListRepository repo;


    @Override
    public WishList insertList(WishList list) {
        return repo.save(list);
    }

    @Override
    public List<WishList> getAllWishLists(AppUser user) {
        return repo.findByUser(user);
    }

    @Override
    public void deleteListById(UUID listId) {
        repo.deleteById(listId);
    }

    @Override
    public WishList getWishListById(AppUser user, UUID listId) {
        return this.repo.findById(listId).orElse(null);
    }
}
