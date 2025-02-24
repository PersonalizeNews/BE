package com.amcamp.domain.wishlist;

import com.amcamp.domain.member.dao.MemberRepository;
import com.amcamp.domain.member.domain.Member;
import com.amcamp.domain.member.domain.OauthInfo;
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
    private final MemberRepository memberRepository; // 🔹 MemberRepository를 직접 사용

    @Autowired
    public WishlistController(WishlistService wishlistService, MemberRepository memberRepository) {
        this.wishlistService = wishlistService;
        this.memberRepository = memberRepository;
    }

    // 🔹 로그인한 사용자의 Member 객체를 가져오는 메서드 (AuthService 없이 MemberRepository 사용)
    private Member getAuthenticatedMember(Authentication authentication) {
        if (authentication.getPrincipal() instanceof OidcUser oidcUser) {
            OauthInfo oauthInfo = OauthInfo.createOauthInfo(oidcUser.getSubject(), oidcUser.getIssuer().toString());
            return memberRepository.findByOauthInfo(oauthInfo)
                    .orElseThrow(() -> new RuntimeException("Member not found in database"));
        }
        throw new RuntimeException("Invalid authentication principal");
    }

    // 🔹 위시리스트에 곡 추가
    @PostMapping("/add")
    public ResponseEntity<Wishlist> addWishlistItem(
            @RequestParam String trackName,
            @RequestParam String artist,
            @RequestParam String spotifyId,
            @RequestParam String albumCoverUrl,
            Authentication authentication) {

        Member member = getAuthenticatedMember(authentication);
        Wishlist wishlist = wishlistService.addToWishlist(member, trackName, artist, spotifyId, albumCoverUrl);
        return ResponseEntity.ok(wishlist);
    }

    // 🔹 위시리스트 조회
    @GetMapping("/list")
    public ResponseEntity<List<Wishlist>> getUserWishlist(Authentication authentication) {
        Member member = getAuthenticatedMember(authentication);
        return ResponseEntity.ok(wishlistService.getUserWishlist(member));
    }

    // 🔹 위시리스트 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeWishlistItem(@PathVariable Long id) {
        wishlistService.removeFromWishlist(id);
        return ResponseEntity.ok("위시리스트에서 삭제되었습니다.");
    }
}
