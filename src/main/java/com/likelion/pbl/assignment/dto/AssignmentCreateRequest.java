package com.likelion.pbl.assignment.dto;
public class AssignmentCreateRequest {
    private String title, description;
    public AssignmentCreateRequest() {}
    public String getTitle()       { return title; }
    public String getDescription() { return description; }
    public void setTitle(String v)       { title = v; }
    public void setDescription(String v) { description = v; }
}
