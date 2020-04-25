package com.riceplant.bakingapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.riceplant.bakingapp.activity.RecipeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SimpleTests {

    @Rule
    public ActivityTestRule<RecipeActivity> mActivityRule = new ActivityTestRule<>(RecipeActivity.class);

    @Test
    public void placeholder_image_is_shown_correctly() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // go to stepsDetail Screen
        onView(allOf(withId(R.id.recipe_name), withText("Nutella Pie"))).perform(click());
        onView(allOf(withId(R.id.steps_short_description), withText("Starting prep"))).perform(click());

        // view assertion
        onView(withId(R.id.placeholder_image_view)).check(matches(isDisplayed()));
        onView(withId(R.id.step_detail_description)).check(matches(isDisplayed()));
    }

    @Test
    public void exoplayer_is_shown_correctly() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // go to stepsDetail Screen
        onView(allOf(withId(R.id.recipe_name), withText("Nutella Pie"))).perform(click());
        onView(allOf(withId(R.id.steps_short_description), withText("Recipe Introduction"))).perform(click());

        // view assertion
        onView(withId(R.id.exo_content_frame)).check(matches(isDisplayed()));
        onView(withId(R.id.step_detail_description)).check(matches(isDisplayed()));
    }
}
