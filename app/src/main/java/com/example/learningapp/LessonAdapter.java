package com.example.learningapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.learningapp.databinding.LessonListviewBinding;


import java.util.ArrayList;

public class LessonAdapter extends ArrayAdapter<Lesson> {

    private static final String TAG = "xDEBUG";

    private LessonListviewBinding binding;

    public LessonAdapter(Context context, ArrayList<Lesson> lessonArray) {
        super(context, 0, lessonArray);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lesson_listview, parent, false);
        }

        binding = LessonListviewBinding.bind(convertView);

        Lesson lesson = getItem(position);

        binding.lessonNumber.setText(String.valueOf(lesson.getLessonNumber()));
        binding.textViewListItemName.setText(lesson.getName());
        binding.textViewListItemLength.setText("Length:  " + lesson.lengthConverter(lesson.getLength()));

        if (lesson.isCompleted()) {
            binding.ivCheckMark.setVisibility(View.VISIBLE);
        } else {
            binding.ivCheckMark.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

}
