package com.wishlist.wishlistapp.model.requests;

import lombok.Data;

@Data
public class CreateWishListRequest {
    private String name;
    private String description;

}
