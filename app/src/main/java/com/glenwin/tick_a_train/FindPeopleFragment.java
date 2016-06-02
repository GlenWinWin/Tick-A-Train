package com.glenwin.tick_a_train;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.glenwin.adapter.ViewPagerAdapter;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FindPeopleFragment extends Fragment {

    ViewPager viewPager;
    PagerAdapter adapter;
    String[] rank;
    String[] transportation;
    int[] pictures_transportation;

    public FindPeopleFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_find_people, container, false);

        rank = new String[] { "1", "2", "3"};

        transportation = new String[] { "LRT 1", "LRT 2", "MRT 3"};

        pictures_transportation = new int[] { R.drawable.lrt1,R.drawable.lrt2,R.drawable.mrt3 };

        // Locate the ViewPager in viewpager_main.xml
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        // Pass results to ViewPagerAdapter Class
        adapter = new ViewPagerAdapter(getActivity(),rank,transportation,pictures_transportation);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);

        return rootView;
    }
}
