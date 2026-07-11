package com.likelion.pbl.assignment.controller;

import com.likelion.pbl.assignment.dto.*;
import com.likelion.pbl.assignment.service.AssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    // ── POST /members/{memberId}/assignments ──────────────────
    @PostMapping("/members/{memberId}/assignments")
    public ResponseEntity<AssignmentResponse> create(
            @PathVariable Long memberId,
            @RequestBody AssignmentCreateRequest req) {
        return ResponseEntity.status(201).body(assignmentService.create(memberId, req));
    }

    // ── GET /members/{memberId}/assignments ───────────────────
    @GetMapping("/members/{memberId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> findByMemberId(@PathVariable Long memberId) {
        return ResponseEntity.ok(assignmentService.findByMemberId(memberId));
    }

    // ── GET /assignments ──────────────────────────────────────
    @GetMapping("/assignments")
    public ResponseEntity<List<AssignmentResponse>> findAll() {
        return ResponseEntity.ok(assignmentService.findAll());
    }

    // ── GET /assignments/search?keyword= ─────────────────────
    @GetMapping("/assignments/search")
    public ResponseEntity<List<AssignmentResponse>> searchByTitle(
            @RequestParam String keyword) {
        return ResponseEntity.ok(assignmentService.searchByTitle(keyword));
    }

    // ── GET /assignments/{id} ─────────────────────────────────
    @GetMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.findById(id));
    }

    // ── PUT /assignments/{id} ─────────────────────────────────
    @PutMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> update(
            @PathVariable Long id,
            @RequestBody AssignmentUpdateRequest req) {
        return ResponseEntity.ok(assignmentService.update(id, req));
    }

    // ── DELETE /assignments/{id} ──────────────────────────────
    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
