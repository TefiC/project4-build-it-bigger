package com.udacity.gradle.builditbigger.CustomAssertions;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.view.View;
import android.widget.TextView;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Custom assertion to check that a TextView is not empty
 */

public class TextViewNotEmptyFreeAssertion implements ViewAssertion {

    /*
     * Methods
     */

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        TextView textView = (TextView) view;
        assertThat(textView.getText().length(), greaterThan(0));
    }
}
