package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class PantryListActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private PantryAdapter adapter;
    private List<PantryItem> pantryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_list);

        dbHelper = new DatabaseHelper(this);
        pantryItems = new ArrayList<>();

        ListView listView = findViewById(R.id.listViewPantry);
        Button btnAdd = findViewById(R.id.btnAddIngredient);

        adapter = new PantryAdapter(this, pantryItems);
        listView.setAdapter(adapter);

        loadPantryItems();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PantryListActivity.this, AddEditIngredientActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PantryItem item = pantryItems.get(position);
                Intent intent = new Intent(PantryListActivity.this, AddEditIngredientActivity.class);
                intent.putExtra("ITEM_ID", item.getId());
                intent.putExtra("ITEM_NAME", item.getName());
                intent.putExtra("ITEM_QUANTITY", item.getQuantity());
                intent.putExtra("ITEM_UNIT", item.getUnit());
                intent.putExtra("ITEM_EXPIRY", item.getExpiryDate());
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PantryItem item = pantryItems.get(position);
                dbHelper.deletePantryItem(item.getId());
                loadPantryItems();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPantryItems();
    }

    private void loadPantryItems() {
        pantryItems.clear();
        pantryItems.addAll(dbHelper.getAllPantryItems());
        adapter.notifyDataSetChanged();
    }
}
