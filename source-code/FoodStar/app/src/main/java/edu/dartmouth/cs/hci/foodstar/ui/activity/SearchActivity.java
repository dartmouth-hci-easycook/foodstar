package edu.dartmouth.cs.hci.foodstar.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.dartmouth.cs.hci.foodstar.R;
import edu.dartmouth.cs.hci.foodstar.model.Recipe;
import edu.dartmouth.cs.hci.foodstar.ui.adapters.FoodAdapter;

public class SearchActivity extends AppCompatActivity {

    private GridView mGvSearchResults;
    private List<Recipe> mListRecipes = new ArrayList<>();
    private FoodAdapter mAdapter = null;
    private EditText mEdtSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        mGvSearchResults = (GridView) findViewById(R.id.gvSearchResults);
        mGvSearchResults.setEmptyView(findViewById(R.id.txtEmptyView));
        mAdapter = new FoodAdapter(SearchActivity.this,mListRecipes);
        mGvSearchResults.setAdapter(mAdapter);
        mGvSearchResults.setOnItemClickListener(onItemClickListener);
        mEdtSearch = (EditText)findViewById(R.id.edtSearch);
        mEdtSearch.setOnEditorActionListener(mEditorActionListener);
        //addHardCodeData();
        mAdapter.notifyDataSetChanged();
    }

    private void addHardCodeData() {
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            mListRecipes.add(new Recipe(mListRecipes.size(), "Recipe " + (i + 1), r.nextInt(100)));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(SearchActivity.this,RecipeInfoActivity.class));
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.search, menu);
        return false;
    }
    private TextView.OnEditorActionListener mEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            boolean result = false;
            if(v.getId() == R.id.edtSearch){
                startSearch();
            }
            return result;
        }
    };

    public void startSearch(){
        String searchText = mEdtSearch.getText().toString().trim().toLowerCase();
        if(searchText!= null && !searchText.isEmpty()){
            mListRecipes.clear();
            mListRecipes.addAll(Recipe.getFilteredRecipes(getBaseContext(),searchText));
            mAdapter.notifyDataSetChanged();
        }else{
            mEdtSearch.setError(getString(R.string.error_search_no_text));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
