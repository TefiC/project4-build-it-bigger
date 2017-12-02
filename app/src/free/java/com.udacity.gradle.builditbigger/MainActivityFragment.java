package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * A Fragments that contains a button to display jokes
 */
public class MainActivityFragment extends Fragment {

    /*
     * Constructor
     */

    public MainActivityFragment() {}

    /*
     * Methods
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        setupAd(root);
        return root;
    }

    /**
     * Sets up an Ad and starts loading it
     *
     * @param root The root view that will contain the Ad View
     */
    private void setupAd(View root) {
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }
}
