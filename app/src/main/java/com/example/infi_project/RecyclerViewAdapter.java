package com.example.infi_project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.infi_project.data.ExploreTab;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {

    private static  final String TAG="RecyclerViewIconAdapter";

    private ArrayList<String> interestNames= new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> interestNames) {
        this.interestNames = interestNames;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called");
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.interestlist, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(mContext)
                .asBitmap();
        holder.interest_name.setText(interestNames.get(position));
        holder.interest_name.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "onClick: clicked"+interestNames.get(position));
                        Toast.makeText(mContext, "Clicked"+interestNames.get(position), Toast.LENGTH_SHORT).show();
                    }
                }
        );


    }

    @Override
    public int getItemCount() {
        return interestNames.size();
    }

    public class  viewHolder extends RecyclerView.ViewHolder{
        TextView interest_name;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            interest_name=itemView.findViewById(R.id.interest_name);

        }
    }

}
