package com.example.android_project_v1;

public class Lesson {

    int lessonNumber;
     String name;
     int length; // will convert to hr and min later with a converter
     boolean isCompleted;

    private Lesson() {
    }

    private Lesson(int lessonNumber, String name, int length, boolean isCompleted) {
        this.lessonNumber = lessonNumber;
        this.name = name;
        this.length = length;
        this.isCompleted = isCompleted;
    }

    private static Lesson[] instances = {
        new Lesson(1, "Introduction to the course", 12, false),
                new Lesson(2, "What is Javascript", 30, false),
                new Lesson(3, "Variables and conditionals", 80, false),
                new Lesson(4, "Loops", 38, false)
    };

    public static Lesson[] getInstances() {
        return instances;
    }

    public static Lesson getInstance(int index) {
        return instances[index];
    }

    public String lengthConverter(int length) {
        if(length >= 60) {
            return (length / 60) + "hr " + (length % 60) + " min";
        }
        else {
            return (length % 60) + " min";
        }

    }

    @Override
    public String toString() {
        return lessonNumber + ". " + name + "\n" + lengthConverter(length);
    }
}
