package com.g4bor.getinshape;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TrainingActivity extends AppCompatActivity {
    private Bundle extras;
    private ImageView workoutImage;
    private TextView workoutName;
    private Button btnStart;
    private Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        Resources resources = getApplicationContext().getResources();
        extras = getIntent().getExtras();
        workoutName = findViewById(R.id.training_title);
        workoutName.setText(extras.getString("title"));
        workoutImage = findViewById(R.id.training_image);
        int drawableId = resources.getIdentifier(
                extras.getString("imagename"),
                "drawable",
                getBaseContext().getPackageName());
        try {
            workoutImage.setImageDrawable(resources.getDrawable(drawableId));
        } catch (Exception ignored) { }

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
}
