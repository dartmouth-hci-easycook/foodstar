package edu.dartmouth.cs.hci.foodstar.model;

import java.io.Serializable;

/**
 * Created by Dharmanshu on 2/24/16.
 */
public class RecipeStep implements Serializable {
    public String description;
    public String detailedDescription;
    public int uriLarge;
    public int timerMinutes;
    public boolean hasTimer = false;

    RecipeStep(String description , String detailedDescription , int uriLarge) {
        this.description = description;
        this.detailedDescription = detailedDescription;
        this.uriLarge = uriLarge;
        this.hasTimer = false;
    }

    RecipeStep(String description , String detailedDescription , int uriLarge , boolean hasTimer , int timerMinutes) {
        this.description = description;
        this.detailedDescription = detailedDescription;
        this.uriLarge = uriLarge;
        this.hasTimer = hasTimer;
        timerMinutes =  timerMinutes;
    }
}
