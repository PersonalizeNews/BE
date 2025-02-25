package com.amcamp.domain.track.api;

import com.amcamp.domain.track.application.TrackService;
import com.amcamp.domain.track.dto.request.TrackCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tracks")
public class TrackController {

    private final TrackService trackService;

    @PostMapping
    public ResponseEntity<Void> trackCreate(@RequestBody List<TrackCreateRequest> request) {
        trackService.createTrack(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
