package com.likelion.pbl.member.dto;
public class LionCreateRequest {
    private String name, major, part, studentId; private int generation;
    public LionCreateRequest() {}
    public String getName()       { return name; }
    public String getMajor()      { return major; }
    public int    getGeneration() { return generation; }
    public String getPart()       { return part; }
    public String getStudentId()  { return studentId; }
    public void setName(String v)      { name = v; }
    public void setMajor(String v)     { major = v; }
    public void setGeneration(int v)   { generation = v; }
    public void setPart(String v)      { part = v; }
    public void setStudentId(String v) { studentId = v; }
}
