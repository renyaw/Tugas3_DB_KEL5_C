package com.example.fsm_classroom;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewClasses extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<ClassModal> courseModalArrayList;
    private DBHandler dbHandler;
    private ClassRVAdapter courseRVAdapter;
    private RecyclerView classesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_class);

        // initializing our all variables.
        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewClasses.this);

        // getting our course array
        // list from db handler class.
        courseModalArrayList = dbHandler.readClasses();

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new ClassRVAdapter(courseModalArrayList, ViewClasses.this);
        classesRV = findViewById(R.id.idRVClasses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewClasses.this, RecyclerView.VERTICAL, false);
        classesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        classesRV.setAdapter(courseRVAdapter);
    }
}
