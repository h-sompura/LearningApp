package com.example.android_project_v1;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.ls.LSException;

public class LessonListActivity extends AppCompatActivity {

    ListView lessonListView;
    Lesson[] lessons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);

        configure();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("xDEBUG", "onResume: lessons[0].isCompleted = " + lessons[0].isCompleted);
    }

    private void configure() {
        lessonListView = findViewById(R.id.listview_lesson_list);

        lessons = Lesson.getInstances();

        lessonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // send info to new activity (LessonDetailActivity)

                Log.d("xDEBUG", "i: " + i);
                Log.d("xDEBUG", "lessons[i].lessonNumber: " + lessons[i].lessonNumber);
                Intent intent = new Intent(LessonListActivity.this, LessonDetailActivity.class);

                intent.putExtra("lessonNumber", lessons[i].lessonNumber);

                startActivity(intent);
            }
        });

        ArrayAdapter<Lesson> lessonArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lessons);
        lessonArrayAdapter.notifyDataSetChanged();
        lessonListView.setAdapter(lessonArrayAdapter);
    }
}