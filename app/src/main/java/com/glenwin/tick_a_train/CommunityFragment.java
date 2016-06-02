package com.glenwin.tick_a_train;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CommunityFragment extends Fragment {

    public CommunityFragment(){}

    MediaPlayer mp = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_community, container, false);

        mp = MediaPlayer.create(getActivity(),R.raw.about);
        mp.start();
        return rootView;
    }
    @Override
    public void onPause() {
        mp.stop();
        super.onPause();
    }
}

