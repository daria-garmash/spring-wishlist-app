package com.wishlist.wishlistapp.model.requests;

import lombok.Data;

@Data
public class CreateWishRequest {
    String name;
    String description;
    String link;
    String image;
    String category;

}
