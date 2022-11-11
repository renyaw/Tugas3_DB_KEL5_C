package com.example.fsm_classroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText classNoEdt, buildingEdt, capacityEdt, classDescriptionEdt;
    private Button addClassBtn,readClassBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing all our variables.
        classNoEdt = findViewById(R.id.idEdtClassNo);
        buildingEdt = findViewById(R.id.idEdtBuilding);
        capacityEdt = findViewById(R.id.idEdtCapacity);
        classDescriptionEdt = findViewById(R.id.idEdtClassDescription);
        addClassBtn = findViewById(R.id.idBtnAddClass);
        readClassBtn= findViewById(R.id.idBtnReadClass);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(MainActivity.this);

        // below line is to add on click listener for our add class button.
        addClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String classNo = classNoEdt.getText().toString();
                String building = buildingEdt.getText().toString();
                String capacity = capacityEdt.getText().toString();
                String classDescription = classDescriptionEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (classNo.isEmpty() && building.isEmpty() && capacity.isEmpty() && classDescription.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Data belum lengkap", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // class to sqlite data and pass all our values to it.
                dbHandler.addNewClass(classNo, building, capacity, classDescription);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Kelas berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                classNoEdt.setText("");
                buildingEdt.setText("");
                capacityEdt.setText("");
                classDescriptionEdt.setText("");
            }
        });


        readClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(MainActivity.this, ViewClasses.class);
                startActivity(i);
            }
        });
    }

}