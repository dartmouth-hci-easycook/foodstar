package edu.dartmouth.cs.hci.foodstar.model;

/**
 * Created by Vishal Gaurav
 */
public class Filter {

    public int minProtein = 0;
    public int maxProtein = 1000;
    public int maxFat = 1000;
    public int minFat = 0;
    public int minCarb = 0;
    public int maxCarb = 1000;
    public int minTime = 1;
    public int maxTime = 1000;
    public int minRating = 0;

    public String filterText = null;


    public static boolean isFilterPassed(Recipe recipe, Filter filter) {
        return recipe.getRecipeName().toLowerCase().contains(filter.filterText)&&isCarbPassed(filter, recipe) && isProteinPassed(filter, recipe) && isFatPassed(filter, recipe) && isRatingPassed(filter, recipe) && isTimePassed(filter, recipe);
    }

    public static boolean isCarbPassed(Filter filter, Recipe recipe) {
        return recipe.carbs >= filter.minCarb && recipe.carbs <= filter.maxCarb;
    }

    public static boolean isFatPassed(Filter filter, Recipe recipe) {
        return recipe.fat >= filter.minFat && recipe.fat <= filter.maxFat;
    }

    public static boolean isProteinPassed(Filter filter, Recipe recipe) {
        return recipe.protein >= filter.minProtein && recipe.protein <= filter.maxProtein;
    }

    public static boolean isTimePassed(Filter filter, Recipe recipe) {
        return recipe.getDuration() >= filter.minTime && recipe.getDuration() <= filter.maxTime;
    }

    public static boolean isRatingPassed(Filter filter, Recipe recipe) {
        return recipe.getStars() >= filter.minRating;
    }


}
