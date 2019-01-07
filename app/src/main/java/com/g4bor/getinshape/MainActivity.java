package com.g4bor.getinshape;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import adapter.TrainingListAdapter;
import model.DifficultyLevel;
import model.TrainingItem;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager gridLayoutManager;
    private TrainingListAdapter adapter;
    private List<TrainingItem> listItems;
    private List<TrainingItem> originalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView = findViewById(R.id.training_list);
        recyclerView.setLayoutManager(gridLayoutManager);

        listItems = new ArrayList<>();
        fillUpList(listItems);
        adapter = new TrainingListAdapter(listItems);
        adapter.setOnClickListener(new TrainingListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(view.getContext(), position + " has clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, TrainingActivity.class);
                intent.putExtra("title", listItems.get(position).getTitle());
                intent.putExtra("imagename", listItems.get(position).getImageName());
                intent.putExtra("level", listItems.get(position).getLevel().toString());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab_addTraining);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "This action is not available yet...", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void fillUpList(List<TrainingItem> listItems) {
        // Test data!
        List<String> workouts = Arrays.asList("Pull ups", "Exercises on the floor", "Advanced dips", "Work with weights", "Climb up!");
        List<DifficultyLevel> levels = Arrays.asList(DifficultyLevel.BEGINNER, DifficultyLevel.INTERMEDIATE, DifficultyLevel.ADVANCED);
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            String training = workouts.get(random.nextInt(workouts.size()));
            DifficultyLevel lvl = levels.get(random.nextInt(levels.size()));
            listItems.add(new TrainingItem(training, getDrawable(R.drawable.weight), "weight", lvl));
        }
        originalList = new ArrayList<>(listItems);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        String levelId = item.getTitle().toString();

        switch (levelId) {
            case "Beginner workouts":
                filteringWorkouts(DifficultyLevel.BEGINNER);
                break;
            case "Intermediate workouts":
                filteringWorkouts(DifficultyLevel.INTERMEDIATE);
                break;
            case "Advanced workouts":
                filteringWorkouts(DifficultyLevel.ADVANCED);
                break;
            case "ALL workouts":
                restoreList();
                adapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filteringWorkouts(DifficultyLevel level) {
        restoreList();
        List<TrainingItem> newList = listItems.stream().filter(i -> i.getLevel().equals(level)).collect(Collectors.toList());
        listItems.clear();
        listItems.addAll(newList);
        adapter.notifyDataSetChanged();
    }

    private void restoreList() {
        listItems.clear();
        listItems.addAll(originalList);
    }
}
