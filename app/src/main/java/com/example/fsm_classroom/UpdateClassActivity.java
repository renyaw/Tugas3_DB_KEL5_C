package com.example.fsm_classroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateClassActivity extends AppCompatActivity {

    // variables for our edit text, button, strings and dbhandler class.
    private EditText classNameEdt, buildingEdt, capacityEdt, classDescriptionEdt;
    private Button updateClassBtn,deleteClassBtn;
    private DBHandler dbHandler;
    String className, classDesc, capacity, building;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_class);

        // initializing all our variables.
        classNameEdt = findViewById(R.id.idEdtClassNo);
        buildingEdt = findViewById(R.id.idEdtBuilding);
        capacityEdt = findViewById(R.id.idEdtCapacity);
        classDescriptionEdt = findViewById(R.id.idEdtClassDescription);
        updateClassBtn = findViewById(R.id.idBtnUpdateClass);
        deleteClassBtn = findViewById(R.id.idBtnDelete);

        // on below line we are initialing our dbhandler class.
        dbHandler = new DBHandler(UpdateClassActivity.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        className = getIntent().getStringExtra("name");
        building = getIntent().getStringExtra("building");
        capacity = getIntent().getStringExtra("capacity");
        classDesc = getIntent().getStringExtra("description");

        // setting data to edit text
        // of our update activity.
        classNameEdt.setText(className);
        buildingEdt.setText(building);
        capacityEdt.setText(capacity);
        classDescriptionEdt.setText(classDesc);

        // adding on click listener to our update class button.
        updateClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update class
                // method and passing all our edit text values.
                dbHandler.updateClass(className, classNameEdt.getText().toString(), buildingEdt.getText().toString(), capacityEdt.getText().toString(), classDescriptionEdt.getText().toString());

                // displaying a toast message that our class has been updated.
                Toast.makeText(UpdateClassActivity.this, "Kelas Berhasil Diubah..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateClassActivity.this, ViewClasses.class);
                startActivity(i);
            }
        });
        // adding on click listener for delete button to delete our class.
        deleteClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our class.
                dbHandler.deleteClass(className);
                Toast.makeText(UpdateClassActivity.this, "Kelas Dihapus", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateClassActivity.this, ViewClasses.class);
                startActivity(i);
            }
        });

    }
}
