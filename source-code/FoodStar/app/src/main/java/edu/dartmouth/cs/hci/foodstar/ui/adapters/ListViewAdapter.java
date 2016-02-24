package edu.dartmouth.cs.hci.foodstar.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.dartmouth.cs.hci.foodstar.R;

/**
 * Created by nisha on 2/16/16.
 */
public class ListViewAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;
    private final String[] values1;
    private final Integer imageID;

    public ListViewAdapter(Context context, String[] values, String[] values1, Integer imageID) {
        super(context, R.layout.recipe_list_item, values);
        this.context = context;
        this.values = values;
        this.values1 = values1;
        this.imageID = imageID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.recipe_list_item, parent, false);
        TextView topTextView = (TextView) rowView.findViewById(R.id.top_text);
        TextView bottomTextView = (TextView) rowView.findViewById(R.id.bottom_text);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.more_details_image);

        topTextView.setText(values[position]);
        bottomTextView.setText(values1[position]);
        imageView.setImageResource(imageID);

        return rowView;
    }




}
