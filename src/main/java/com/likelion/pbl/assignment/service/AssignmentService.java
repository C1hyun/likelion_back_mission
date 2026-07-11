package com.likelion.pbl.assignment.service;

import com.likelion.pbl.assignment.domain.Assignment;
import com.likelion.pbl.assignment.dto.*;
import com.likelion.pbl.assignment.repository.AssignmentRepository;
import com.likelion.pbl.global.exception.AssignmentNotFoundException;
import com.likelion.pbl.global.exception.MemberNotFoundException;
import com.likelion.pbl.member.domain.Member;
import com.likelion.pbl.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final MemberRepository     memberRepository;

    public AssignmentService(AssignmentRepository assignmentRepository,
                             MemberRepository memberRepository) {
        this.assignmentRepository = assignmentRepository;
        this.memberRepository     = memberRepository;
    }

    // ── 등록 ─────────────────────────────────────────────────

    @Transactional
    public AssignmentResponse create(Long memberId, AssignmentCreateRequest req) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId)); // null 대신 예외

        Assignment saved = assignmentRepository.save(
                new Assignment(req.getTitle(), req.getDescription(), member));
        return AssignmentResponse.from(saved);
    }

    // ── 조회 ─────────────────────────────────────────────────

    public List<AssignmentResponse> findAll() {
        return assignmentRepository.findAll().stream()
                .map(AssignmentResponse::from).toList();
    }

    public List<AssignmentResponse> findByMemberId(Long memberId) {
        return assignmentRepository.findByMemberId(memberId).stream()
                .map(AssignmentResponse::from).toList();
    }

    public AssignmentResponse findById(Long id) {
        return AssignmentResponse.from(
                assignmentRepository.findById(id)
                        .orElseThrow(() -> new AssignmentNotFoundException(id)));
    }

    /** 제목 키워드 검색 */
    public List<AssignmentResponse> searchByTitle(String keyword) {
        return assignmentRepository.findByTitleContaining(keyword).stream()
                .map(AssignmentResponse::from).toList();
    }

    // ── 수정 ─────────────────────────────────────────────────

    @Transactional
    public AssignmentResponse update(Long id, AssignmentUpdateRequest req) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException(id));
        assignment.updateInfo(req.getTitle(), req.getDescription());
        return AssignmentResponse.from(assignment);
    }

    // ── 삭제 ─────────────────────────────────────────────────

    @Transactional
    public void delete(Long id) {
        if (!assignmentRepository.existsById(id))
            throw new AssignmentNotFoundException(id);
        assignmentRepository.deleteById(id);
    }
}
