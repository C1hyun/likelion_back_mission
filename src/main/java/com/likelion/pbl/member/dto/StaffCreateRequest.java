package com.likelion.pbl.member.dto;
public class StaffCreateRequest {
    private String name, major, part, position; private int generation;
    public StaffCreateRequest() {}
    public String getName()       { return name; }
    public String getMajor()      { return major; }
    public int    getGeneration() { return generation; }
    public String getPart()       { return part; }
    public String getPosition()   { return position; }
    public void setName(String v)     { name = v; }
    public void setMajor(String v)    { major = v; }
    public void setGeneration(int v)  { generation = v; }
    public void setPart(String v)     { part = v; }
    public void setPosition(String v) { position = v; }
}
