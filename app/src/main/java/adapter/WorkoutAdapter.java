package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.g4bor.getinshape.R;

import java.util.List;

import model.Workout;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {
    private List<Workout> workoutList;

    public WorkoutAdapter(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercises_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WorkoutAdapter.ViewHolder viewHolder, int position) {
        Workout item = workoutList.get(position);
         viewHolder.exercise.setText(item.getName());
        if (item.isTimedSet()) {
            viewHolder.data.setText(String.valueOf(item.getTime()));
            viewHolder.dataType.setText(R.string.dataTypeSecond);
        } else {
            viewHolder.data.setText(String.valueOf(item.getSet()));
            viewHolder.dataType.setText(R.string.dataTypeReputation);
        }
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView exercise;
        public TextView data;
        public TextView dataType;

        public ViewHolder(View itemView) {
            super(itemView);
            exercise = itemView.findViewById(R.id.el_exercise);
            data = itemView.findViewById(R.id.el_data);
            dataType = itemView.findViewById(R.id.el_dataType);
        }
    }
}
