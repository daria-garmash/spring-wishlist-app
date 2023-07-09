package com.wishlist.wishlistapp.dao.impl;

import com.wishlist.wishlistapp.dao.WishListDao;
import com.wishlist.wishlistapp.model.AppUser;
import com.wishlist.wishlistapp.model.WishList;
import com.wishlist.wishlistapp.repository.WishListRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PostgresWishListDao implements WishListDao {

    private final WishListRepository repo;

    public PostgresWishListDao(WishListRepository repo) {
        this.repo = repo;
    }

    @Override
    public WishList insertList(WishList list) {
        return repo.save(list);
    }

    @Override
    public List<WishList> getAllWishLists(AppUser user) {
        return repo.findByUser(user);
    }

    @Override
    public void deleteListById(AppUser user, UUID listId) {
        repo.deleteById(listId, user);
    }

    @Override
    public WishList getWishListById(AppUser user, UUID listId) {
        return this.repo.findById(listId).get();
    }
}
