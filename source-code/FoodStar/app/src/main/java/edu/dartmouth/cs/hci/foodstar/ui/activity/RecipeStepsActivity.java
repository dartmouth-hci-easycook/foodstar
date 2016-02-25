package edu.dartmouth.cs.hci.foodstar.ui.activity;

import android.app.AlertDialog;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

            TextView minutesView = (TextView) rowView.findViewById(R.id.minutes);
            minutesView.setText(recipeSteps.get(position).minutues);

            if (recipeSteps.get(position).detailedDescription.isEmpty()) {
                imageView.setVisibility(View.INVISIBLE);
            }

            if (recipeSteps.get(position).hasTimer) {
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageResource(R.drawable.timer);
            }
            return rowView;
        }
    }

    public static final String INTENT_RECIPE = "INTENT_RECIPE";
    public static final int WHAT_NEXT_TICK = 1;
    private ListView mListView = null;
    private ListViewAdapter mListAdapter = null;
    private Context mContext;
    private Recipe mRecipe = null;
    private Toolbar mToolbar;
    private Button mRateButton;
    private RatingBar ratingBar;
    private AlertDialog mAlarmDialog;
    private TextView mTxtTimeLeft;
    private ImageView mImgAlarm;

    private Handler mAlarmHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case WHAT_NEXT_TICK:{
                    int timeLeft = msg.arg1;
                    mTxtTimeLeft.setText((timeLeft< 10)?"0:0"+timeLeft + " secs left":"0:"+timeLeft + " secs left");
                    if(timeLeft > 0 ){
                        msg = Message.obtain();
                        msg.what = WHAT_NEXT_TICK;
                        msg.arg1 = --timeLeft;
                        mAlarmHandler.sendMessageDelayed(msg,1000);
                    }else{
                        mAlarmHandler.removeMessages(WHAT_NEXT_TICK);
                        mAlarmDialog.dismiss();
                        vibrate(getBaseContext());
                    }
                }
                break;
            }
            super.handleMessage(msg);
        }
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecipe = (Recipe) getIntent().getSerializableExtra(INTENT_RECIPE);
        setContentView(R.layout.activity_recipe_steps);

        mListView = (ListView)findViewById(android.R.id.list);

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
                if (recipeStep.hasTimer) {
                    showAlarmDialog(position, 10); // for demo purpose
                } else {
                    if (recipeStep.detailedDescription.isEmpty()) {
                        return;
                    } else {
                        Intent detailedStepIntent = new Intent(mContext, DetailedStepActivity.class);
                        detailedStepIntent.putExtra(DetailedStepActivity.INTENT_EXTRA, mRecipe.recipeSteps.get(position));
                        mContext.startActivity(detailedStepIntent);
                    }
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

    public void showAlarmDialog(final int position, int seconds){
        AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.dialog_alarm,null);
        mTxtTimeLeft = (TextView)view.findViewById(R.id.txtTimeLeft);
        mImgAlarm = (ImageView)view.findViewById(R.id.imgAlarm);
        mTxtTimeLeft.setText((seconds < 10) ? "0:0" + seconds + " secs left" : "0:" + seconds + " secs left");
        popDialog.setTitle("Step " + (position + 1) + " in Progress ! ");
        popDialog.setView(view);
        Message msg = mAlarmHandler.obtainMessage();
        msg.what = WHAT_NEXT_TICK;
        msg.arg1 = --seconds;
        mAlarmHandler.sendMessageDelayed(msg,1000);
        popDialog.setCancelable(false);
        // Button OK
        popDialog.setPositiveButton(android.R.string.cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mAlarmDialog.dismiss();
                        mAlarmHandler.removeMessages(WHAT_NEXT_TICK);
                    }
                });

        mAlarmDialog = popDialog.show();
    }

    public void ShowDialog() {
        AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.dialog_rating,null);
//        RatingBar rating = (RatingBar)findViewById(R.id.rating);
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
    private void vibrate(Context context){
        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
    }
}
