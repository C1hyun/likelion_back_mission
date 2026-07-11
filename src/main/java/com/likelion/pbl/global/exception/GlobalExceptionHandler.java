package com.likelion.pbl.global.exception;

import com.likelion.pbl.global.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * [전역 예외 처리기]
 * @RestControllerAdvice: 모든 @RestController에서 발생한 예외를 여기서 가로챈다.
 * Controller에서 try-catch 불필요. 에러 응답 형식을 한 곳에서 통일.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotFound(MemberNotFoundException e) {
        return ResponseEntity
                .status(404)
                .body(new ErrorResponse(404, e.getMessage()));
    }

    @ExceptionHandler(AssignmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAssignmentNotFound(AssignmentNotFoundException e) {
        return ResponseEntity
                .status(404)
                .body(new ErrorResponse(404, e.getMessage()));
    }

    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateMember(DuplicateMemberException e) {
        return ResponseEntity
                .status(409)
                .body(new ErrorResponse(409, e.getMessage()));
    }
}
