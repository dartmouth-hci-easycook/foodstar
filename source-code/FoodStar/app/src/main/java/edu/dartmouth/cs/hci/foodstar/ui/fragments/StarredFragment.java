package edu.dartmouth.cs.hci.foodstar.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.dartmouth.cs.hci.foodstar.R;
import edu.dartmouth.cs.hci.foodstar.model.Recipe;
import edu.dartmouth.cs.hci.foodstar.ui.activity.RecipeInfoActivity;
import edu.dartmouth.cs.hci.foodstar.ui.adapters.FoodAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StarredFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StarredFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mRootView = null; // root view of inflated fragment
    private GridView mGvFoodList = null;
    private FoodAdapter mAdapter = null;
    private OnFragmentInteractionListener mListener;
    private List<Recipe> mListRecipe = new ArrayList<Recipe>();
    private static Context mContext;
    public StarredFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StarredFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StarredFragment newInstance(String param1, String param2) {
        StarredFragment fragment = new StarredFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView =  inflater.inflate(R.layout.fragment_starred, container, false);
        //setting the context
        mContext = getActivity();

        addHardCodeData();
        initViews();
        return mRootView;
    }

    private void initViews(){
        mGvFoodList = (GridView) mRootView.findViewById(R.id.gvFoodList);
        mAdapter = new FoodAdapter(getActivity(),mListRecipe);
        mGvFoodList.setAdapter(mAdapter);

        //setting the listeners for the individual item clicks

        mGvFoodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent recipeStepsIntent = new Intent(mContext,RecipeInfoActivity.class);
                recipeStepsIntent.putExtra(RecipeInfoActivity.EXTRA_RECIPE, mAdapter.getItem(position));
                mContext.startActivity(recipeStepsIntent);

            }
        });
        }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void addHardCodeData(){
        Random r = new Random();
        mListRecipe.clear();
        mListRecipe.addAll(Recipe.getHardCodedRecipes(getActivity()));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
