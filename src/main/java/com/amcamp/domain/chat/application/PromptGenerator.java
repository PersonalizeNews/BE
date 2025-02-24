package com.amcamp.domain.chat.application;

import org.springframework.stereotype.Component;

@Component
public class PromptGenerator {
    private static final String GOAL = "다음 줄에 기분을 입력할 거야. 그 기분에 맞는 음악 장르를 2개 추천해줘.";
    private static final String CONDITION = "답변은 반드시 영어로 출력하고, 쉼표(,)로 구분된 한 줄의 텍스트로만 출력하고, 다른 설명은 포함하지 말아줘.";
    private static final String SYSTEM_PROMPT = GOAL + CONDITION;

    public String generatePrompt(String input) {
        return SYSTEM_PROMPT + System.lineSeparator() + input;
    }
}
