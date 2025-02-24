package com.amcamp.domain.wishlist;

import com.amcamp.domain.member.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    // 🔹 위시리스트에 곡 추가
    public Wishlist addToWishlist(Member member, String trackName, String artist, String spotifyId, String albumCoverUrl) {
        Wishlist wishlist = new Wishlist(member, trackName, artist, spotifyId, albumCoverUrl);
        return wishlistRepository.save(wishlist);
    }

    // 🔹 특정 사용자의 위시리스트 조회
    public List<Wishlist> getUserWishlist(Member member) {
        return wishlistRepository.findByMember(member);
    }

    // 🔹 위시리스트에서 곡 삭제
    public void removeFromWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }
}
