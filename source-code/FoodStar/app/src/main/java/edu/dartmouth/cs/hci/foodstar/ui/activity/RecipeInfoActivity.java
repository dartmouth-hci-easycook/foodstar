package edu.dartmouth.cs.hci.foodstar.ui.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import edu.dartmouth.cs.hci.foodstar.R;
import edu.dartmouth.cs.hci.foodstar.model.Recipe;

public class RecipeInfoActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE = "extra_Recipe";

    private Toolbar mToolBar = null;
    private Recipe mRecipe = null;
    private TextView mTxtTime;
    private ImageView mImgRecipe;
    private RatingBar mRbRatings;
    private TextView mProteinText;
    private TextView mCarbsText;
    private TextView mFatText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecipe = (Recipe)getIntent().getSerializableExtra(EXTRA_RECIPE);
        Log.e("VVV","name = " + mRecipe.getRecipeName());
        Log.e("VVV","time = " + mRecipe.getDuration());
        Log.e("VVV", "image = " + mRecipe.getUriThumb());
        Log.e("VVV", "rating = " + mRecipe.getStars());

        setContentView(R.layout.activity_recipe_info);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mTxtTime = (TextView)findViewById(R.id.txtTime);
        mRbRatings = (RatingBar)findViewById(R.id.rbRating);
        mImgRecipe = (ImageView)findViewById(R.id.imgRecipe);
        mProteinText = (TextView) findViewById(R.id.txtProtein);
        mCarbsText = (TextView) findViewById(R.id.txtCarbs);
        mFatText = (TextView) findViewById(R.id.txtFat);

        mImgRecipe.setBackgroundResource(mRecipe.getUriThumb());
        mToolBar.setTitle(mRecipe.getRecipeName());
        mRbRatings.setNumStars(mRecipe.getStars());
        mProteinText.setText(Integer.toString(mRecipe.protein) + "G");
        mFatText.setText(Integer.toString(mRecipe.fat) + "G");
        mCarbsText.setText(Integer.toString(mRecipe.carbs) + "G");

        setSupportActionBar(mToolBar);
        mTxtTime.setText(mRecipe.getDuration() + "min");
        RatingBar mRatingBar = (RatingBar) findViewById(R.id.rbRating);
        mRatingBar.getProgressDrawable().setColorFilter(this.getBaseContext().getResources().getColor(R.color.green2), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipe_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_start:{
                Intent recipeStepsIntent = new Intent(RecipeInfoActivity.this , RecipeStepsActivity.class);
                recipeStepsIntent.putExtra(RecipeStepsActivity.INTENT_RECIPE , mRecipe);
                this.startActivity(recipeStepsIntent);
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
