package com.example.learningapp;

import java.util.ArrayList;

public class LessonList {

    private static ArrayList<Lesson> lessonList = null;

    private LessonList() {
    }

    public static ArrayList<Lesson> getLessons() {
        if (lessonList == null) {
            lessonList = new ArrayList<>();

            lessonList.add(new Lesson(
                    1,
                    "Introduction to the course",
                    "HTML is the foundation of all web pages. It defines the structure of a page, while CSS defines its style. HTML and CSS are the beginning of everything you need to know to make your first web page!",
                    12,
                    "https://www.youtube.com/watch?v=qz0aGYrrlhU&ab_channel=ProgrammingwithMosh",
                    false));
            lessonList.add(new Lesson(
                    2,
                    "What is Javascript",
                    "JavaScript is a fun and flexible programming language. It’s one of the core technologies of web development and can be used on both the front-end and the back-end.",
                    30,
                    "https://www.youtube.com/watch?v=upDLs1sn7g4&ab_channel=ProgrammingwithMosh",
                    false));
            lessonList.add(new Lesson(
                    3,
                    "Variables and conditionals",
                    "Get started on conditionals, functions, and scope, which are all universal programming concepts.In this video, we’ll look at how to define and use variables.",
                    80,
                    "https://www.youtube.com/watch?v=edlFjlzxkSI",
                    false));
            lessonList.add(new Lesson(
                    4,
                    "Loops",
                    "In this course, you’ll learn about two core programming topics: Arrays and loops! Then, you’ll discover how to easily create repeating code using loops.",
                    38,
                    "https://www.youtube.com/watch?v=s9wW2PpJsmQ&ab_channel=ProgrammingwithMosh",
                    false));
        }

        return lessonList;
    }
}
