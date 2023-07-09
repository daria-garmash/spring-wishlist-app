package com.wishlist.wishlistapp.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "wish_list")
public class WishList {
    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String name;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private AppUser user;

    @OneToMany(mappedBy = "list")
    private Set<WishInList> wishes;
    public WishList(UUID id) {
        this.id = id;
    }
}
