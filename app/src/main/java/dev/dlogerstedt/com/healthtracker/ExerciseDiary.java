package dev.dlogerstedt.com.healthtracker;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

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

        getExercises();

    }

    public void onAddExerciseClick (View v) {
        TextView titleView = findViewById(R.id.title_input_field);
        TextView descriptionView = findViewById(R.id.description_input_field);
        TextView quantityView = findViewById(R.id.quantity_input_field);
        String title = titleView.getText().toString();
        String description = descriptionView.getText().toString();
        int quantity = Integer.parseInt(quantityView.getText().toString());
        Exercise theExercise = new Exercise(title, quantity, description);
        db.daoAccess().insertExercise(theExercise);
        exerciseStrings.add(theExercise.toString());
        exerciseAdapter.notifyItemInserted(exerciseStrings.size() - 1);
        titleView.setText("");
        descriptionView.setText("");
        quantityView.setText("");
    }

    public void getExercises() {
        RequestQueue reqQue = Volley.newRequestQueue(this);
        String backend = "https://secret-lake-95349.herokuapp.com/exercises";

        StringRequest request = new StringRequest(Request.Method.GET, backend, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                System.out.println(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.err.println(error);
            }
        }
        );

        reqQue.add(request);
    }

}
