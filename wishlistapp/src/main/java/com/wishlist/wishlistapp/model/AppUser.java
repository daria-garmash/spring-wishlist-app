package com.wishlist.wishlistapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "app_user")
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String name;

    @Column(name = "date_joined")
    private Instant dateJoined;

    @Column
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    private List<WishList> wishLists;

    public AppUser(UUID id) {
        this.id = id;
    }

    public void addNewWishList(WishList list) {
        if (this.wishLists == null) {
            this.wishLists = new ArrayList<>();
        }

        this.wishLists.add(list);
    }
}
