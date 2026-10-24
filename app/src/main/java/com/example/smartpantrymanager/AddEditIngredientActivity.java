package com.example.smartpantrymanager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class AddEditIngredientActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TextInputEditText etName, etQuantity, etExpiry;
    private Spinner spinnerUnit;
    private int ingredientId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        dbHelper = new DatabaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        etName = findViewById(R.id.etName);
        etQuantity = findViewById(R.id.etQuantity);
        etExpiry = findViewById(R.id.etExpiry);
        spinnerUnit = findViewById(R.id.spinnerUnit);

        // Setup unit spinner
        String[] units = {"kg", "g", "lb", "oz", "L", "ml", "cup", "tbsp", "tsp", "piece", "whole"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(adapter);

        // Check if editing existing ingredient
        ingredientId = getIntent().getIntExtra("ingredient_id", -1);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(ingredientId == -1 ? "Add Ingredient" : "Edit Ingredient");
        }
        if (ingredientId != -1) {
            loadIngredientData();
        }

        // Setup date picker
        etExpiry.setOnClickListener(v -> showDatePicker());

        findViewById(R.id.btnSave).setOnClickListener(v -> saveIngredient());
        findViewById(R.id.btnCancel).setOnClickListener(v -> finish());
    }

    private void loadIngredientData() {
        PantryItem item = dbHelper.getPantryItem(ingredientId);
        if (item != null) {
            etName.setText(item.getName());
            etQuantity.setText(String.valueOf(item.getQuantity()));
            etExpiry.setText(item.getExpiryDate());

            // Find and select the unit in spinner
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinnerUnit.getAdapter();
            int position = adapter.getPosition(item.getUnit());
            if (position >= 0) {
                spinnerUnit.setSelection(position);
            }
        }
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, month1, dayOfMonth) -> {
                    String date = String.format("%04d-%02d-%02d", year1, month1 + 1, dayOfMonth);
                    etExpiry.setText(date);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void saveIngredient() {
        String name = etName.getText().toString().trim();
        String quantityStr = etQuantity.getText().toString().trim();
        String unit = spinnerUnit.getSelectedItem().toString();
        String expiry = etExpiry.getText().toString().trim();

        // Validation
        if (name.isEmpty()) {
            etName.setError("Please enter an ingredient name");
            Toast.makeText(this, "Please enter an ingredient name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (quantityStr.isEmpty()) {
            etQuantity.setError("Please enter a quantity");
            Toast.makeText(this, "Please enter a quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        double quantity;
        try {
            quantity = Double.parseDouble(quantityStr);
            if (quantity <= 0) {
                etQuantity.setError("Quantity must be greater than 0");
                Toast.makeText(this, "Quantity must be greater than 0", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            etQuantity.setError("Please enter a valid number");
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
            return;
        }

        PantryItem item = new PantryItem();
        item.setName(name);
        item.setQuantity(quantity);
        item.setUnit(unit);
        item.setExpiryDate(expiry.isEmpty() ? null : expiry);

        if (ingredientId == -1) {
            // Add new ingredient
            dbHelper.addPantryItem(item);
            Toast.makeText(this, "Ingredient added successfully", Toast.LENGTH_SHORT).show();
        } else {
            // Update existing ingredient
            item.setId(ingredientId);
            dbHelper.updatePantryItem(item);
            Toast.makeText(this, "Ingredient updated successfully", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
