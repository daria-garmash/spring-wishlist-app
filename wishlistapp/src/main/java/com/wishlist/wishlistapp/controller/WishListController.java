package com.wishlist.wishlistapp.controller;

import com.wishlist.wishlistapp.model.AppUser;
import com.wishlist.wishlistapp.model.requests.CreateUserRequest;
import com.wishlist.wishlistapp.model.WishList;
import com.wishlist.wishlistapp.model.requests.CreateWishListRequest;
import com.wishlist.wishlistapp.service.UserService;
import com.wishlist.wishlistapp.service.WishListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/wishes")
public class WishListController {
    private final WishListService service;
    private final UserService userService;

    public WishListController(WishListService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/new-user")
    public AppUser createUser(@RequestBody CreateUserRequest request){
        return userService.createUser(request.getName(), request.getEmail());
    }
    @DeleteMapping("/delete-user")
    public AppUser deleteUser(@RequestHeader("userId") UUID userId) {
        AppUser user = userService.getUserById(userId);
        userService.deleteUser(user);

        return user;
    }

    @GetMapping("/my-lists")
    public List<WishList> getAllUserWishLists(@RequestHeader("userId") UUID userId) {
        AppUser user = userService.getUserById(userId);
        return service.getUserWishList(user);
    }

    @PostMapping("/new-list")
    public WishList createNewWishList(@RequestHeader("userId") UUID userId, @RequestBody CreateWishListRequest request) {
        AppUser user = userService.getUserById(userId);

        return service.createWishList(user, request);
    }

    @DeleteMapping("/{id}/delete")
    public WishList deleteWishListById(@RequestHeader("userId") UUID userId, @PathVariable String id) {
        UUID listId = UUID.fromString(id);
        AppUser user = userService.getUserById(userId);
        WishList toDelete = service.getUserWishListById(user, listId);
        service.deleteWishList(toDelete);

        return toDelete;
    }


}
