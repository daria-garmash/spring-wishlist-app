package com.wishlist.wishlistapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class WishInListKey implements Serializable {

    @Column(name = "wish_id")
    private UUID wishId;

    @Column(name = "list_id")
    private UUID listId;

    public WishInListKey(UUID wishId, UUID listId) {
        this.wishId = wishId;
        this.listId = listId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishInListKey that = (WishInListKey) o;
        return Objects.equals(wishId, that.wishId) && Objects.equals(listId, that.listId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wishId, listId);
    }
}
