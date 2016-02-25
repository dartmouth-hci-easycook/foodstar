package edu.dartmouth.cs.hci.foodstar.ui.activity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import edu.dartmouth.cs.hci.foodstar.R;
import edu.dartmouth.cs.hci.foodstar.model.Recipe;
import edu.dartmouth.cs.hci.foodstar.ui.adapters.ListViewAdapter;

public class RecipeStepsActivity extends ListActivity {
    public static final String INTENT_RECIPE = "INTENT_RECIPE";

    private static ListView mListView = null;
    private ListViewAdapter mListAdapter = null;
    private static Context mContext;
    private Recipe mRecipe = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);
        mListView = (ListView)findViewById(android.R.id.list);
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
                Intent detailedStepIntent = new Intent(mContext, DetailedStepActivity.class);
                mContext.startActivity(detailedStepIntent);
            }
        });
    }

    private String[] generateRecipeStepNumbers() {
        int length = mRecipe.recipeSteps.size();
        String[] recipeSteps = new String[length];
        for (int i=0; i<length; i++) {
            recipeSteps[i] = "Step " + Integer.toString(i+1);
            System.out.println(recipeSteps[i]);
        }
        return recipeSteps;
    }

    private String[] generateRecipeSteps() {
        int length = mRecipe.recipeSteps.size();
        String[] recipeSteps = new String[length];
        for (int i=0; i<length; i++) {
            recipeSteps[i] = mRecipe.recipeSteps.get(i).description;
        }
        return recipeSteps;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home_screen, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
