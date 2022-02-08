package com.example.learningapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.example.learningapp.databinding.ListLessonBinding;
import com.google.android.material.snackbar.Snackbar;

public class LessonList extends AppCompatActivity {

    private static final String TAG = "LessonList";


    //binding
    private ListLessonBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting binding variable
        this.binding = ListLessonBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        Log.d(TAG, "Lesson List Screen created");

        //creating a successfully logged in message
        Snackbar snackbar = Snackbar.make((findViewById(android.R.id.content)), "Successfully logged in", Snackbar.LENGTH_LONG);
        snackbar.setBackgroundTint(Color.rgb(235,251,246));
        snackbar.setTextColor(Color.rgb(52,211,157));
        snackbar.show();


        //on logout
        this.binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go back to log in

                Intent intent = new Intent(LessonList.this,MainActivity.class);
                Log.d(TAG, "Logout button pressed");

                startActivity(intent);
            }
        });
    }


}
