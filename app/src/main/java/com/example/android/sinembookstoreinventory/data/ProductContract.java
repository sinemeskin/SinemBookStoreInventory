package com.example.android.sinembookstoreinventory.data;

import android.provider.BaseColumns;

public class ProductContract {

    private ProductContract() {
    }

    public static final class ProductEntry implements BaseColumns {

        public final static String TABLE_NAME = "products";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PRODUCT_NAME = "name";
        public final static String COLUMN_PRICE = "price";
        public final static String COLUMN_QUANTITY = "quantity";
        public final static String COLUMN_SUPPLIER_NAME = "supplier_name";
        public final static String COLUMN_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";

    }

}
