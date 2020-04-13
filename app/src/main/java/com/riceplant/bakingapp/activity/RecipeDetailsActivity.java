package com.riceplant.bakingapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.riceplant.bakingapp.R;
import com.riceplant.bakingapp.adapter.RecipeDetailsAdapter;
import com.riceplant.bakingapp.model.Recipe;
import com.riceplant.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.riceplant.bakingapp.activity.RecipeActivity.MY_RECIPE;

public class RecipeDetailsActivity extends AppCompatActivity implements RecipeDetailsAdapter.RecipeDetailsAdapterOnClickHandler {
    public static final String TAG = RecipeDetailsActivity.class.getSimpleName();

    public static Recipe recipes;
    private ArrayList<Recipe> recipeArrayList;
    private List<Step> stepList = new ArrayList<>();

    private String recipeName;
    private String mStepId;
    private String mShortDescription;
    private RecipeDetailsAdapter mAdapter;

    @BindView(R.id.recipe_details_rv)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        recipeName = recipes.getName();
        stepList = recipes.getSteps();

        if (stepList != null) {
            mStepId = stepList.get(0).getId().toString();
            mShortDescription = stepList.get(0).getShortDescription();
        } else {
            Log.v(TAG, "FAILED LOADING STEPS");
        }

        setTitle(recipeName);

        ButterKnife.bind(this);

        mAdapter = new RecipeDetailsAdapter(this, stepList, RecipeDetailsActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecipeDetailsActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(int adapterPosition) {
        Context context = this;
        Class stepDetailsClass = StepDetailsActivity.class;
        // StepDetailsActivity.recipes = recipeArrayList.get(adapterPosition);
        StepDetailsActivity.steps = stepList.get(adapterPosition);

        Intent stepDetailsIntent = new Intent(context, stepDetailsClass);
        // stepDetailsIntent.putParcelableArrayListExtra(MY_RECIPE, recipeArrayList);
        startActivity(stepDetailsIntent);
    }
}
