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
    private int uriThumb;
    private int uriLarge;

    public Recipe(long id, String recipeName, int duration) {
        this.id = id;
        this.recipeName = recipeName;
        this.duration = duration;
    }

    public Recipe(long id, String recipeName, int duration , int drawable , int stars) {
        this.id = id;
        this.recipeName = recipeName;
        this.duration = duration;
        this.uriThumb = drawable;
        this.uriLarge = drawable;
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

//    public Uri getUriThumb() {
//        return uriThumb;
//    }

//    public void setUriThumb(Uri uriThumb) {
//        this.uriThumb = uriThumb;
//    }

//    public Uri getUriLarge() {
//        return uriLarge;
//    }

//    public void setUriLarge(Uri uriLarge) {
//        this.uriLarge = uriLarge;
//    }

    public static List<Recipe> getHardCodedRecipes(Context context) {
        List<Recipe> result = new ArrayList<>();
        String[] array = context.getResources().getStringArray(R.array.recipes);
        result.add(new Recipe(0, array[1], 20 , R.drawable.recipe1 , 1));
        result.add(new Recipe(1, array[1], 20 , R.drawable.recipe2 , 2));
        result.add(new Recipe(2, array[2], 20 , R.drawable.recipe3 , 3));
        result.add(new Recipe(3, array[3], 20 , R.drawable.recipe4 , 2));
        result.add(new Recipe(4, array[4], 20 , R.drawable.recipe5 , 4));
        result.add(new Recipe(5, array[5], 20 , R.drawable.recipe6 , 5));
        result.add(new Recipe(6, array[6], 20 , R.drawable.recipe7 , 5));
        result.add(new Recipe(7, array[7], 20 , R.drawable.recipe8 , 3));
        result.add(new Recipe(8, array[8], 20 , R.drawable.recipe9 , 2));
        result.add(new Recipe(9, array[9], 20 , R.drawable.recipe10, 1));
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
