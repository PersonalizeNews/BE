package com.amcamp.domain.chat.application;

import com.amcamp.domain.chat.dto.request.ChatRequest;
import com.amcamp.domain.chat.dto.response.ChatResponse;
import com.amcamp.infra.config.chat.ChatInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private static final String MODEL = "gemini-pro";
    private final ChatInterface chatInterface;

    public ChatResponse getCompletion(ChatRequest request) {
        return chatInterface.getCompletion(MODEL, request);
    }

    public String getCompletion(String text) {
        ChatResponse response = getCompletion(new ChatRequest(text));

        return response.getCandidates()
                .stream()
                .findFirst().flatMap(candidate -> candidate.getContent().getParts()
                        .stream()
                        .findFirst()
                        .map(ChatResponse.TextPart::getText))
                .orElse(null);
    }
}
