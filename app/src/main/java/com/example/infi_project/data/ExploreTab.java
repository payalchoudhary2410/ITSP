package com.example.infi_project.data;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infi_project.AppMainPage;
import com.example.infi_project.R;
import com.example.infi_project.RecyclerViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Vector;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

public class ExploreTab extends Fragment {

    public String mobileText;
    private ArrayList<String> interestNames= new ArrayList<>();
    private static final String TAG= "Explore:";


    public ExploreTab() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppMainPage activity= (AppMainPage) getActivity();
        mobileText=activity.sendData();




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore_tab, container, false);
    }

    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState){

        interestNames.add("All");
        DatabaseReference reff;
        reff= FirebaseDatabase.getInstance().getReference().child("userDetails").child(mobileText);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
//                    Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
//                    Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
//                    int i = 0;
//                    while (iterator.hasNext()) {
//                        DataSnapshot next = (DataSnapshot) iterator.next();
//                        interestNames.add(Objects.requireNonNull(next.getValue()).toString());
//                        i = i + 1;
//                    }
                    String interestNoText=dataSnapshot.child("totalNoOfInterest").getValue().toString();
                    int interestNo=Integer.parseInt(interestNoText);
                    for (int i=0; i<interestNo; i++){
                        String interestNumber= String.valueOf(i);
                        String interest= dataSnapshot.child("userInterest").child(interestNumber).getValue().toString();
                        interestNames.add(interest);
                    }

                    initRecyclerView();
                }

                else {
                    Toast.makeText(getContext(), "DataSnapshot doesn't exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView");
        RecyclerView recyclerView = (RecyclerView)getView().findViewById(R.id.iconRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), interestNames);
        recyclerView.setAdapter(adapter);


    }


}
