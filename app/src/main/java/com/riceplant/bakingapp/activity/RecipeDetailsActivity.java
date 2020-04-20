package com.riceplant.bakingapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.riceplant.bakingapp.R;
import com.riceplant.bakingapp.adapter.IngredientAdapter;
import com.riceplant.bakingapp.adapter.RecipeDetailsAdapter;
import com.riceplant.bakingapp.fragment.RecipeDetailsFragment;
import com.riceplant.bakingapp.fragment.StepDetailsFragment;
import com.riceplant.bakingapp.model.Ingredient;
import com.riceplant.bakingapp.model.Recipe;
import com.riceplant.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.riceplant.bakingapp.activity.RecipeActivity.MY_RECIPE;

public class RecipeDetailsActivity extends AppCompatActivity {
    public static final String TAG = RecipeDetailsActivity.class.getSimpleName();

    public static Recipe recipes;
    public static boolean isTwoPane;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        getSupportActionBar().setTitle(recipes.getName());

        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.detailContainer) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.container, new RecipeDetailsFragment())
                    .commit();
        }

        if (isTwoPane) {
            fragmentManager.beginTransaction()
                    .replace(R.id.detailContainer, new StepDetailsFragment())
                    .commit();
        }
    }
}
