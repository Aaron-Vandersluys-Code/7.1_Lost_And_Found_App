package com.example.lostandfoundapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity2 extends AppCompatActivity {

    // initialising EditText variables
    EditText nameEditText;
    EditText phoneNumberEditText;
    EditText descriptionEditText;
    EditText dateEditText;
    EditText locationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Creating a new instance of DBHelper class, to open and manage SQLite database.
        DBHelper dbHelper = new DBHelper(this);

        // initialising radio buttons for lost / found selection
        RadioButton radioButtonLost = findViewById(R.id.radioButtonLost);
        RadioButton radioButtonFound = findViewById(R.id.radioButtonFound);

        // Creating onClickListener's for radio buttons (and setting them to false) and save button post button.
        radioButtonLost.setOnClickListener(v -> radioButtonFound.setChecked(false));
        radioButtonFound.setOnClickListener(v -> radioButtonLost.setChecked(false));

        // Initialising save button and save button OnClickListener.
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> {

            // if...else statement to set the status of radio buttons depending on which is selected.
            String lostOrFound = "";
            if (radioButtonLost.isChecked()) {
                lostOrFound = "Lost";
            } else if (radioButtonFound.isChecked()) {
                lostOrFound = "Found";
            }

            // Linking user input fields to code, to later get user input.
            nameEditText = findViewById(R.id.name);
            phoneNumberEditText = findViewById(R.id.phoneNumber);
            descriptionEditText = findViewById(R.id.itemDescription);
            dateEditText = findViewById(R.id.dateLostFound);
            locationEditText = findViewById(R.id.locationLostFound);

            // Storing the user input fields as string variables.
            String name = nameEditText.getText().toString();
            String phoneNumber = phoneNumberEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            String date = dateEditText.getText().toString();
            String location = locationEditText.getText().toString();

            // Adding the user input to the SQLite database.
            dbHelper.addData(name, phoneNumber, description, date, location, lostOrFound);

            // Intent takes the user back to the homepage after pressing the save button and other operations are complete.
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent);
        });

    }

}