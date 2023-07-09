package com.wishlist.wishlistapp.repository;

import com.wishlist.wishlistapp.model.AppUser;
import com.wishlist.wishlistapp.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface WishListRepository extends JpaRepository<WishList, UUID>
{
    List<WishList> findByUser(AppUser user);

    @Query(value="delete from WishList w where w.id = :listId and w.user = :user")
    void deleteById(UUID listId, AppUser user);
}
