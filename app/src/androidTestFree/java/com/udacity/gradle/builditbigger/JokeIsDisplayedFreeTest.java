package com.udacity.gradle.builditbigger;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.CustomAssertions.TextViewNotEmptyFreeAssertion;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

/**
 * Test to verify that a joke is displayed correctly on the activity
 */

@RunWith(AndroidJUnit4.class)
public class JokeIsDisplayedFreeTest {

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

        try {
            // Wait for ad to load
            Thread.sleep(120000);

            // Perform a click on the "Tell Joke" button
            onView(withId(R.id.tell_joke_button)).perform(click());

            // Close the interstitial ad
            onView(allOf(withContentDescription("Interstitial close button"), isDisplayed())).perform(click());

            // Handle asynchronous joke request
            registerIdlingResource();

            // Check that the joke is displayed
            onView(withId(R.id.joke_display)).check(new TextViewNotEmptyFreeAssertion());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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