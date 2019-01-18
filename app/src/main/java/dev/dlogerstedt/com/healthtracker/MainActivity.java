package dev.dlogerstedt.com.healthtracker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String CHANNEL_ID = "channelId";
    private static int noteId;
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
        createNotificationChannel();
        noteId = 0;

        Timer notificationTimer = new Timer();
        notificationTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                notificationsGo();
            }
        }, 5000, 5000);

    }

    public void onStopwatchGo (View v) {
        System.out.println("Stopwatch Go!");
        Intent stopwatchIntent = new Intent(this, Stopwatch.class);
        startActivity(stopwatchIntent);
    }

    public void onExerciseDiaryGo (View v) {
        System.out.println("Exercise Diary Go!");
        Intent exerciseDiaryIntent = new Intent(this, ExerciseDiary.class);
        startActivity(exerciseDiaryIntent);
    }

    public void onFingerExerciseGo (View v) {
        System.out.println("Finger Exercise Go!");
        Intent fingerExerciseIntent = new Intent(this, FingerExercises.class);
        startActivity(fingerExerciseIntent);
    }

    // camera on android found at android docs -> https://developer.android.com/training/camera/photobasics#java

    public void onProfilePictureGo (View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageCaptured = (Bitmap) extras.get("data");
            ImageView profilePic = findViewById(R.id.profile_pic_image);
            profilePic.setImageBitmap(imageCaptured);
        }
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

    public void notificationsGo () {
        NotificationCompat.Builder noteBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.negative)
                .setContentTitle("Water")
                .setContentText("Drink Water")
                .setStyle(new NotificationCompat.BigTextStyle()
                    .bigText("Hydrate Yo Self! Drink Water"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(noteId, noteBuilder.build());
        noteId++;
    }


    // Solution found at https://developer.android.com/training/notify-user/channels#java

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = (CHANNEL_ID);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
