package edu.dartmouth.cs.hci.foodstar.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

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
        holder.txtRecipeName.setText(recipe.getRecipeName());
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
        result.imgDuration = (ImageView)view.findViewById(R.id.imgDuration);
        result.mRatingBar = (RatingBar)view.findViewById(R.id.rbRating);
        return  result;
    }

    private static class RecipeViewHolder {
        private TextView txtRecipeName;
        private ImageView imgFoodThumb;
        private RatingBar mRatingBar;
        private ImageView imgDuration;
    }

}
