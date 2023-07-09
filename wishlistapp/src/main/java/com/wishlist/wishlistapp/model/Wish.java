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
@Table(name = "wish")
public class Wish {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String name;

    @Column
    private String image;

    @Column
    private String description;

    @Column
    private String link;

    @Column(name="added_at")
    private Instant addedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @OneToMany(mappedBy = "wish")
    private Set<WishInList> wishInLists;

    public Wish(UUID id) {
        this.id = id;
    }
}
