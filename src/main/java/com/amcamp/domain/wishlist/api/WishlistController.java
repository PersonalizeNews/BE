package com.amcamp.domain.wishlist.api;

import com.amcamp.domain.wishlist.application.WishlistService;
import com.amcamp.domain.wishlist.dto.response.WishlistInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping
    public void createWishlist(@RequestPart("title") String title,
                               @RequestPart("image") MultipartFile file){
       wishlistService.createWishlist(title, file);
    }

    @GetMapping
    public List<WishlistInfoResponse> findAllWishlist(){
        return wishlistService.findAllWishlist();
    }

/*    @PutMapping("/{wishlistId}")
    public List<WishlistInfoResponse> editWishlist(@PathVariable Long wishlistId){
        return wishlistService.editWishlist(wishlistId);
    }*/

    @DeleteMapping("/{wishlistId}")
    public ResponseEntity<Void> deleteWishlist(@PathVariable Long wishlistId){
        wishlistService.deleteWishlist(wishlistId);
        return ResponseEntity.ok().build();
    }
}
