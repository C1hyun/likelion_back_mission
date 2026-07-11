package com.likelion.pbl.assignment.repository;

import com.likelion.pbl.assignment.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    /** 멤버별 과제 목록: SELECT * FROM assignment WHERE member_id = ? */
    List<Assignment> findByMemberId(Long memberId);

    /**
     * 제목 키워드 검색 (대소문자 무관)
     * Containing → LIKE '%keyword%'
     * → SELECT * FROM assignment WHERE title LIKE '%?%'
     */
    List<Assignment> findByTitleContaining(String keyword);
}
