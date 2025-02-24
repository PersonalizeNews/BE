package com.amcamp.domain.wishlist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WishlistRequest {
    private String trackName;
    private String artist;
    private String spotifyId;
    private String albumCoverUrl;
}
