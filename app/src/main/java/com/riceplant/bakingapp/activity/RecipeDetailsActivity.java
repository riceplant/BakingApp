package com.riceplant.bakingapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.riceplant.bakingapp.R;
import com.riceplant.bakingapp.adapter.IngredientAdapter;
import com.riceplant.bakingapp.model.Ingredient;
import com.riceplant.bakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsActivity extends AppCompatActivity implements IngredientAdapter.IngredientAdapterOnClickHandler{
    private static final String TAG = RecipeDetailsActivity.class.getSimpleName();

    private Recipe recipes;
    private List<Recipe> recipeList;

    private String recipeName;
    private String ingredient;
    private Double quantity;
    private String measure;
    private List<Ingredient> ingredientList = new ArrayList<>();
    IngredientAdapter mAdapter;

    @Nullable
    @BindView(R.id.ingredients)
    TextView ingredientsTV;
    @Nullable
    @BindView(R.id.quantity)
    TextView quantityTV;
    @BindView(R.id. recipe_details_rv)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        Intent intentToCatch = getIntent();
        recipes = intentToCatch.getParcelableExtra(RecipeActivity.MY_RECIPE);

        recipeName = recipes.getName();
        ingredientList = recipes.getIngredients();
        if (ingredientList != null) {
            ingredient = ingredientList.get(0).getIngredient();
            quantity = ingredientList.get(0).getQuantity();
            measure = ingredientList.get(0).getMeasure();
        } else {
            Log.v(TAG, "FAILING LOADING INGREDIENTSLIST");
        }
        setTitle(recipeName);

        ButterKnife.bind(this);

        mAdapter = new IngredientAdapter(this, ingredientList, RecipeDetailsActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecipeDetailsActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(int adapterPosition) {

    }
}
