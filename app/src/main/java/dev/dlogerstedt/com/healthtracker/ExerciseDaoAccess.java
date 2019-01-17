package dev.dlogerstedt.com.healthtracker;

//https://medium.freecodecamp.org/room-sqlite-beginner-tutorial-2e725e47bfab

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ExerciseDaoAccess {

    @Insert
    void insertExercise (Exercise exercise);

    @Insert
    void insertMultipleExercises (List<Exercise> exerciseList);

    @Query("SELECT * FROM Exercise WHERE exerciseId = :exerciseId")
    Exercise fetchOneExerciseById (long exerciseId);

    @Query("SELECT * FROM Exercise")
    List<Exercise> fetchExercises ();

    @Update
    void updateExercise (Exercise exercise);

    @Delete
    void deleteExercise (Exercise exercise);
}
