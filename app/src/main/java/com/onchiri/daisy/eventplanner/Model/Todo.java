package com.onchiri.daisy.eventplanner.Model;

public class Todo {

    private String task;
    private String notes;

    public Todo(){
    }
    public String getTask() {
        return task;
    }

    public String getNotes() {
        return notes;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
