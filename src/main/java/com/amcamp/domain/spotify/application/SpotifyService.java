package com.amcamp.domain.spotify.application;

import com.amcamp.domain.spotify.dto.response.SpotifySearchResponse;
import com.amcamp.global.error.exception.CustomException;
import com.amcamp.global.error.exception.ErrorCode;
import com.amcamp.global.util.MemberUtil;
import com.amcamp.infra.config.spotify.SpotifyConfig;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.neovisionaries.i18n.CountryCode.KR;

@Transactional
@Service
@RequiredArgsConstructor
public class SpotifyService {

    private final SpotifyConfig spotifyConfig;
    private final MemberUtil memberUtil;

    public List<SpotifySearchResponse> searchByGenre(List<String> genres) {
        memberUtil.getCurrentMember();

        Track[] tracks = getTrackInfoByGenre(genres);

        Set<String> uniqueTrackTitles = new HashSet<>();
        List<SpotifySearchResponse> list = new ArrayList<>();

        Arrays.stream(tracks)
                .map(this::getTrackData)
                .distinct()
                .filter(trackData -> uniqueTrackTitles.add(trackData.title()))
                .forEach(list::add);

        return list;
    }

    /*
     * 장르에 따른 검색 결과를 반환합니다.
     */
    public Track[] getTrackInfoByGenre(List<String> genres) {
        try {
            SpotifyApi spotifyApi = new SpotifyApi.Builder()
                    .setAccessToken(spotifyConfig.generateAccessToken())
                    .build();

            String genreQuery = genres.stream()
                    .map(genre -> "genre:" + genre)
                    .collect(Collectors.joining(" OR "));

            String query = genreQuery + " year:2024-2025";

            SearchTracksRequest searchTrackRequest = spotifyApi.searchTracks(query)
                    .market(KR)
                    .limit(10)
                    .build();

            Paging<Track> searchResult = searchTrackRequest.execute();

            return searchResult.getItems();
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            throw new CustomException(ErrorCode.SPOTIFY_EXCEPTION);
        }
    }

    private SpotifySearchResponse getTrackData(Track track) {
        ArtistSimplified[] artists = track.getArtists();
        String artistName = artists[0].getName();

        AlbumSimplified album = track.getAlbum();
        String albumName = album.getName();

        Image[] images = album.getImages();
        String imageUrl = (images.length > 0) ? images[0].getUrl() : "NO_IMAGE";

        return SpotifySearchResponse.of(artistName, track.getName(), albumName, imageUrl);
    }
}

