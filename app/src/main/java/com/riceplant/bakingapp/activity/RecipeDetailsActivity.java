package com.riceplant.bakingapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.riceplant.bakingapp.R;
import com.riceplant.bakingapp.model.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {
    private static final String TAG = RecipeDetailsActivity.class.getSimpleName();

    private Recipe recipes;

    private String recipeName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        Intent intentToCatch = getIntent();
        recipes = intentToCatch.getParcelableExtra(RecipeActivity.MY_RECIPE);

        recipeName = recipes.getName();
        Log.v(TAG, recipeName);

        setTitle(recipeName);
    }

}
