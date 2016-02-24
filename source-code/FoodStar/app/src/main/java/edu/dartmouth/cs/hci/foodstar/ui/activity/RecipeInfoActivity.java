package edu.dartmouth.cs.hci.foodstar.ui.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;

import edu.dartmouth.cs.hci.foodstar.R;
import edu.dartmouth.cs.hci.foodstar.RecipeStepsActivity;
import edu.dartmouth.cs.hci.foodstar.model.Recipe;

public class RecipeInfoActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE = "extra_Recipe";

    private Toolbar mToolBar = null;
    private Recipe mRecipe = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecipe = (Recipe)getIntent().getSerializableExtra(EXTRA_RECIPE);
        setContentView(R.layout.activity_recipe_info);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        mToolBar.setTitle("Recipe 1");
        RatingBar mRatingBar = (RatingBar) findViewById(R.id.rbRating);
        mRatingBar.getProgressDrawable().setColorFilter(this.getBaseContext().getResources().getColor(R.color.green2), PorterDuff.Mode.SRC_ATOP);
//        mToolBar.setNavigationIcon(android.R.drawable.ic_media_previous);
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
                startActivity(new Intent(RecipeInfoActivity.this, RecipeStepsActivity.class));
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
