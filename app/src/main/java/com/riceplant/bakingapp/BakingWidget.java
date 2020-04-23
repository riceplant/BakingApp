package com.riceplant.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.riceplant.bakingapp.activity.RecipeActivity;
import com.riceplant.bakingapp.activity.RecipeDetailsActivity;
import com.riceplant.bakingapp.model.Ingredient;
import com.riceplant.bakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.riceplant.bakingapp.fragment.RecipeDetailsFragment.recipes;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidget extends AppWidgetProvider {
    private static List<Ingredient> ingredientList = new ArrayList<>();
    private static String ingredient;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        ingredientList = recipes.getIngredients();

        Intent intent = new Intent(context, RecipeDetailsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
        remoteViews.setOnClickPendingIntent(R.id.widget_linear_layout, pendingIntent);

        StringBuilder stringBuilder = new StringBuilder();
        for(Ingredient ingredient : ingredientList){
            String quantity = String.valueOf(ingredient.getQuantity());
            String measure = ingredient.getMeasure();
            String ingredientName = ingredient.getIngredient();
            String line = "- " + quantity + " " + measure + " " + ingredientName;
            stringBuilder.append( line + "\n");
        }

        remoteViews.setTextViewText(R.id.ingredients_list_widget, stringBuilder.toString());
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

