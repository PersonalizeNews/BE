package com.amcamp.domain.wishlist;

import com.amcamp.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;  // 🔹 로그인한 사용자 정보 (카카오 로그인)

    private String trackName; // 곡명
    private String artist; // 가수명
    private String spotifyId; // Spotify ID
    private String albumCoverUrl; // 앨범 이미지 URL

    public Wishlist(Member member, String trackName, String artist, String spotifyId, String albumCoverUrl) {
        this.member = member;
        this.trackName = trackName;
        this.artist = artist;
        this.spotifyId = spotifyId;
        this.albumCoverUrl = albumCoverUrl;
    }
}
