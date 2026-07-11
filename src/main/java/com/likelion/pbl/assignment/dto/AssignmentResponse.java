package com.likelion.pbl.assignment.dto;
import com.likelion.pbl.assignment.domain.Assignment;
public class AssignmentResponse {
    private Long id; private String title, description, memberName; private Long memberId;
    private AssignmentResponse() {}
    public static AssignmentResponse from(Assignment a) {
        AssignmentResponse r = new AssignmentResponse();
        r.id = a.getId(); r.title = a.getTitle(); r.description = a.getDescription();
        r.memberId = a.getMember().getId(); r.memberName = a.getMember().getName();
        return r;
    }
    public Long   getId()          { return id; }
    public String getTitle()       { return title; }
    public String getDescription() { return description; }
    public Long   getMemberId()    { return memberId; }
    public String getMemberName()  { return memberName; }
}
