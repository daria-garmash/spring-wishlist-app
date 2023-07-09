package com.wishlist.wishlistapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "app_user")
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<WishList> wishLists;

    public AppUser(UUID id) {
        this.id = id;
    }
}
