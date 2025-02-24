package com.amcamp.domain.wishlist.domain;


import com.amcamp.domain.common.model.BaseTimeEntity;
import com.amcamp.domain.member.domain.Member;
import com.amcamp.domain.wishlist.dto.WishlistRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "wishlist")
public class Wishlist extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 50)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public Wishlist(Member member, String title, String description) {
        this.member = member;
        this.title = title;
        this.description = description;
    }

    public static Wishlist from(Member member, WishlistRequest request) {
        return new Wishlist(member, request.getTitle(), request.getDescription());
    }
}