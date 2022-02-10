package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningapp.databinding.ActivityLessonDetailBinding;

public class LessonDetail extends AppCompatActivity {
    private static final String TAG = "xDEBUG";
    public static final String PREFERENCES = "DetailPrefs";

    private ActivityLessonDetailBinding binding;

    TextView lessonDetailTextView, lessonLengthTextView;
    EditText lessonNotesEditText;
    Button completeLessonButton, saveNotesButton, watchlessonButton;
    Lesson lesson;
    SharedPreferences sharedpreferences;

    private String lessonNotesKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLessonDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configure();
    }

    private void completeLesson() {
        lesson.setCompleted(true);
    }

    private void configure() {

        bindViews(); // Bind views using ViewBinding

        lesson = getLessonInfo(); // Get the lesson using the index

        setActivityInformation(); // Set Lesson information to Views on the Details Screen

        configureButtonActions(); // Configure onClick Action Listeners for buttons.
    }

    private Lesson getLessonInfo() {
        int lessonNumber = getIntent().getIntExtra("lessonNumber", 0);
        int index = lessonNumber - 1;
        return Lesson.getInstance(index);
    }

    private void bindViews() {
        lessonDetailTextView = binding.textviewLessonDetail;
        lessonLengthTextView = binding.textviewLessonLength;
        completeLessonButton = binding.buttonMarkComplete;
        watchlessonButton = binding.buttonWatchLesson;
        saveNotesButton = binding.buttonSaveNotes;
        lessonNotesEditText = binding.edittextTakeNotes;
    }

    private void setActivityInformation() {
        // TODO : get extra name from string resource
        // TODO : try to get Lesson from serialized extra object

        // lesson = (Lesson) getIntent().getSerializableExtra("lessonFromList");
        Log.d(TAG, "configure: lessonInDetailsScreen = " + lesson.toString());

        lessonNotesKey = "lessonNotes_" + lesson.getLessonNumber();

        sharedpreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

        // Set Lesson Notes from SharedPreferences
        lessonNotesEditText.setText(sharedpreferences.getString(lessonNotesKey, ""));

        lessonDetailTextView.setText(lesson.getName());
        lessonLengthTextView.setText(lesson.lengthConverter(lesson.getLength()));
    }

    private void configureButtonActions() {

        // Button action for completing the lesson
        completeLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completeLesson();
                Log.d(TAG, "configure: lessonInDetailsScreen after completeLesson = " + lesson.toString());
                finish();
            }
        });

        // Button action for saving notes to the lesson
        saveNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                String note = lessonNotesEditText.getText().toString();
                editor.putString(lessonNotesKey, note); // Add note to SharedPreferences
                editor.apply();
                lesson.setNote(note); // Add note to the Lesson object as well
                Log.d(TAG, "sharedPreferences: " + sharedpreferences.getString(lessonNotesKey, ""));
                Toast.makeText(LessonDetail.this, "Note is saved.", Toast.LENGTH_LONG).show();
            }
        });

        // Button action to watch the youtube video of the lesson
        watchlessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //watch the video intent
                Intent openVideo = new Intent(Intent.ACTION_VIEW, Uri.parse(lesson.getUrl()));

                startActivity(openVideo);
            }
        });
    }
}