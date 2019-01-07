package model;

import android.graphics.drawable.Drawable;

public class TrainingItem {
    private String title;
    private Drawable image;
    private String imageName;
    private DifficultyLevel level;

    public TrainingItem(String title, Drawable img, String imageName, DifficultyLevel level) {
        this.title = title;
        this.image = img;
        this.imageName = imageName;
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public DifficultyLevel getLevel() {
        return level;
    }

    public void setLevel(DifficultyLevel level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return getTitle() + "(" + getLevel() + ")";
    }
}
