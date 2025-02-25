package com.amcamp.domain.wishlist.api;

import com.amcamp.domain.wishlist.application.WishlistService;
import com.amcamp.domain.wishlist.dto.request.WishlistCreateRequest;
import com.amcamp.domain.wishlist.dto.response.WishlistInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
}
