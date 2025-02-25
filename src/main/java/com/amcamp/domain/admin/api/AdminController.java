package com.amcamp.domain.admin.api;

import com.amcamp.domain.admin.application.AdminService;
import com.amcamp.domain.admin.dto.response.MemberInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/members")
    public List<MemberInfoResponse> memberListInfo() {
        return adminService.getMemberList();
    }
}
