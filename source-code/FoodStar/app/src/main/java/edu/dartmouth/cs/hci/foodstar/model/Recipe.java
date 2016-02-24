package edu.dartmouth.cs.hci.foodstar.model;

import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import edu.dartmouth.cs.hci.foodstar.R;

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

    public static List<Recipe> getHardCodedRecipes(Context context) {
        List<Recipe> result = new ArrayList<>();
        String[] array = context.getResources().getStringArray(R.array.recipes);
        for (int i = 0; i < array.length; i++) {
            result.add(new Recipe(i, array[i], 20));
        }
        return result;
    }

    public static List<Recipe> getFilteredRecipes(Context context, String filterText) {
        List<Recipe> resultAll = getHardCodedRecipes(context);
        List<Recipe> result = new ArrayList<>();
        for(Recipe recipe : resultAll){
            if(recipe.getRecipeName().toLowerCase().contains(filterText.toLowerCase())){
                result.add(recipe);
            }
        }
        return result;
    }
}
