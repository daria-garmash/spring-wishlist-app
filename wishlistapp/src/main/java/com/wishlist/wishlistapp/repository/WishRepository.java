package com.wishlist.wishlistapp.repository;

import com.wishlist.wishlistapp.model.AppUser;
import com.wishlist.wishlistapp.model.Category;
import com.wishlist.wishlistapp.model.Wish;
import com.wishlist.wishlistapp.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WishRepository extends JpaRepository<Wish, UUID> {
    Optional<Wish> findById(UUID id);

    @Query(value="select w from Wish w where w.category = :category")
    List<Wish> findByCategory(Category category);

    //Integer create(Wish wish);

//    Integer create(String name,
//                   String image,
//                   String description,
//                   String link,
//                   Category category);


}