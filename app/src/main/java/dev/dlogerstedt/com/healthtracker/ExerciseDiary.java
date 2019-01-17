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
    private List<String> exerciseStrings;
    ExerciseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary);

        db = Room.databaseBuilder(getApplicationContext(), ExerciseDatabase.class, "exercises").allowMainThreadQueries().build();

        List<Exercise> exercises = db.daoAccess().fetchExercises();
        exerciseStrings = new ArrayList<>();
        for (Exercise exercise : exercises) {
            exerciseStrings.add(exercise.toString());
        }

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
        Exercise pushUps = new Exercise("Push-Ups", (int)(Math.random() * 30), "Pushing Ones Self Off The Ground While On Hands and Toes");
        db.daoAccess().insertExercise(pushUps);
        System.out.println("added to database");
        for (Exercise exercise : db.daoAccess().fetchExercises()) System.out.println(exercise.getTitle());
        exerciseStrings.add(pushUps.toString());
        exerciseAdapter.notifyItemInserted(exerciseStrings.size() - 1);

    }

    public void onAddSitUpsClick (View v) {
        Exercise sitUps = new Exercise("Sit-Ups", (int)(Math.random() * 30), "Pushing Ones Self Off The Ground While On Hands and Toes");
        db.daoAccess().insertExercise(sitUps);
        System.out.println("added to database");
        for (Exercise exercise : db.daoAccess().fetchExercises()) System.out.println(exercise.getTitle());
        exerciseStrings.add(sitUps.toString());
        exerciseAdapter.notifyItemInserted(exerciseStrings.size() - 1);
    }
}
