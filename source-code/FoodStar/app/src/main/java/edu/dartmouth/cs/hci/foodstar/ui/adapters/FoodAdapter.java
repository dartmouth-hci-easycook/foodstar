package edu.dartmouth.cs.hci.foodstar.ui.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

import edu.dartmouth.cs.hci.foodstar.R;
import edu.dartmouth.cs.hci.foodstar.model.Recipe;

/**
 * Created by Vishal Gaurav
 */
public class FoodAdapter extends BaseAdapter {

    private Context mContext = null;
    private List<Recipe> listRecipe = null;

    public FoodAdapter(Context context, List<Recipe> recipeList) {
        this.mContext = context;
        this.listRecipe = recipeList;
    }

    @Override
    public int getCount() {
        return listRecipe.size();
    }

    @Override
    public Recipe getItem(int position) {
        return listRecipe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = getRowView(convertView);
        RecipeViewHolder holder = getHolder(convertView);
        updateView(holder,position);
        return convertView;
    }

    private void updateView(RecipeViewHolder holder,int position){
        Recipe recipe = getItem(position);
        holder.mRatingBar.setNumStars(recipe.getStars());
        holder.txtRecipeName.setText(recipe.getRecipeName());
        holder.imgFoodThumb.setImageResource(recipe.getUriThumb());
        holder.mRatingBar.getProgressDrawable().setColorFilter(this.mContext.getResources().getColor(R.color.green2), PorterDuff.Mode.SRC_ATOP);
        holder.duration.setText(Integer.toString(recipe.getDuration()) + "m");
    }

    private int getRandomImage(int position){
        Random r = new Random();

        int id = position%4;
        int result = R.drawable.recipe4;
        switch (id){
            case 1:{
                result = R.drawable.recipe1;
            }
            break;
            case 2:{
                result = R.drawable.recipe2;
            }
            break;
            case 3:{
                result = R.drawable.recipe3;
            }
            break;
        }
        return result;
    }

    private View getRowView(View convertView){
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.row_grid_recipe,null);
            convertView.setTag(getNewTag(convertView));
        }
        return convertView;
    }
    private RecipeViewHolder getHolder(View convertView){
        return (RecipeViewHolder) convertView.getTag();
    }

    private RecipeViewHolder getNewTag(View view){
        RecipeViewHolder result = new RecipeViewHolder();
        result.txtRecipeName = (TextView)view.findViewById(R.id.txtRecipeName);
        result.imgFoodThumb = (ImageView)view.findViewById(R.id.imgFood);
        result.mRatingBar = (RatingBar)view.findViewById(R.id.rbRating);
        result.duration = (TextView)view.findViewById(R.id.duration);
        return  result;
    }

    private static class RecipeViewHolder {
        private TextView txtRecipeName;
        private ImageView imgFoodThumb;
        private RatingBar mRatingBar;
        private ImageView imgDuration;
        private TextView duration;
    }
}
