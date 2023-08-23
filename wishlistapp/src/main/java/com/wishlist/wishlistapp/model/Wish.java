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
@Table(name = "wish")
@NoArgsConstructor
@AllArgsConstructor
public class Wish {

    @Id
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
    @JsonIgnore
    private Set<WishInList> wishInLists;

    public Wish(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Wish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", addedAt=" + addedAt +
                ", category=" + category +
                ", wishInLists=" + wishInLists +
                '}';
    }
}
