package com.example.infi_project.data;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.infi_project.PagerAdapter;
import com.example.infi_project.R;
import com.google.android.material.tabs.TabLayout;


public class ProfileTab extends Fragment {




    public TabLayout tabLayout;
    public ViewPager viewPager;
    public Toolbar toolbar;
    PagerAdapterProfile pagerAdapterprofile;

    public ProfileTab() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_tab, container, false);
    }

    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {

        tabLayout=getView().findViewById(R.id.tabLayout1);
        viewPager=getView().findViewById(R.id.pager1);
        pagerAdapterprofile=new PagerAdapterProfile(getFragmentManager());
        viewPager.setAdapter(pagerAdapterprofile);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



    }



}
