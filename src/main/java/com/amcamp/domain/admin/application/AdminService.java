package com.amcamp.domain.admin.application;

import com.amcamp.domain.admin.dto.response.MemberInfoResponse;
import com.amcamp.domain.member.dao.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class AdminService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberInfoResponse> getMemberList() {
        return memberRepository.findAll()
                .stream()
                .map(MemberInfoResponse::from)
                .toList();
    }
}
