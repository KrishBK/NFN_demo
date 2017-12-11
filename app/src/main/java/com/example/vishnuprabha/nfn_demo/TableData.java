package com.example.vishnuprabha.nfn_demo;

import android.provider.BaseColumns;

/**
 * Created by Deepan on 9/27/2015.
 */
public class TableData {
    public TableData()
    {

    }
    public static abstract class TableInfo implements BaseColumns
    {
        public static final String TAG_NAME = "id";
        public static final String TAG_MOBILE = "question";
        public static final String TAG_ADDRESS = "answer";
        public static final String DATABASE_NAME = "try4";
        public static final String TABLE_NAME = "info";

        public static final String TAG_NAME1 = "newid";
        public static final String TAG_MOBILE1 = "question";
        public static final String TAG_ADDRESS1 = "answer";
        public static final String TAG_NEW = "oldid";
        public static final String TABLE_NAME1 = "info1";
    }
}
