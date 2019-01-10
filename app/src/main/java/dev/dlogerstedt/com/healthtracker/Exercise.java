package dev.dlogerstedt.com.healthtracker;

//https://medium.freecodecamp.org/room-sqlite-beginner-tutorial-2e725e47bfab

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Timestamp;

@Entity
public class Exercise {
    @NonNull
    @PrimaryKey
    private long exerciseId;
    private String title;
    private int quantity;
    private String description;
    private Timestamp timestamp;

    public Exercise () {}

    public Exercise (String title, int quantity, String description) {
        this.title = title;
        this.quantity = quantity;
        this.description = description;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public String getTitle () {return this.title;}
    public int getQuantity () {return this.quantity;}
    public String getDescription () {return this.description;}
    public Timestamp getTimestamp () {return this.timestamp;}

}
