package com.likelion.pbl.member.repository;

import com.likelion.pbl.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
    boolean existsByName(String name);

    /** 파트별 멤버 필터링: SELECT * FROM member WHERE part = ? */
    List<Member> findByPart(String part);
}
