package dev.dlogerstedt.com.healthtracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

// Part of solution found at https://developer.android.com/guide/topics/ui/layout/recyclerview

class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private String[] exerciseDataset;

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {

        public TextView exerciseTextView;
        public ExerciseViewHolder(TextView v) {
            super(v);
            exerciseTextView = v;
        }
    }

    public ExerciseAdapter(String[] eData){
        exerciseDataset = eData;
    }

    @NonNull
    @Override
    public ExerciseAdapter.ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TextView exerciseTextView = (TextView)LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.exercise_text_view, viewGroup, false);

        ExerciseViewHolder exerViewHolder = new ExerciseViewHolder(exerciseTextView);
        return exerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder exerciseViewHolder, int i) {
        exerciseViewHolder.exerciseTextView.setText(exerciseDataset[i]);
    }

    @Override
    public int getItemCount() {
        return exerciseDataset.length;
    }
}
