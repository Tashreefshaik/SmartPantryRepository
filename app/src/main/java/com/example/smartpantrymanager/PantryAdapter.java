package com.example.smartpantrymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class PantryAdapter extends ArrayAdapter<PantryItem> {

    private OnDeleteClickListener deleteListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(PantryItem item);
    }

    public PantryAdapter(Context context, List<PantryItem> items) {
        super(context, 0, items);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PantryItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pantry, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tvItemName);
        TextView tvQuantity = convertView.findViewById(R.id.tvItemQuantity);
        TextView tvExpiry = convertView.findViewById(R.id.tvItemExpiry);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        tvName.setText(item.getName());
        tvQuantity.setText(item.getQuantity() + " " + (item.getUnit() != null ? item.getUnit() : ""));
        tvExpiry.setText("Expires: " + item.getExpiryDate());

        btnDelete.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onDeleteClick(item);
            }
        });

        return convertView;
    }
}
