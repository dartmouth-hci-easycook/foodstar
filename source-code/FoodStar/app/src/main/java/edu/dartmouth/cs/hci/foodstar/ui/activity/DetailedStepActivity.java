package edu.dartmouth.cs.hci.foodstar.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import edu.dartmouth.cs.hci.foodstar.R;
import edu.dartmouth.cs.hci.foodstar.model.RecipeStep;

public class DetailedStepActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA = "INTENT_EXTRA";
    private RecipeStep mRecipeStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_step);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbar);
        mRecipeStep = (RecipeStep)getIntent().getSerializableExtra(INTENT_EXTRA);

        toolbar.setTitle("Step Description");
        ImageView detailImage = (ImageView) findViewById(R.id.detail_step_image);
        TextView detailText = (TextView) findViewById(R.id.detail_step_text);

        detailImage.setImageResource(mRecipeStep.uriLarge);
        detailText.setText(mRecipeStep.detailedDescription);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

}
