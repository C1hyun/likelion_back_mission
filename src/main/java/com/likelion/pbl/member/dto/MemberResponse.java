package com.likelion.pbl.member.dto;
import com.likelion.pbl.member.domain.Member;
public class MemberResponse {
    private Long id; private String name, major, part, roleName, studentId, position;
    private int generation;
    private MemberResponse() {}
    public static MemberResponse from(Member m) {
        MemberResponse r = new MemberResponse();
        r.id = m.getId(); r.name = m.getName(); r.major = m.getMajor();
        r.generation = m.getGeneration(); r.part = m.getPart();
        r.roleName = m.getRoleType().getDisplayName();
        r.studentId = m.getStudentId(); r.position = m.getPosition();
        return r;
    }
    public Long   getId()         { return id; }
    public String getName()       { return name; }
    public String getMajor()      { return major; }
    public int    getGeneration() { return generation; }
    public String getPart()       { return part; }
    public String getRoleName()   { return roleName; }
    public String getStudentId()  { return studentId; }
    public String getPosition()   { return position; }
}
