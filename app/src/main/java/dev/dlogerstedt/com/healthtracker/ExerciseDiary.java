package dev.dlogerstedt.com.healthtracker;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
// found walkthrough of recycler views in android docs at https://developer.android.com/guide/topics/ui/layout/recyclerview


public class ExerciseDiary extends AppCompatActivity {

    private RecyclerView exerciseList;
    private RecyclerView.Adapter exerciseAdapter;
    private RecyclerView.LayoutManager exerciseLayoutManager;
    ExerciseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary);

        db = Room.databaseBuilder(getApplicationContext(), ExerciseDatabase.class, "exercises").allowMainThreadQueries().build();

        List<Exercise> exercises = db.daoAccess().fetchExercises();
        String[] exerciseStrings = new String[exercises.size()];
        int count = 0;
        for (Exercise exercise : exercises) {
            exerciseStrings[count] = "Exercise Name: " + exercise.getTitle() + "\nExercise Description " + exercise.getDescription() + "\nNumber Completed: " + exercise.getQuantity();
            count++;
        }

        db.daoAccess().insertExercise(new Exercise("Push-Ups", ((int)Math.random() * 30), "Pushing Ones Self Off The Ground While On Hands and Toes"));

        exerciseList = findViewById(R.id.exercises_list);

        // recycler view won't resize for content
        exerciseList.setHasFixedSize(true);

        // linear layout manager
        exerciseLayoutManager = new LinearLayoutManager(this);
        exerciseList.setLayoutManager(exerciseLayoutManager);

        // the adapter for the recycler view
        exerciseAdapter = new ExerciseAdapter(exerciseStrings);
        exerciseList.setAdapter(exerciseAdapter);

    }

    public void onAddPushUpsClick (View v) {
        db.daoAccess().insertExercise(new Exercise("Push-Ups", ((int)Math.random() * 30), "Pushing Ones Self Off The Ground While On Hands and Toes"));

    }

    public void onAddSitUpsClick (View v) {
        db.daoAccess().insertExercise(new Exercise("Sit-Ups", ((int)Math.random() * 30), "Pushing Ones Self Off The Ground While On Hands and Toes"));

    }
}
