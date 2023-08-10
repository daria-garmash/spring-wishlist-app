package com.wishlist.wishlistapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "wish_list")
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnore
    private AppUser user;

    @OneToMany(mappedBy = "list")
    private Set<WishInList> wishes;

    public WishList(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WishList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", wishes=" + wishes +
                '}';
    }
}
