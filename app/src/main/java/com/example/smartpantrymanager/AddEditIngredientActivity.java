package com.example.smartpantrymanager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class AddEditIngredientActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText etName, etQuantity, etExpiry, etUnit;
    private Spinner spinnerUnit;
    private int ingredientId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        dbHelper = new DatabaseHelper(this);

        etName = findViewById(R.id.etIngredientName);
        etQuantity = findViewById(R.id.etQuantity);
        etUnit = findViewById(R.id.etUnit);
        etExpiry = findViewById(R.id.etExpiryDate);

        // Check if editing existing ingredient
        ingredientId = getIntent().getIntExtra("ITEM_ID", -1);
        if (ingredientId != -1) {
            loadIngredientData();
        }

        // Setup date picker
        etExpiry.setOnClickListener(v -> showDatePicker());

        findViewById(R.id.btnSave).setOnClickListener(v -> saveIngredient());
        findViewById(R.id.btnCancel).setOnClickListener(v -> finish());
    }

    private void loadIngredientData() {
        String name = getIntent().getStringExtra("ITEM_NAME");
        int quantity = getIntent().getIntExtra("ITEM_QUANTITY", 0);
        String unit = getIntent().getStringExtra("ITEM_UNIT");
        String expiry = getIntent().getStringExtra("ITEM_EXPIRY");

        if (name != null) {
            etName.setText(name);
            etQuantity.setText(String.valueOf(quantity));
            etUnit.setText(unit);
            etExpiry.setText(expiry);
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
        String unit = etUnit.getText().toString().trim();
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

        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
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
}
