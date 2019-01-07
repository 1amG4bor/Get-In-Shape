package model;

import java.util.ArrayList;
import java.util.List;

public class Training {
    private String name;
    private int imageId;
    private List<Workout> taskList;
    private DifficultyLevel level;

    public Training(String name, int imageId, DifficultyLevel level) {
        this.name = name;
        this.imageId = imageId;
        this.level = level;
        taskList = new ArrayList<>();
    }

    public void addWorkout(Workout workout) {
        taskList.add(workout);
    }

    public void removeWorkout(int index) {
        taskList.remove(index);
    }

    //region Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public DifficultyLevel getLevel() {
        return level;
    }

    public void setLevel(DifficultyLevel level) {
        this.level = level;
    }

    public List<Workout> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Workout> taskList) {
        this.taskList = taskList;
    }
    //endregion
}
