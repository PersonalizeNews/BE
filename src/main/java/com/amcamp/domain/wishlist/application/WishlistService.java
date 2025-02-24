package com.amcamp.domain.wishlist.application;

import com.amcamp.domain.member.dao.MemberRepository;
import com.amcamp.domain.member.domain.Member;
import com.amcamp.domain.wishlist.dao.WishlistRepository;
import com.amcamp.domain.wishlist.domain.Wishlist;
import com.amcamp.domain.wishlist.dto.WishlistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository, MemberRepository memberRepository) {
        this.wishlistRepository = wishlistRepository;
        this.memberRepository = memberRepository;
    }

    public Wishlist createWishlist(Long memberId, WishlistRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Wishlist wishlist = Wishlist.from(member, request);
        return wishlistRepository.save(wishlist);
    }

    public Wishlist getWishlistById(Long wishlistId) {
        return wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));
    }

    public List<Wishlist> getUserWishlist(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        return wishlistRepository.findByMember(member);
    }
}

