package com.example.android.sinembookstoreinventory;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.sinembookstoreinventory.data.ProductContract;
import com.example.android.sinembookstoreinventory.data.ProductContract.ProductEntry;

public class ProductCursorAdapter extends CursorAdapter {

    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.name);
        int nameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
        String productName = cursor.getString(nameColumnIndex);
        nameTextView.setText(productName);
        LinearLayout listItemView = view.findViewById(R.id.list_item);

        int idColumnIndex = cursor.getColumnIndex(ProductEntry._ID);
        TextView priceTextView = view.findViewById(R.id.price);
        int priceColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRICE);
        String productPrice = cursor.getString(priceColumnIndex);
        priceTextView.setText(productPrice);

        final int id = cursor.getInt(idColumnIndex);
        final TextView quantityTextView = view.findViewById(R.id.quantity);
        final int quantityColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_QUANTITY);
        final String productQuantity = cursor.getString(quantityColumnIndex);
        quantityTextView.setText(productQuantity);


        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, EditorActivity.class);
                Uri currentProductUri = ContentUris.withAppendedId(ProductEntry.CONTENT_URI, id);
                intent.setData(currentProductUri);
                context.startActivity(intent);
            }
        });


        Button sell = view.findViewById(R.id.sell);
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int quantity = Integer.parseInt(productQuantity);

                if (quantity <= 0) {
                    Toast.makeText(context, String.valueOf(R.string.no_stock), Toast.LENGTH_SHORT).show();
                } else if (quantity > 0) {
                    quantity--;

                    String quantityString = Integer.toString(quantity);

                    ContentValues values = new ContentValues();
                    values.put(ProductEntry.COLUMN_QUANTITY, quantityString);

                    Uri currentProductUri = ContentUris.withAppendedId(ProductEntry.CONTENT_URI, id);

                    int rowsAffected = context.getContentResolver().update(currentProductUri, values, null, null);

                    if (rowsAffected != 0) {

                        quantityTextView.setText(quantityString);

                    }
                }
            }
        });


    }


}


