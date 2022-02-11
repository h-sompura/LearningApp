package com.example.learningapp;

import java.util.ArrayList;

public class LessonList {

    private static ArrayList<Lesson> lessonList = null;

    private  LessonList() {}

    public static ArrayList<Lesson> getLessons() {
        if(lessonList == null) {
            lessonList = new ArrayList<>();

            lessonList.add(new Lesson(
                    1,
                    "Introduction to the course",
                    12,
                    "https://www.youtube.com/watch?v=qz0aGYrrlhU&ab_channel=ProgrammingwithMosh",
                    false));
            lessonList.add(new Lesson(
                    2,
                    "What is Javascript",
                    30,
                    "https://www.youtube.com/watch?v=upDLs1sn7g4&ab_channel=ProgrammingwithMosh",
                    false));
            lessonList.add(new Lesson(
                    3,
                    "Variables and conditionals",
                    80,
                    "https://www.youtube.com/watch?v=edlFjlzxkSI",
                    false));
            lessonList.add(new Lesson(
                    4,
                    "Loops",
                    38,
                    "https://www.youtube.com/watch?v=s9wW2PpJsmQ&ab_channel=ProgrammingwithMosh",
                    false));
        }

        return lessonList;
    }
}
