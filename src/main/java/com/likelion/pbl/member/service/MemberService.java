package com.likelion.pbl.member.service;

import com.likelion.pbl.global.exception.DuplicateMemberException;
import com.likelion.pbl.global.exception.MemberNotFoundException;
import com.likelion.pbl.member.domain.Member;
import com.likelion.pbl.member.domain.RoleType;
import com.likelion.pbl.member.dto.*;
import com.likelion.pbl.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    // ── 생성 ─────────────────────────────────────────────────

    @Transactional
    public MemberResponse createLion(LionCreateRequest req) {
        if (repository.existsByName(req.getName()))
            throw new DuplicateMemberException(req.getName());  // null 대신 예외

        Member saved = repository.save(new Member(
                req.getName(), req.getMajor(), req.getGeneration(), req.getPart(),
                RoleType.LION, req.getStudentId(), null));
        return MemberResponse.from(saved);
    }

    @Transactional
    public MemberResponse createStaff(StaffCreateRequest req) {
        if (repository.existsByName(req.getName()))
            throw new DuplicateMemberException(req.getName());

        Member saved = repository.save(new Member(
                req.getName(), req.getMajor(), req.getGeneration(), req.getPart(),
                RoleType.STAFF, null, req.getPosition()));
        return MemberResponse.from(saved);
    }

    // ── 조회 ─────────────────────────────────────────────────

    public List<MemberResponse> findAll() {
        return repository.findAll().stream().map(MemberResponse::from).toList();
    }

    /** part가 있으면 필터링, 없으면 전체 반환 */
    public List<MemberResponse> findAllOrByPart(String part) {
        if (part == null || part.isBlank()) return findAll();
        return repository.findByPart(part).stream().map(MemberResponse::from).toList();
    }

    public MemberResponse findById(Long id) {
        return MemberResponse.from(
                repository.findById(id)
                        .orElseThrow(() -> new MemberNotFoundException(id)));
    }

    // ── 수정 ─────────────────────────────────────────────────

    @Transactional
    public MemberResponse updateLion(Long id, LionUpdateRequest req) {
        Member member = repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        member.updateInfo(req.getMajor(), req.getGeneration(), req.getPart());
        member.updateStudentId(req.getStudentId());
        return MemberResponse.from(member);
    }

    @Transactional
    public MemberResponse updateStaff(Long id, StaffUpdateRequest req) {
        Member member = repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        member.updateInfo(req.getMajor(), req.getGeneration(), req.getPart());
        member.updatePosition(req.getPosition());
        return MemberResponse.from(member);
    }

    // ── 삭제 ─────────────────────────────────────────────────

    @Transactional
    public void deleteMember(Long id) {
        if (!repository.existsById(id))
            throw new MemberNotFoundException(id);
        repository.deleteById(id);
    }
}
