package com.example.android.sinembookstoreinventory;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.sinembookstoreinventory.data.ProductContract;

import org.w3c.dom.Text;

public class ProductCursorAdapter extends CursorAdapter {

    public ProductCursorAdapter(Context context, Cursor c){
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        int nameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
        String productName = cursor.getString(nameColumnIndex);
        nameTextView.setText(productName);

        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        int priceColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRICE);
        String productPrice = cursor.getString(priceColumnIndex);
        priceTextView.setText("Price: " + productPrice);

        TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        int quantityColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_QUANTITY);
        String productQuantity = cursor.getString(quantityColumnIndex);
        quantityTextView.setText("Quantity: " + productQuantity);

    }
}
