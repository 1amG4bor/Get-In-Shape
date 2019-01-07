package adapter;

import com.g4bor.getinshape.R;

import java.util.ArrayList;
import java.util.List;

import model.DifficultyLevel;
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
        Training training = new Training("First", R.drawable.weight, DifficultyLevel.BEGINNER);
        training.addWorkout(new Workout("Test workout", 45));
        training.addWorkout(new Workout("Test workout-2", 30));
        trainingList.add(training);
        return trainingList;
    }

    public Training loadTestTraining() {
        Training training = new Training("6 pack Abs", R.drawable.weight, DifficultyLevel.BEGINNER);
        training.addWorkout(new Workout("High knee taps", 45));
        training.addWorkout(new Workout("Russian twist", 45));
        training.addWorkout(new Workout("Lying leg raises", 45));
        training.addWorkout(new Workout("Hip raises", 45));
        training.addWorkout(new Workout("Flutter kicks", 45));
        training.addWorkout(new Workout("Plank knees to elbow", 45));
        training.addWorkout(new Workout("Chair sit ups", 45));
        training.addWorkout(new Workout("Seated in & outs", 45));
        training.addWorkout(new Workout("Jumping jacks", 45));

        /*training.addWorkout(new Workout("Test workout", 45));
        training.addWorkout(new Workout("Test workout-2", 30));
        training.addWorkout(new Workout("Test-3", 3, 5));
        training.addWorkout(new Workout("Test-4", 5, 10));
        training.addWorkout(new Workout("Test-5", 3, 20));*/
        return training;
    }
}
