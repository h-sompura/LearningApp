package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.learningapp.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    private EditText username;
    private EditText password;
    private Switch saveLoginSwitch;
    private Button loginButton;

    private Boolean isLoginSaved;

    private ArrayList<User> usersList = new ArrayList<User>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting binding variable
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        //get fields
        username = this.binding.etUserName;
        password = this.binding.etPassword;
        saveLoginSwitch = this.binding.saveLoginToggle;
        loginButton = this.binding.loginButton;
//        isLoginSaved = saveLoginSwitch.isChecked();
        SharedPreferences preferences = getSharedPreferences("saveLoginToggle", MODE_PRIVATE);
        saveLoginSwitch.setChecked(preferences.getBoolean("isLoginSaved", true));
        isLoginSaved = saveLoginSwitch.isChecked();

        if (isLoginSaved) {
            Intent intent = new Intent(MainActivity.this, LessonListActivity.class);
            startActivity(intent);
        }

        // add user class and go through the list of users and see if this is a match
        usersList.add(new User("abcd", "1234"));
        usersList.add(new User("xyz", "5678"));
        usersList.add(new User("admin", "admin"));


        //programmatically creating the button onClick
        this.binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LessonListActivity.class);
                Log.d(TAG, "Login button pressed");

                //check if the user has entered BOTH the fields i.e. SHOULD NOT be empty
                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    //display fields can't be empty
                    //Log.d(TAG, "Please enter all the required fields!");

                    Snackbar snackbar = Snackbar.make(view, "Please enter all the fields", Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.rgb(255, 249, 233));
                    snackbar.setTextColor(Color.rgb(255, 192, 46));
                    snackbar.show();

                    //clear the fields
                    username.getText().clear();
                    password.getText().clear();
                }

                //both the fields are entered
                if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    //display fields can't be empty
                    //Log.d(TAG, "The user has entered required fields!");

                    String enteredUsername = username.getText().toString();
                    String enteredPassword = password.getText().toString();

//                    if (!enteredUsername.equals("abcd")) {
//                        //user enters something other than abcd
//                        Snackbar snackbar = Snackbar.make(view, "The username does not exist", Snackbar.LENGTH_LONG);
//                        snackbar.setBackgroundTint(Color.rgb(255, 233, 217));
//                        snackbar.setTextColor(Color.rgb(244, 86, 40));
//                        snackbar.show();
//
//                        //clear the fields
//                        username.getText().clear();
//                        password.getText().clear();
//
//                    } else {
//                        Snackbar snackbar = Snackbar.make(view, "The username/password combination does not match", Snackbar.LENGTH_LONG);
//                        snackbar.setBackgroundTint(Color.rgb(255, 233, 217));
//                        snackbar.setTextColor(Color.rgb(244, 86, 40));
//                        snackbar.show();
//
//                        //clear the fields
//                        username.getText().clear();
//                        password.getText().clear();
//                    }
//
//                    if (enteredUsername.equals("abcd") && enteredPassword.equals("1234")) {
//
//                        Log.d(TAG, "Try to go to LessonList activity");
//                        startActivity(intent);
//                    }

                    if (isUsernamePresent(usersList, enteredUsername)) {
                        //user enters something other than usernames present in our list
                        Snackbar snackbar = Snackbar.make(view, "The username does not exist", Snackbar.LENGTH_LONG);
                        snackbar.setBackgroundTint(Color.rgb(255, 233, 217));
                        snackbar.setTextColor(Color.rgb(244, 86, 40));
                        snackbar.show();

                        //clear the fields
                        username.getText().clear();
                        password.getText().clear();
                    } else {
                        for (User i : usersList) {
                            if (enteredUsername.equals(i.getUsername()) && enteredPassword.equals(i.getPassword())) {

                                Log.d(TAG, "Try to go to LessonList activity");
                                startActivity(intent);
                            } else {
                                Snackbar snackbar = Snackbar.make(view, "The username/password combination does not match", Snackbar.LENGTH_LONG);
                                snackbar.setBackgroundTint(Color.rgb(255, 233, 217));
                                snackbar.setTextColor(Color.rgb(244, 86, 40));
                                snackbar.show();

                                //clear the fields
                                username.getText().clear();
                                password.getText().clear();
                            }
                        }
                    }

                }
            }
        });

        //set event listener for save login toggle
        saveLoginSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    //save the value as true
                    SharedPreferences preferences = getSharedPreferences("saveLoginToggle", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isLoginSaved", true);
                    editor.apply();

                } else if (!compoundButton.isChecked()) {
                    //save the value as false
                    SharedPreferences preferences = getSharedPreferences("saveLoginToggle", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isLoginSaved", false);
                    editor.apply();
                }
            }
        });


    }

    //check if user exists
    public Boolean isUsernamePresent(ArrayList<User> usersList, String username) {
        Boolean result = true;
        for (User i : usersList) {
            if (i.getUsername().compareTo(username) == 0) {
                result = false;
            }
        }
        return result;
    }

}