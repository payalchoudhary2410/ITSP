package com.example.infi_project.data;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.infi_project.data.model.HighlightsProfile;
import com.example.infi_project.data.model.ProfileAll;

public class PagerAdapterProfile extends FragmentPagerAdapter {



    public PagerAdapterProfile(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                System.out.println("Hello");
                return new HighlightsProfile();
            case 1:
                return new ProfileAll();

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0: return "Higlights";
            case 1: return "All";

            default: return null;

        }
    }
}

