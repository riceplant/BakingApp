package com.riceplant.bakingapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.riceplant.bakingapp.R;
import com.riceplant.bakingapp.adapter.RecipeAdapter;
import com.riceplant.bakingapp.model.Ingredient;
import com.riceplant.bakingapp.model.Recipe;
import com.riceplant.bakingapp.network.RecipeClient;
import com.riceplant.bakingapp.network.RecipeService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler{
    private static final String TAG = RecipeActivity.class.getSimpleName();

    @BindView(R.id.recipe_recycler_view)
    RecyclerView mRecyclerView;

    private RecipeAdapter mRecipeAdapter;
    private List<Recipe> recipes;

    public static final String MY_RECIPE = "myRecipe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        RecipeService service = RecipeClient.getRetrofit().create(RecipeService.class);

        Call<List<Recipe>> call = service.getAllRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (response.isSuccessful()) {
                    recipes = response.body();
                    generateDataList(recipes);
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(RecipeActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.v(TAG, t.toString());
            }
        });
    }

    private void generateDataList(List<Recipe> recipeList) {
        ButterKnife.bind(this);
        mRecipeAdapter = new RecipeAdapter(this, recipeList, RecipeActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecipeActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecipeAdapter);
    }


    @Override
    public void onClick(int adapterPosition) {
        Context context = this;
        Class detailClass = RecipeDetailsActivity.class;

        Intent detailsIntent = new Intent(context, detailClass);
        detailsIntent.putExtra(MY_RECIPE, recipes.get(adapterPosition));
        startActivity(detailsIntent);
    }
}
