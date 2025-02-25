package com.amcamp.domain.wishlist.application;

import com.amcamp.domain.member.domain.Member;
import com.amcamp.domain.wishlist.dao.WishlistRepository;
import com.amcamp.domain.wishlist.domain.Wishlist;
import com.amcamp.domain.wishlist.dto.request.WishlistCreateRequest;
import com.amcamp.domain.wishlist.dto.response.WishlistInfoResponse;
import com.amcamp.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final MemberUtil memberUtil;

    public WishlistInfoResponse createWishlist(WishlistCreateRequest request) {
        Member member = memberUtil.getCurrentMember();
        Wishlist wishlist = wishlistRepository.save(Wishlist.createWishlist(member, request.title()));
        return new WishlistInfoResponse(wishlist.getId(), wishlist.getTitle());
    }
}

