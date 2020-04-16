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
import com.riceplant.bakingapp.adapter.IngredientAdapter;
import com.riceplant.bakingapp.adapter.RecipeDetailsAdapter;
import com.riceplant.bakingapp.model.Ingredient;
import com.riceplant.bakingapp.model.Recipe;
import com.riceplant.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.riceplant.bakingapp.activity.RecipeActivity.MY_RECIPE;

public class RecipeDetailsActivity extends AppCompatActivity implements RecipeDetailsAdapter.RecipeDetailsAdapterOnClickHandler, IngredientAdapter.IngredientAdapterOnClickHandler {
    public static final String TAG = RecipeDetailsActivity.class.getSimpleName();

    public static Recipe recipes;
    private List<Step> stepList = new ArrayList<>();
    private List<Ingredient> ingredientList = new ArrayList<>();

    private String recipeName;
    private String mStepId;
    private String mShortDescription;
    private RecipeDetailsAdapter mAdapter;
    private IngredientAdapter ingredientAdapter;

    @BindView(R.id.recipe_details_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.ingredients_detail_rv)
    RecyclerView ingredientRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        recipeName = recipes.getName();
        stepList = recipes.getSteps();
        ingredientList = recipes.getIngredients();

        if (stepList != null) {
            mStepId = stepList.get(0).getId().toString();
            mShortDescription = stepList.get(0).getShortDescription();
        } else {
            Log.v(TAG, "FAILED LOADING STEPS");
        }

        setTitle(recipeName);

        ButterKnife.bind(this);

        mAdapter = new RecipeDetailsAdapter(stepList, RecipeDetailsActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecipeDetailsActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        ingredientAdapter = new IngredientAdapter(ingredientList, RecipeDetailsActivity.this);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(RecipeDetailsActivity.this);
        ingredientRecyclerView.setLayoutManager(layoutManager1);
        ingredientRecyclerView.setAdapter(ingredientAdapter);
    }

    @Override
    public void onClick(int adapterPosition) {
        Context context = this;
        Class stepDetailsClass = StepDetailsActivity.class;
        StepDetailsActivity.steps = stepList.get(adapterPosition);

        Intent stepDetailsIntent = new Intent(context, stepDetailsClass);
        startActivity(stepDetailsIntent);
    }
}
