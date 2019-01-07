package com.g4bor.getinshape;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.DataImport;
import adapter.WorkoutAdapter;
import model.DifficultyLevel;
import model.Training;
import model.Workout;

public class TrainingActivity extends AppCompatActivity {
    DataImport dataImport = DataImport.getInstance();
    private Training training = dataImport.loadTestTraining();
    private WorkoutAdapter adapter;
    private StaggeredGridLayoutManager layoutManager;
    // UI
    private Bundle extras;
    private ImageView workoutImage;
    private TextView workoutName;
    private LinearLayout levelPanel;
    private TextView levelText;
    private RecyclerView workoutList;
    private Button btnStart;
    private Button btnEdit;

    private Map<String, Integer> lvlColor =new HashMap<String, Integer>() {
        {   put("BEGINNER", R.drawable.green_panel);
            put("INTERMEDIATE", R.drawable.yellow_panel);
            put("ADVANCED", R.drawable.red_panel); }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        extras = getIntent().getExtras();
        levelPanel = (LinearLayout) findViewById(R.id.levelPanel);
        setLevelPanel();
        setHeader();
        setWorkoutList();

        btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainingActivity.this, DoWorkoutActivity.class);
                intent.putExtra("title", workoutName.getText());
                startActivity(intent);
            }
        });
        btnEdit = findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Edit workout is not available yet!", Toast.LENGTH_LONG).show();

                /*Intent intent = new Intent(TrainingActivity.this, NewWorkoutActivity.class);
                intent.putExtra("title", workoutName.getText());
                startActivity(intent);*/
            }
        });

    }


    private void setLevelPanel() {
        Integer panelColor = lvlColor.get(extras.getString("level"));
        levelPanel.setBackgroundResource(panelColor);

        DifficultyLevel lvlName = DifficultyLevel.valueOf(extras.getString("level"));
        levelText = findViewById(R.id.levelText);
        levelText.setText(lvlName.toText());
    }

    private void setHeader() {
        Resources resources = getApplicationContext().getResources();
        workoutImage = findViewById(R.id.training_image);
        int drawableId = resources.getIdentifier(
                extras.getString("imagename"),
                "drawable",
                getBaseContext().getPackageName());
        try {
            workoutImage.setImageDrawable(resources.getDrawable(drawableId));
        } catch (Exception ignored) { }

        workoutName = findViewById(R.id.training_title);
        workoutName.setText(extras.getString("title"));
    }

    private void setWorkoutList() {
        workoutList = findViewById(R.id.workout_list);
        layoutManager = new StaggeredGridLayoutManager(1 , StaggeredGridLayoutManager.VERTICAL);
        workoutList.setLayoutManager(layoutManager);

        adapter = new WorkoutAdapter(training.getTaskList());
        workoutList.setAdapter(adapter);
    }
}
