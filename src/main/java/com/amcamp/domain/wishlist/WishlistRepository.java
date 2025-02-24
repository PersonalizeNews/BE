package com.amcamp.domain.wishlist;

import com.amcamp.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByMember(Member member); // 특정 사용자의 위시리스트 조회
}
