package com.example.smartpantrymanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnClearData = findViewById(R.id.btnClearData);
        Button btnBack = findViewById(R.id.btnBack);

        btnClearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClearDataDialog();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showClearDataDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Clear All Data")
                .setMessage("Are you sure you want to delete all pantry items? This action cannot be undone.")
                .setPositiveButton("Yes", (dialog, which) -> {
                    DatabaseHelper dbHelper = new DatabaseHelper(SettingsActivity.this);
                    dbHelper.clearAllData();
                    Toast.makeText(SettingsActivity.this, "All data cleared", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }
}
