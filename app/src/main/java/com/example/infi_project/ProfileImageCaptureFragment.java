package com.example.infi_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileImageCaptureFragment extends Fragment {

    public String mobileText;
    private TextView hello;
    private static final String TAG= "Profile Image Capture:";


    public ProfileImageCaptureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Interest_Part activity= (Interest_Part) getActivity();
        assert activity != null;
        mobileText=activity.sendData();




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile_image_capture, container, false);
        hello=view.findViewById(R.id.hello);
        hello.setText(mobileText);
        return view;
    }
}
