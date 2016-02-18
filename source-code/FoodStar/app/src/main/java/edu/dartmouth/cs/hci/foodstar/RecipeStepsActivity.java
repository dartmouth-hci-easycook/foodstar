package edu.dartmouth.cs.hci.foodstar;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

import edu.dartmouth.cs.hci.foodstar.model.Recipe;
import edu.dartmouth.cs.hci.foodstar.ui.adapters.FoodAdapter;
import edu.dartmouth.cs.hci.foodstar.ui.adapters.ListViewAdapter;

public class RecipeStepsActivity extends ListActivity {

    private static ListView mListView = null;
    private ListViewAdapter mListAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initViews();
    }

    private void initViews() {
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };
        String[] values1 = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };

        // storing string resources into Array
        String[] recipeSteps = getResources().getStringArray(R.array.recipeSteps);
        String[] stepsNumber = getResources().getStringArray(R.array.stepsNumber);

        ListViewAdapter adapter = new ListViewAdapter(this, stepsNumber, recipeSteps);
        setListAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home_screen, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
