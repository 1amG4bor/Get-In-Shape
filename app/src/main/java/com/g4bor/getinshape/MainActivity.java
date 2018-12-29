package com.g4bor.getinshape;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.TrainingAdapter;
import model.TrainingItem;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager gridLayoutManager;
    private TrainingAdapter adapter;
    private List<TrainingItem> listItems;
    private FloatingActionButton btn_NewTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listItems = new ArrayList<>();
        fillUpList(listItems);

        gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView = findViewById(R.id.training_list);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new TrainingAdapter(this, listItems);
        adapter.setOnClickListener(new TrainingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(view.getContext(), position + " has clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, TrainingActivity.class);
                intent.putExtra("title", listItems.get(position).getTitle());
                intent.putExtra("imagename", listItems.get(position).getImageName());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        btn_NewTraining = findViewById(R.id.fab_addTraining);
        btn_NewTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "'fab'-button has clicked!", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this, NewTrainingActivity.class);
//                startActivity(intent);
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab_addTraining);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Action will coming...", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }

    private void fillUpList(List<TrainingItem> listItems) {
        listItems.add(new TrainingItem("Pull ups", getDrawable(R.drawable.weight), "pullup"));
        listItems.add(new TrainingItem("Exercises on the floor", getDrawable(R.drawable.weight), "carpet"));
        listItems.add(new TrainingItem("Advanced dips", getDrawable(R.drawable.weight), "dips"));
        listItems.add(new TrainingItem("Work with weights", getDrawable(R.drawable.weight), "woman_with_weight"));
        listItems.add(new TrainingItem("Climb up!", getDrawable(R.drawable.weight), "rope"));
        listItems.add(new TrainingItem("Pull ups", getDrawable(R.drawable.weight), "pullup"));
        listItems.add(new TrainingItem("Exercises on the floor", getDrawable(R.drawable.weight), "carpet"));
        listItems.add(new TrainingItem("Advanced dips", getDrawable(R.drawable.weight), "dips"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
