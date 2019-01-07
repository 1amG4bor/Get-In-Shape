package model;

public enum DifficultyLevel {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED;


    public String toBigCapital() {
        return super.toString();
    }

    public String toText() {
        char first = super.toString().charAt(0);
        return first + super.toString().toLowerCase().substring(1);
    }
}
