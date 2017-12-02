package com.example.android.displayjoke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    /*
     * Constants
     */

    private static final String JOKE_INTENT_KEY = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        // Declare an error message if no string is received
        String joke = "No string received";

        // Get the joke from the
        if(getIntent().getExtras().containsKey(JOKE_INTENT_KEY)) {
            joke = getIntent().getExtras().getString(JOKE_INTENT_KEY);
        }

        // Populate the TextView with the joke sent as extra
        TextView textView = findViewById(R.id.joke_display);
        textView.setText(joke);
    }
}
