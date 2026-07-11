package com.likelion.pbl.global.dto;

/**
 * 모든 에러 응답에 사용하는 공통 DTO.
 * 프론트엔드는 message 필드를 토스트 알림으로 표시한다.
 */
public class ErrorResponse {

    private int    status;
    private String message;

    public ErrorResponse(int status, String message) {
        this.status  = status;
        this.message = message;
    }

    public int    getStatus()  { return status; }
    public String getMessage() { return message; }
}
