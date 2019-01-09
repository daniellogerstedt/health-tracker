package dev.dlogerstedt.com.healthtracker;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
    Stopwatch solution was found at https://stackoverflow.com/questions/4597690/android-timer-how-to and adapted from the best answer for how to create a timer.
 */

public class Stopwatch extends AppCompatActivity {

    TextView stopWatchView;
    long startTime = 0;
    long currentTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        stopWatchView = (TextView) findViewById(R.id.stop_watch_time);
        final Button startStopButton = findViewById(R.id.stop_watch_start);
        startStopButton.setText("Start");
        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startStopButton.getText().equals("Stop")) {
                    currentTime = System.currentTimeMillis() - startTime + currentTime;
                    stopWatchHandler.removeCallbacks(stopWatchRunner);
                    startStopButton.setText("Start");

                } else {
                    startTime = System.currentTimeMillis();
                    startStopButton.setText("Stop");
                    stopWatchHandler.postDelayed(stopWatchRunner, 0);
                }

            }
        });
    }

    Handler stopWatchHandler = new Handler();
    Runnable stopWatchRunner = new Runnable() {
        @Override
        public void run() {
            long millis = (System.currentTimeMillis() - startTime + currentTime);
            int seconds = (int)(millis/1000);
            int minutes = seconds/60;
            int hours = minutes/60;

            millis = millis%1000;
            seconds = seconds%60;
            minutes = minutes%60;
            hours = hours%24;

            stopWatchView.setText(String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, millis));
            stopWatchHandler.postDelayed(this, 50);

        }
    };

    @Override
    public void onPause() {
        super.onPause();
        stopWatchHandler.removeCallbacks(stopWatchRunner);
        Button startStopButton = findViewById(R.id.stop_watch_start);
        startStopButton.setText("Start");
    }

    public void onReset (View v) {
        currentTime = 0;
        stopWatchHandler.removeCallbacks(stopWatchRunner);
        TextView time = findViewById(R.id.stop_watch_time);
        time.setText("00:00:00:000");
        Button startStop = findViewById(R.id.stop_watch_start);
        startStop.setText("Start");
    }


}
