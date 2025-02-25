package com.amcamp.domain.wishlist.application;

import com.amcamp.domain.image.application.ImageService;
import com.amcamp.domain.member.domain.Member;
import com.amcamp.domain.wishlist.dao.WishlistRepository;
import com.amcamp.domain.wishlist.domain.Wishlist;
import com.amcamp.domain.wishlist.dto.response.WishlistInfoResponse;
import com.amcamp.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final MemberUtil memberUtil;
    private final ImageService imageService;

    public WishlistInfoResponse createWishlist(String title, MultipartFile file) {
        Member member = memberUtil.getCurrentMember();

        String imageUrl = imageService.uploadInitWishlistImage(file);

        Wishlist wishlist = wishlistRepository.save(Wishlist.createWishlist(member, title, imageUrl));
        imageService.storeImageInfo(imageUrl, wishlist.getId().toString());

        return new WishlistInfoResponse(wishlist.getId(), wishlist.getTitle(), wishlist.getImageUrl());
    }
}

