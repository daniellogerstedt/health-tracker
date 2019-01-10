package dev.dlogerstedt.com.healthtracker;

//https://medium.freecodecamp.org/room-sqlite-beginner-tutorial-2e725e47bfab

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities={Exercise.class}, version = 1, exportSchema = false)
public abstract class ExerciseDatabase extends RoomDatabase {
    public abstract ExerciseDaoAccess daoAccess();
}
