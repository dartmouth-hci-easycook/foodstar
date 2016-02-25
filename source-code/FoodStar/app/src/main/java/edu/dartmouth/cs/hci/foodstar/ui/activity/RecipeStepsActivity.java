package edu.dartmouth.cs.hci.foodstar.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import edu.dartmouth.cs.hci.foodstar.R;
import edu.dartmouth.cs.hci.foodstar.model.Recipe;
import edu.dartmouth.cs.hci.foodstar.model.RecipeStep;
import edu.dartmouth.cs.hci.foodstar.ui.adapters.ListViewAdapter;

public class RecipeStepsActivity extends AppCompatActivity {

    private class RecipeAdapter extends BaseAdapter {
        private final Context context;
        private ArrayList<RecipeStep> recipeSteps;

        public RecipeAdapter(Context context, ArrayList<RecipeStep> recipeSteps) {
            this.context = context;
            this.recipeSteps = recipeSteps;
        }

        @Override
        public int getCount() {
            return recipeSteps.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.recipe_list_item, parent, false);
            TextView topTextView = (TextView) rowView.findViewById(R.id.top_text);
            TextView bottomTextView = (TextView) rowView.findViewById(R.id.bottom_text);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.more_details_image);

            topTextView.setText("Step " + Integer.toString(position + 1));
            bottomTextView.setText(recipeSteps.get(position).description);

            if (recipeSteps.get(position).detailedDescription.isEmpty()) {
                imageView.setVisibility(View.INVISIBLE);
            }
            return rowView;
        }
    }

    public static final String INTENT_RECIPE = "INTENT_RECIPE";

    private ListView mListView = null;
    private ListViewAdapter mListAdapter = null;
    private Context mContext;
    private Recipe mRecipe = null;
    private Toolbar mToolbar;
    private Button mRateButton;
    private RatingBar ratingBar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecipe = (Recipe) getIntent().getSerializableExtra(INTENT_RECIPE);
        setContentView(R.layout.activity_recipe_steps);
        mListView = (ListView) findViewById(android.R.id.list);


        //setting the context
        mContext = this;
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(mRecipe.getRecipeName());
        mToolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(mToolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initViews();
    }

    private void initViews() {

        // storing string resources into Array
//        String[] recipeSteps = generateRecipeStepNumbers();
//        String[] stepsNumber = generateRecipeSteps();
//        Integer imageID = R.drawable.arrow;
        RecipeAdapter adapter = new RecipeAdapter(this, mRecipe.recipeSteps);
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

        getMenuInflater().inflate(R.menu.menu_recipe_steps, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
            }
            break;
            case R.id.action_rate: {
                ShowDialog();
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void ShowDialog() {
        AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.dialog_rating,null);
        RatingBar rating = (RatingBar)findViewById(R.id.rating);
        popDialog.setTitle("Rate - " + mRecipe.getRecipeName());
        popDialog.setView(view);

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
