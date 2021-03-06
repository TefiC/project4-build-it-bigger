package com.udacity.gradle.builditbigger;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.CustomAssertions.TextViewNotEmptyPaidAssertion;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Test to verify that a joke is displayed correctly on the activity
 */

@RunWith(AndroidJUnit4.class)
public class JokeIsDisplayedPaidTest {

    /*
     * Activity
     */

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class, true, true);

    /*
     * Tests
     */

    @Test
    public void jokeIsDisplayedCorrectly() {
        onView(ViewMatchers.withId(R.id.tell_joke_button)).perform(click());

        // Handle asynchronous request
        registerIdlingResource();
        onView(withId(R.id.joke_display)).check(new TextViewNotEmptyPaidAssertion());
    }

    /*
     * Helper methods
     */

    /*
     * Register an idling resource to stop the test's execution until the
     * asynchronous operation is completed
     */
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(mActivityTestRule.getActivity().getIdlingResource());
    }
}