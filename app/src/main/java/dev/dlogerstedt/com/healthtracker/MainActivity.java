package dev.dlogerstedt.com.healthtracker;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;




public class MainActivity extends AppCompatActivity {


    ArrayList<Integer> images = new ArrayList<>();
    Integer currentImage = R.drawable.bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images.add(currentImage);
        images.add(R.drawable.newbitmap);
        images.add(R.drawable.grayscale);
        images.add(R.drawable.negative);
        images.add(R.drawable.randomized);
        images.add(R.drawable.rotatecolors);
        ImageView image = findViewById(R.id.current_image);
        image.setImageResource(currentImage);

    }

    public void onStopwatchGo (View v) {
        System.out.println("Stopwatch Go!");
        Intent stopwatchIntent = new Intent(this, Stopwatch.class);
        startActivity(stopwatchIntent);
    }

    public void onFingerExerciseGo (View v) {
        System.out.println("Finger Exercise Go!");
        Intent fingerExerciseIntent = new Intent(this, FingerExercises.class);
        startActivity(fingerExerciseIntent);
    }

    public void onNextImage (View v) {
        ImageView image = findViewById(R.id.current_image);
        int currentIndex = images.indexOf(currentImage);
        currentImage = currentIndex == images.size() - 1? images.get(0) : images.get(currentIndex + 1);
        image.setImageResource(currentImage);
    }
}
