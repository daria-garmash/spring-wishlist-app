package com.wishlist.wishlistapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishInList {

    @EmbeddedId
    private WishInListKey id;

    @ManyToOne
    @MapsId("wishId")
    @JoinColumn(name = "wish_id")
    @JsonIgnore
    private Wish wish;

    @ManyToOne
    @MapsId("listId")
    @JoinColumn(name = "list_id")
    @JsonIgnore
    private WishList list;

    public WishInList(WishInListKey id) {
        this.id = id;
    }
}
