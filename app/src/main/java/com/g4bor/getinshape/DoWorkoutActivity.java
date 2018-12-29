package com.g4bor.getinshape;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import adapter.CounterTask;
import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;

public class DoWorkoutActivity extends AppCompatActivity {
    private Bundle      extras;
    private ImageView   workoutImage;
    private TextView    workoutTitle;
    private TextView    nextTitle;
    private CircleProgressView progressView;
    private Timer timer = new Timer();
    private CounterTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_workout);

        Resources resources = getApplicationContext().getResources();
        extras = getIntent().getExtras();
        workoutImage = findViewById(R.id.workout_image);
        workoutTitle = findViewById(R.id.workout_title);
        workoutTitle.setText(extras.getString("title", resources.getString(R.string.workoutTitle)));
        nextTitle = findViewById(R.id.nextTitle);

        progressView = findViewById(R.id.progressBar);
        progressView.setBarColor(Color.RED, Color.YELLOW);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                task = new CounterTask(progressView, timer);
                int sec = 45;
                task.setCounter(true, sec, 0,sec/5);
                timer.schedule(task, 1000, 1000);
            }
        });
    }
}
