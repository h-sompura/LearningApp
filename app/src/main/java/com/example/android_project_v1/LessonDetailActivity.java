package com.example.android_project_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LessonDetailActivity extends AppCompatActivity {

    public static final String PREFERENCES = "DetailPrefs" ;

    TextView lessonDetailTextView, lessonLengthTextView;
    EditText lessonNotesEditText;
    Button completeLessonButton, saveNotesButton;
    Lesson lesson;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);

        configure();
    }

    private void completeLesson() {
        lesson.isCompleted = true;
    }

    private void configure() {
        lessonDetailTextView = findViewById(R.id.textview_lesson_detail);
        lessonLengthTextView = findViewById(R.id.textview_lesson_length);
        completeLessonButton = findViewById(R.id.button_mark_complete);
        saveNotesButton = findViewById(R.id.button_save_notes);
        lessonNotesEditText = findViewById(R.id.edittext_take_notes);

        lesson = getLessonInfo();
        String lessonNotesKey = "lessonNotes_" + lesson.lessonNumber;
        Log.d("xDEBUG", "lessonNotes: " + lessonNotesKey);

        sharedpreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

        // Set Lesson Notes from SharedPreferences
        lessonNotesEditText.setText(sharedpreferences.getString(lessonNotesKey, ""));
        Log.d("xDEBUG", "sharedPreferences: " + sharedpreferences.getString(lessonNotesKey, ""));

        lessonDetailTextView.setText(lesson.name);
        lessonLengthTextView.setText(lesson.lengthConverter(lesson.length));

        completeLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completeLesson();
                finish();
            }
        });

        saveNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(lessonNotesKey, lessonNotesEditText.getText().toString());
                editor.apply();
                Log.d("xDEBUG", "sharedPreferences: " + sharedpreferences.getString(lessonNotesKey, ""));
                Toast.makeText(LessonDetailActivity.this,"Note is saved.",Toast.LENGTH_LONG).show();
            }
        });
    }

    private Lesson getLessonInfo() {
        int lessonNumber = getIntent().getIntExtra("lessonNumber", 0);
        Log.d("xDEBUG", "lessonNumber: " + lessonNumber);
        int index = lessonNumber-1;
        return Lesson.getInstance(index);
    }
}