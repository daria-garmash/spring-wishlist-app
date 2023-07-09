package com.wishlist.wishlistapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WishInList {

    @EmbeddedId
    private WishInListKey id;

    @ManyToOne
    @MapsId("wishId")
    @JoinColumn(name = "wish_id")
    private Wish wish;

    @ManyToOne
    @MapsId("listId")
    @JoinColumn(name = "list_id")
    private WishList list;

    public WishInList(WishInListKey id) {
        this.id = id;
    }
}
