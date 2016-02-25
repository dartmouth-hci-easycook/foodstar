package edu.dartmouth.cs.hci.foodstar.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import edu.dartmouth.cs.hci.foodstar.R;

public class DetailedStepActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_step);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Recipe 1");
        toolbar.setNavigationIcon(android.R.drawable.ic_media_previous);

    }

}
