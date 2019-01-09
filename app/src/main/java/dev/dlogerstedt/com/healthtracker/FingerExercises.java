package dev.dlogerstedt.com.healthtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FingerExercises extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerexercises);
    }

    public void onCounterClick(View v) {
        TextView countDisplay = findViewById(R.id.count_the_presses);
        int theCount = Integer.parseInt(countDisplay.getText().toString());
        theCount += 1;
        countDisplay.setText(Integer.toString(theCount));
        TextView encourage = findViewById(R.id.encouragement_text);
        if (theCount%50 == 0) encourage.setText("Nice, you hit another fifty!");
        else if (theCount%10 == 0) encourage.setText("Ten more down.");
        else encourage.setText("");
    }
}
