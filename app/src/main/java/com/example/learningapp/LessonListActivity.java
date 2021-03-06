package com.example.learningapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.learningapp.databinding.ListLessonBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LessonListActivity extends AppCompatActivity {

    private static final String TAG = "LessonList";

    //binding
    private ListLessonBinding binding;

    private ListView lessonListView;
    private ArrayList<Lesson> lessonList;
    private LessonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting binding variable
        this.binding = ListLessonBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        Log.d(TAG, "Lesson List Screen created");

        //creating a successfully logged in message
        Snackbar snackbar = Snackbar.make((findViewById(android.R.id.content)), "Successfully logged in", Snackbar.LENGTH_LONG);
        snackbar.setBackgroundTint(Color.rgb(235, 251, 246));
        snackbar.setTextColor(Color.rgb(52, 211, 157));
        snackbar.show();

        //configuring list view
        configureListView();

        //on logout
        this.binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go back to log in
                //set preferences to false
                SharedPreferences preferences = getSharedPreferences("saveLoginToggle", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isLoginSaved", false);
                editor.clear();
                editor.apply();

                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private void configureListView() {
        // lessonListView = findViewById(R.id.listview_lesson_list);
        lessonListView = binding.listViewLessonList;

        lessonList = LessonList.getLessons();

        lessonListView.setClickable(true);
        // ArrayAdapter<Lesson> lessonArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lessons);
        adapter = new LessonAdapter(this, lessonList);
        // adapter.notifyDataSetChanged();
        lessonListView.setAdapter(adapter);

        // List action for tapping on a row of the ListView
        lessonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Check for Sequential Progression...
                if(binding.switchSequentialProgress.isChecked()) {

                    if(i != 0 && !lessonList.get(i - 1).isCompleted()) {
                        //creating a snackbar for seq toggle
                        Snackbar snackbar = Snackbar.make((findViewById(android.R.id.content)), "You have to complete lesson " + i +" to start this lesson.", Snackbar.LENGTH_LONG);
                        snackbar.setBackgroundTint(Color.rgb(255, 249, 233));
                        snackbar.setTextColor(Color.rgb(255, 192, 46));
                        snackbar.show();
                        return;
                    }
                }

                Intent intent = new Intent(LessonListActivity.this, LessonDetailActivity.class);

                // TODO : try to send Lesson from serialized extra object
                intent.putExtra("lessonNumber", lessonList.get(i).getLessonNumber());
                // intent.putExtra("lessonFromList", lessons[i]);

                startActivity(intent);
            }
        });

    }




}

