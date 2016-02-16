package edu.dartmouth.cs.hci.foodstar.model;

import android.net.Uri;

/**
 * Created by Vishal Gaurav
 */
public class Recipe {
    private long id;
    private String recipeName;
    private int duration;
    private Uri uriThumb;
    private Uri uriLarge;

    public Recipe(long id, String recipeName, int duration) {
        this.id = id;
        this.recipeName = recipeName;
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Uri getUriThumb() {
        return uriThumb;
    }

    public void setUriThumb(Uri uriThumb) {
        this.uriThumb = uriThumb;
    }

    public Uri getUriLarge() {
        return uriLarge;
    }

    public void setUriLarge(Uri uriLarge) {
        this.uriLarge = uriLarge;
    }
}
