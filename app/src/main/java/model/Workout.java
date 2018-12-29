package model;

public class Workout {
    private String name;
    private boolean isTimedSet;
    private int time; // 'Full-time' or 'time for reputation'
    // Reputation based set
    private int set;

    public Workout(String name, int time) {
        isTimedSet=true;
        this.name = name;
        this.time = time;
    }

    public Workout(String name, int time, int set) {
        isTimedSet = false;
        this.name = name;
        this.time = time;
        this.set = set;
    }

    //region Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    //endregion
}
