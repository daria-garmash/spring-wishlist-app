package com.wishlist.wishlistapp.dao.impl;

import com.wishlist.wishlistapp.dao.WishDao;
import com.wishlist.wishlistapp.model.Wish;
import com.wishlist.wishlistapp.model.WishList;
import com.wishlist.wishlistapp.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PostgresWishDao implements WishDao {

    @Autowired
    private WishRepository repo;

    @Override
    public Wish insertWish(Wish wish) {
        return repo.save(wish);
    }

    @Override
    public List<Wish> getAllWishes(WishList wishList) {
        return null;
    }

    @Override
    public Wish getWishById(UUID id) {
        return repo.findById(id).isPresent() ? repo.findById(id).get() : null;
    }

    @Override
    public void deleteWishById(UUID id) {
        repo.deleteById(id);

    }
}
