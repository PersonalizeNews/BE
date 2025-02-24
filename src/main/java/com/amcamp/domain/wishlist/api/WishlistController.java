package com.amcamp.domain.wishlist.api;

import com.amcamp.domain.wishlist.application.WishlistService;
import com.amcamp.domain.wishlist.domain.Wishlist;
import com.amcamp.domain.wishlist.dto.WishlistRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    private Long getAuthenticatedMemberId(Authentication authentication) {
        if (authentication.getPrincipal() instanceof OidcUser oidcUser) {
            return Long.parseLong(oidcUser.getSubject());
        }
        throw new RuntimeException("Invalid authentication principal");
    }

    @PostMapping
    public ResponseEntity<Wishlist> createWishlist(
            @RequestBody WishlistRequest request,
            Authentication authentication) {

        Long memberId = getAuthenticatedMemberId(authentication);
        Wishlist wishlist = wishlistService.createWishlist(memberId, request);
        return ResponseEntity.ok(wishlist);
    }


    @GetMapping
    public ResponseEntity<List<Wishlist>> getUserWishlist(Authentication authentication) {
        Long memberId = getAuthenticatedMemberId(authentication);
        return ResponseEntity.ok(wishlistService.getUserWishlist(memberId));
    }

    @GetMapping("/{wishlistId}")
    public ResponseEntity<Wishlist> getWishlist(
            @PathVariable Long wishlistId) {

        Wishlist wishlist = wishlistService.getWishlistById(wishlistId);
        return ResponseEntity.ok(wishlist);
    }
}
