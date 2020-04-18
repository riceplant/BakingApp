package com.riceplant.bakingapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.riceplant.bakingapp.R;
import com.riceplant.bakingapp.activity.StepDetailsActivity;
import com.riceplant.bakingapp.adapter.IngredientAdapter;
import com.riceplant.bakingapp.adapter.RecipeDetailsAdapter;
import com.riceplant.bakingapp.model.Ingredient;
import com.riceplant.bakingapp.model.Recipe;
import com.riceplant.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsFragment extends Fragment implements RecipeDetailsAdapter.RecipeDetailsAdapterOnClickHandler, IngredientAdapter.IngredientAdapterOnClickHandler {
    RecipeDetailsAdapter mAdapter;
    IngredientAdapter ingredientAdapter;

    public static Recipe recipes;
    private String mStepId;
    private String mShortDescription;

    private List<Step> stepList = new ArrayList<>();
    private List<Ingredient> ingredientList = new ArrayList<>();

    @BindView(R.id.recipe_details_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.ingredients_detail_rv)
    RecyclerView ingredientRecyclerView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_recipe_details, container, false);
        ButterKnife.bind(this, rootView);

        stepList = recipes.getSteps();
        ingredientList = recipes.getIngredients();

        if (stepList != null) {
            mStepId = stepList.get(0).getId().toString();
            mShortDescription = stepList.get(0).getShortDescription();
        }

        mAdapter = new RecipeDetailsAdapter(stepList, RecipeDetailsFragment.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        ingredientAdapter = new IngredientAdapter(ingredientList, RecipeDetailsFragment.this);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        ingredientRecyclerView.setLayoutManager(layoutManager1);
        ingredientRecyclerView.setAdapter(ingredientAdapter);

        return rootView;
    }

    @Override
    public void onClick(int adapterPosition) {
        Class stepDetailsClass = StepDetailsActivity.class;
        StepDetailsActivity.steps = stepList.get(adapterPosition);

        Intent stepDetailsIntent = new Intent(getActivity(), stepDetailsClass);
        startActivity(stepDetailsIntent);
    }
}
