package com.likelion.pbl.assignment.dto;
public class AssignmentUpdateRequest {
    private String title, description;
    public AssignmentUpdateRequest() {}
    public String getTitle()       { return title; }
    public String getDescription() { return description; }
    public void setTitle(String v)       { title = v; }
    public void setDescription(String v) { description = v; }
}
