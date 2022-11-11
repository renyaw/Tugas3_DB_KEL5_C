package com.example.fsm_classroom;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassRVAdapter extends RecyclerView.Adapter<ClassRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<ClassModal> classModalArrayList;
    private Context context;

    // constructor
    public ClassRVAdapter(ArrayList<ClassModal> classModalArrayList, Context context) {
        this.classModalArrayList = classModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        ClassModal modal = classModalArrayList.get(position);
        holder.classNoTV.setText(modal.getClassNo());
        holder.classDescTV.setText(modal.getClassDescription());
        holder.classBuilding.setText(modal.getBuilding());
        holder.classCapacity.setText(modal.getCapacity());

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateClassActivity.class);

                // below we are passing all our values.
                i.putExtra("name", modal.getClassNo());
                i.putExtra("description", modal.getClassDescription());
                i.putExtra("building", modal.getBuilding());
                i.putExtra("capacity", modal.getCapacity());

                // starting our activity.
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return classModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView classNoTV, classDescTV, classBuilding, classCapacity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            classNoTV = itemView.findViewById(R.id.idTVClassNo);
            classDescTV = itemView.findViewById(R.id.idTVClassDescription);
            classBuilding = itemView.findViewById(R.id.idTVClassBuilding);
            classCapacity = itemView.findViewById(R.id.idTVClassCapacity);
        }
    }


}
