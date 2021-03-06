package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.android.displayjoke.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    /*
     * Constants
     */

    private static final String TAG_COUNTING_IDLING_RESOURCE = "NEW_LOADER";

    /*
     * Fields
     */

    private Context mContext;

    // Ad
    private static InterstitialAd mInterstitialAd;

    // Views
    private ProgressBar mProgressBar;
    private RelativeLayout mMainActivityLayout;

    // Testing Idling resource
    public static CountingIdlingResource mIdlingResource = new CountingIdlingResource(TAG_COUNTING_IDLING_RESOURCE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign context and view
        mContext = this;
        assignViews();

        // Set up and start loading interstitial Ad
        setUpInterstitialAd();
        loadInterstitialAd();
        setInterstitialAdListener();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Method that executes an AsyncTask that launches
     * a new activity that displays the corresponding joke
     *
     * @param view The button pressed to display the joke
     */
    public void tellJoke(View view) {
        if (mInterstitialAd != null) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                // For testing idling resource
                startAsyncTask(mContext);
                loadInterstitialAd();
            }
        } else {
            // For testing idling resource
            startAsyncTask(mContext);
        }
    }

    /**
     * AsyncTask that loads a joke from the local server
     */
    class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

        /*
         * Fields
         */

        private Context context;
        private MyApi myApiService = null;

        /*
         * Methods
         */

        @Override
        protected void onPreExecute() {
            // Progress bar and layout visibility
            mProgressBar.setVisibility(View.VISIBLE);
            mMainActivityLayout.setVisibility(View.GONE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Pair<Context, String>... params) {

            if (myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver
                myApiService = builder.build();
            }

            context = params[0].first;

            try {
                return myApiService.tellJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Prepare intent
            Intent intent = new Intent(context, JokeActivity.class);
            intent.putExtra("joke", result);

            // Launch activity
            startActivity(intent);

            // For testing idling resource
            mIdlingResource.decrement();

            // Hide progress bar
            mProgressBar.setVisibility(View.GONE);
        }
    }

    /**
     * Only called from testing, creates and returns a new CountingIdlingResource
     * if one doesn't exist already. Else, it returns the current one.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new CountingIdlingResource(TAG_COUNTING_IDLING_RESOURCE);
        }
        return mIdlingResource;
    }

    @Override
    protected void onRestart() {
        // In case the layout is not visible when the user returns to the activity
        if (mMainActivityLayout.getVisibility() != View.VISIBLE) {
            mMainActivityLayout.setVisibility(View.VISIBLE);
        }
        super.onRestart();
    }

    /*
     * Helper methods
     */

    /**
     * Assigns the views that will be populated with data
     */
    private void assignViews() {
        mProgressBar = findViewById(R.id.joke_progress_bar);
        mMainActivityLayout = findViewById(R.id.main_activity_layout);
    }

    /**
     * Starts an AsyncTask that launches a new activity
     * and supplies a joke to be displayed on the new activity
     *
     * @param context The Activity Context
     */
    private void startAsyncTask(Context context) {
        // For testing idling resource
        mIdlingResource.increment();
        new EndpointsAsyncTask().execute(new Pair<Context, String>(context, ""));
    }

    /**
     * Sets up an interstitial Ad
     */
    private void setUpInterstitialAd() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
    }

    /**
     * Starts loading an interstitial Ad
     */
    private void loadInterstitialAd() {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    /**
     * Assigns a listener for the interstitial Ad to
     * handle the Ad's lifecycle events
     */
    private void setInterstitialAdListener() {
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                startAsyncTask(mContext);
                loadInterstitialAd();
            }
        });
    }
}

