package com.example.androidcomp1786;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidcomp1786.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    EditText date;
    EditText price;
    EditText reporter_name;

    Spinner propertySpinner;
    Spinner bedroomSpinner;
    Spinner furnitureSpinner;

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bind to layout item
        propertySpinner = findViewById(R.id.property_spinner);
        bedroomSpinner = findViewById(R.id.bedroom_spinner);
        furnitureSpinner = findViewById(R.id.furniture_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> propertyAdapter = ArrayAdapter.createFromResource(this,
                R.array.property_list, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> bedroomAdapter = ArrayAdapter.createFromResource(this,
                R.array.bedroom_list, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> furnitureAdapter = ArrayAdapter.createFromResource(this,
                R.array.furniture_list, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        propertyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bedroomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        furnitureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        propertySpinner.setAdapter(propertyAdapter);
        bedroomSpinner.setAdapter(bedroomAdapter);
        furnitureSpinner.setAdapter(furnitureAdapter);

        // bind to layout item
        date = findViewById(R.id.date);
        price = findViewById(R.id.price);
        reporter_name = findViewById(R.id.reporter_name);

        // set current date to date field
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = formatter.format(date);
        this.date.setText(currentDate);

        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitButtonClicked();
            }
        });
    }


    private void submitButtonClicked() {

        // get text from Spinner item
        String property = propertySpinner.getSelectedItem().toString();
        String bedroom = bedroomSpinner.getSelectedItem().toString();

        // get text from EditText item
        String date = this.date.getText().toString();
        String price = this.price.getText().toString();
        String reporter_name = this.reporter_name.getText().toString();

        // check if the value has input
        boolean propertyState = property.equals("Select an item");
        boolean bedroomState = bedroom.equals("Select an item");
        boolean dateState = date.trim().isEmpty();
        boolean priceState = price.trim().isEmpty();
        boolean reporterNameState = reporter_name.trim().isEmpty();

        if (propertyState || bedroomState || dateState || priceState || reporterNameState) {
            Toast submitToast = Toast.makeText(MainActivity.this, "Missing in required fields!", Toast.LENGTH_SHORT);
            submitToast.show();

            // get text view from spinner
            TextView propertyError = (TextView) propertySpinner.getSelectedView();
            TextView bedroomError = (TextView) bedroomSpinner.getSelectedView();

            if (propertyState) {
                propertyError.setError("Please select a property type");
            }
            if (bedroomState) {
                bedroomError.setError("Please select a bedroom type");
            }
            if (priceState) {
                this.price.setError("Please enter a price");
            }
            if (reporterNameState) {
                this.reporter_name.setError("Please enter a reporter's name");
            }
        } else {
            Toast submitToast = Toast.makeText(MainActivity.this, "Property submitted!", Toast.LENGTH_SHORT);
            submitToast.show();
        }
    }
}
