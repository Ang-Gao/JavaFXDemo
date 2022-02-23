package com.example.classweek3;

import java.time.LocalDate;
import java.util.Date;

public class TodoItem {
    private LocalDate deadline;
    private String shortDescription;
    private String details;

    public TodoItem() {

    }

    public LocalDate getDueDateTime() {
        return deadline;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDetails() {
        return details;
    }

    public void setDueDateTime(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public TodoItem(String shortDescription,String details,LocalDate deadline) {
        this.deadline = deadline;
        this.shortDescription = shortDescription;
        this.details = details;
    }

    @Override
    public String toString() {
        return
                getShortDescription();
    }
}
