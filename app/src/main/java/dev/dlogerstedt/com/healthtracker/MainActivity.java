package dev.dlogerstedt.com.healthtracker;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;




public class MainActivity extends AppCompatActivity {


    ArrayList<ImageInfo> images = new ArrayList<>();
    ImageInfo currentImage = new ImageInfo(R.drawable.bitmap, "The Original Bitmap");

    private class ImageInfo {
        Integer imageRef;
        String imageCap;

        public ImageInfo (Integer i, String s) {
            this.imageRef = i;
            this.imageCap = s;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images.add(currentImage);
        images.add(new ImageInfo(R.drawable.newbitmap, "The New Bitmap"));
        images.add(new ImageInfo(R.drawable.grayscale, "The GrayScale Bitmap"));
        images.add(new ImageInfo(R.drawable.negative, "The Negative Bitmap"));
        images.add(new ImageInfo(R.drawable.randomized, "The Randomized Bitmap"));
        images.add(new ImageInfo(R.drawable.rotatecolors, "The Rotated Bitmap"));
        ImageView image = findViewById(R.id.current_image);
        image.setImageResource(currentImage.imageRef);
        TextView text = findViewById(R.id.image_caption_text);
        text.setText(currentImage.imageCap);
        TextView num = findViewById(R.id.image_caption_number);
        String count = 1 + " of " + images.size();
        num.setText(count);

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
        int currentIndex = images.indexOf(currentImage)  == images.size() - 1? 0 : images.indexOf(currentImage) + 1;
        currentImage = images.get(currentIndex);
        TextView numberCaption = findViewById(R.id.image_caption_number);
        TextView contentCaption = findViewById(R.id.image_caption_text);
        String count = (currentIndex+1) + " of " + images.size();
        numberCaption.setText(count);
        contentCaption.setText(currentImage.imageCap);
        image.setImageResource(currentImage.imageRef);
    }
}
