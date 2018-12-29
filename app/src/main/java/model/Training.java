package model;

import java.util.ArrayList;
import java.util.List;

public class Training {
    private String name;
    private int imageId;
    private List<Workout> taskList;

    public Training(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
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

    public List<Workout> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Workout> taskList) {
        this.taskList = taskList;
    }
    //endregion
}
