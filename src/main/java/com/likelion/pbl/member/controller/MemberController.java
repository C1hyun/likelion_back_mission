package com.likelion.pbl.member.controller;

import com.likelion.pbl.member.dto.*;
import com.likelion.pbl.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * GET /members         → 전체 조회
     * GET /members?part=백엔드 → 파트별 필터링
     */
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers(
            @RequestParam(required = false) String part) {
        return ResponseEntity.ok(memberService.findAllOrByPart(part));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        // 예외는 GlobalExceptionHandler가 처리 — null 체크 없음
        return ResponseEntity.ok(memberService.findById(id));
    }

    @PostMapping("/lions")
    public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest req) {
        return ResponseEntity.status(201).body(memberService.createLion(req));
    }

    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest req) {
        return ResponseEntity.status(201).body(memberService.createStaff(req));
    }

    @PutMapping("/lions/{id}")
    public ResponseEntity<MemberResponse> updateLion(@PathVariable Long id,
                                                     @RequestBody LionUpdateRequest req) {
        return ResponseEntity.ok(memberService.updateLion(id, req));
    }

    @PutMapping("/staffs/{id}")
    public ResponseEntity<MemberResponse> updateStaff(@PathVariable Long id,
                                                      @RequestBody StaffUpdateRequest req) {
        return ResponseEntity.ok(memberService.updateStaff(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
