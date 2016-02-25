package edu.dartmouth.cs.hci.foodstar.ui.activity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;

import java.util.ArrayList;

import edu.dartmouth.cs.hci.foodstar.R;
import edu.dartmouth.cs.hci.foodstar.model.Recipe;
import edu.dartmouth.cs.hci.foodstar.model.RecipeStep;
import edu.dartmouth.cs.hci.foodstar.ui.adapters.ListViewAdapter;

public class RecipeStepsActivity extends ListActivity {
    public static final String INTENT_RECIPE = "INTENT_RECIPE";

    private static ListView mListView = null;
    private ListViewAdapter mListAdapter = null;
    private static Context mContext;
    private Recipe mRecipe = null;
    private Button mRateButton;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);
        mListView = (ListView)findViewById(android.R.id.list);

        // Rating button
        mRateButton = (Button) findViewById(R.id.btn_rate_me);
        mRateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ShowDialog();
            }
        });

        //setting the context
        mContext = this;
        mRecipe = (Recipe)getIntent().getSerializableExtra(INTENT_RECIPE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initViews();
    }

    private void initViews() {

        // storing string resources into Array
//        String[] recipeSteps = generateRecipeStepNumbers();
//        String[] stepsNumber = generateRecipeSteps();
//        Integer imageID = R.drawable.arrow;
        ListViewAdapter adapter = new ListViewAdapter(this, mRecipe.recipeSteps);
        mListView.setAdapter(adapter);

        //setting listview listener
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecipeStep recipeStep = mRecipe.recipeSteps.get(position);
                if (recipeStep.detailedDescription.isEmpty()) {
                    return;
                } else {
                    Intent detailedStepIntent = new Intent(mContext, DetailedStepActivity.class);
                    detailedStepIntent.putExtra(DetailedStepActivity.INTENT_EXTRA, mRecipe.recipeSteps.get(position));
                    mContext.startActivity(detailedStepIntent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home_screen, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void ShowDialog()
    {
        AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        RatingBar rating = new RatingBar(this);
        rating.setMax(5);

        popDialog.setIcon(android.R.drawable.btn_star_big_on);
        popDialog.setTitle("Vote!! ");
        popDialog.setView(rating);

        // Button OK
        popDialog.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                // Button Cancel
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        popDialog.create();
        popDialog.show();
    }
}
