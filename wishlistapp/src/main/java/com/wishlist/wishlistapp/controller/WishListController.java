package com.wishlist.wishlistapp.controller;

import com.wishlist.wishlistapp.exceptions.WishListNotFoundException;
import com.wishlist.wishlistapp.exceptions.WishNotFoundException;
import com.wishlist.wishlistapp.model.AppUser;
import com.wishlist.wishlistapp.model.Wish;
import com.wishlist.wishlistapp.model.requests.CreateUserRequest;
import com.wishlist.wishlistapp.model.WishList;
import com.wishlist.wishlistapp.model.requests.CreateWishListRequest;
import com.wishlist.wishlistapp.model.requests.CreateWishRequest;
import com.wishlist.wishlistapp.service.UserService;
import com.wishlist.wishlistapp.service.WishListService;
import com.wishlist.wishlistapp.service.WishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/wishes")
@Slf4j
public class WishListController {
    private final WishListService service;
    private final UserService userService;

    private final WishService wishService;

    public WishListController(WishListService service,
                              UserService userService,
                              WishService wishService) {
        this.service = service;
        this.userService = userService;
        this.wishService = wishService;
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
    public ResponseEntity createNewWishList(@RequestHeader("userId") UUID userId, @RequestBody CreateWishListRequest request) {
        AppUser user = userService.getUserById(userId);

        WishList created = null;
        try {
            created = service.createWishList(user, request);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}/delete")
    public WishList deleteWishListById(@RequestHeader("userId") UUID userId, @PathVariable String id) {
        UUID listId = UUID.fromString(id);
        AppUser user = userService.getUserById(userId);
        WishList toDelete = service.getUserWishListById(user, listId);
        service.deleteWishList(toDelete);

        return toDelete;
    }

    @PostMapping("/{id}/new-wish")
    public ResponseEntity createWish(@RequestHeader("userId") UUID userId,
                                     @PathVariable String id,
                                     @RequestBody CreateWishRequest request){
        UUID listId = UUID.fromString(id);
        AppUser user = userService.getUserById(userId);
        WishList wishList = service.getUserWishListById(user, listId);
        Wish created = null;

        try {
            created = service.createNewWish(wishList, request);
        } catch (WishListNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}/wishes/{wish}")
    public Wish getWishById(@PathVariable("wish") String id) {
        log.info("ID: "+id);
        UUID wishId = UUID.fromString(id);

        Wish wish = wishService.getWishById(wishId);

        log.info(wish.toString());
        return wish;
    }

    @DeleteMapping("/{id}/wishes/{wish}")
    public ResponseEntity deleteById(@RequestHeader("userId") UUID userId,
                                     @PathVariable("id") String list,
                                     @PathVariable("wish") String id) {
        UUID wishId = UUID.fromString(id);
        UUID listId = UUID.fromString(list);

        AppUser user = userService.getUserById(userId);
        Wish wish = wishService.getWishById(wishId);
        WishList wishList = service.getUserWishListById(user, listId);
        try {
            service.deleteWishFromList(wishList, wish);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }

        return ResponseEntity.ok(wish);
    }
}
