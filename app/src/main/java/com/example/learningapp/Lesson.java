package com.example.learningapp;

import java.io.Serializable;

public class Lesson implements Serializable {

    private int lessonNumber;
    private String name;
    private String description;
    private int length;
    private String url;
    private boolean isCompleted;
    private String note;

    public Lesson(int lessonNumber, String name, String description, int length, String url, boolean isCompleted) {
        this.lessonNumber = lessonNumber;
        this.name = name;
        this.description = description;
        this.length = length;
        this.url = url;
        this.isCompleted = isCompleted;
    }

    public String lengthConverter(int length) {
        if (length >= 60) {
            return (length / 60) + "hr " + (length % 60) + " min";
        } else {
            return (length % 60) + " min";
        }
    }

    @Override
    public String toString() {
        return lessonNumber + ". " + name + "\n" + lengthConverter(length) + "Checked: " + isCompleted;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }


    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getUrl() {
        return url;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
