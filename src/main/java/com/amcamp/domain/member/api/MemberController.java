package com.amcamp.domain.member.api;

import com.amcamp.domain.member.application.MemberService;
import com.amcamp.domain.member.dto.response.MemberInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public MemberInfoResponse memberInfo() {
        return memberService.getMemberInfo();
    }
}
