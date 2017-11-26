package com.example.android.displayjoke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String joke = "No string received";

        if(getIntent().getExtras().containsKey("joke")) {
            joke = getIntent().getExtras().getString("joke");
        }

        TextView textView = (TextView) findViewById(R.id.joke_display);
        textView.setText(joke);

    }
}
