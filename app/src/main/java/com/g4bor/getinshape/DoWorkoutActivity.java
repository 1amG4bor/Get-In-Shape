package com.g4bor.getinshape;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

import adapter.CounterTask;
import adapter.DataImport;
import at.grabner.circleprogress.CircleProgressView;
import model.Training;

public class DoWorkoutActivity extends AppCompatActivity {
    // Temporary solution to create workout data
    Training training = DataImport.getInstance().loadTestTraining();
    // todo: get Training from 'extras'
    private Bundle      extras;
    private TextView    trainingName;
    private ImageView   workoutImage;
    private TextView    workoutTitle;
    private TextView    nextTitle;
    private CircleProgressView progressView;
    private Button btn_togglePlay;
    private Button btn_skip;
    private Timer timer;
    private CounterTask task;
    private boolean isRunning = false;
    private float actualTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_workout);

        Resources resources = getApplicationContext().getResources();
        // region Initialization
        trainingName = findViewById(R.id.dw_training_name);
        workoutImage = findViewById(R.id.dw_workout_image);
        workoutTitle = findViewById(R.id.dw_workout_title);
        nextTitle = findViewById(R.id.dw_nextTitle);
        progressView = findViewById(R.id.progressBar);
        progressView.setBarColor(Color.RED, Color.YELLOW, Color.GREEN);
        // endregion
        extras = getIntent().getExtras();
        // todo: get the info from the database!
        reset_UI(0);

        btn_togglePlay = findViewById(R.id.btn_togglePlay);
        btn_togglePlay.setOnClickListener(e -> {
            if(!isRunning) {
                btn_togglePlay.setBackground(getResources().getDrawable(R.drawable.ic_pause_black_24dp));
                btn_togglePlay.setBackgroundTintList(ColorStateList.valueOf(0xFF555555));
                start(actualTime, 45);
                isRunning = true;
            } else {
                btn_togglePlay.setBackground(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
//                btn_togglePlay.setBackgroundTintList(ColorStateList.valueOf(0x00701a));

                pause();
                isRunning = false;
            }
        });

        btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(e -> {
            progressView.setValue(0);
            progressView.setText("0:00");
            pause();
        });
    }

    private void reset_UI(int id) {
        actualTime = training.getTaskList().get(0).getTime();
        trainingName.setText(training.getName());
//        workoutImage.setBackground();
        workoutTitle.setText(training.getTaskList().get(0).getName());
        nextTitle.setText(training.getTaskList().get(1).getName());
        progressView.setValue(actualTime);
        // todo: create time calculator
        progressView.setText("0:45");
    }

    public void start(float sec, int max) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timer = new Timer();
                task = new CounterTask(progressView, timer);
                task.setCounter(true, 0, max, (int) sec, (int) max/5);
                timer.schedule(task, 0, 100);
            }
        });
    }

    private void pause() {
        actualTime = progressView.getCurrentValue();
        timer.cancel();
    }
}
