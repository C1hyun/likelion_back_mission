package com.likelion.pbl.global.exception;

public class AssignmentNotFoundException extends RuntimeException {
    public AssignmentNotFoundException(Long id) {
        super("과제를 찾을 수 없습니다. id: " + id);
    }
}
