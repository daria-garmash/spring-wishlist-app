package com.wishlist.wishlistapp.repository;

import com.wishlist.wishlistapp.model.WishInList;
import com.wishlist.wishlistapp.model.WishInListKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WishInListRepository extends JpaRepository<WishInList, WishInListKey> {
    List<WishInList> findByIdListId(UUID id);

}
