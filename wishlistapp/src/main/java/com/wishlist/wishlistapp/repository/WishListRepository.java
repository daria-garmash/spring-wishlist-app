package com.wishlist.wishlistapp.repository;

import com.wishlist.wishlistapp.model.AppUser;
import com.wishlist.wishlistapp.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface WishListRepository extends JpaRepository<WishList, UUID>
{
    List<WishList> findByUser(AppUser user);


    //void deleteById(UUID listId);
}
