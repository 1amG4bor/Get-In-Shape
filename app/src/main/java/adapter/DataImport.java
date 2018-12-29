package adapter;

import com.g4bor.getinshape.R;

import java.util.ArrayList;
import java.util.List;

import model.Training;
import model.Workout;

public class DataImport {
    private static final DataImport instance = new DataImport();
    public static DataImport getInstance() {
        return instance;
    }

    private DataImport() {
    }

    public List<Training> loadTrainings(String filename) {
        List<Training> trainingList = new ArrayList<>();
        // TODO: implement data-import
        Training training = new Training("First", R.drawable.weight);
        training.addWorkout(new Workout("Test workout", 45));
        training.addWorkout(new Workout("Test workout-2", 30));
        trainingList.add(training);
        return trainingList;
    }
}
