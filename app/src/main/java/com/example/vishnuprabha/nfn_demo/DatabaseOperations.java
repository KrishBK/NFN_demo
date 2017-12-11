package com.example.vishnuprabha.nfn_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseOperations extends SQLiteOpenHelper {
    public static final int database_version = 4;
    public String CREATE_QUERY = "CREATE TABLE "+ TableData.TableInfo.TABLE_NAME+"("+ TableData.TableInfo.TAG_NAME+" TEXT, "+ TableData.TableInfo.TAG_MOBILE+" TEXT, "+ TableData.TableInfo.TAG_ADDRESS+" TEXT"+");";
    public String CREATE_QUERY1 = "CREATE TABLE "+ TableData.TableInfo.TABLE_NAME1+"("+ TableData.TableInfo.TAG_NAME1+" TEXT, "+ TableData.TableInfo.TAG_MOBILE1+" TEXT, "+ TableData.TableInfo.TAG_ADDRESS1+" TEXT, "+TableData.TableInfo.TAG_NEW+" TEXT"+");";
    public DatabaseOperations(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database Operations", "DATABASE SUCCESSFULLY CREATED");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(CREATE_QUERY);
        Log.d("Database Operations","TABLE1 SUCCESSFULLY CREATED");
        try{
            sdb.execSQL(CREATE_QUERY1);
            Log.d("Database Operations","TABLE2 SUCCESSFULLY CREATED");
        }
        catch (Exception e){
            Log.d("Exception",e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void putInformation1(DatabaseOperations dop, String name, String mobile, String address, String id){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(TableData.TableInfo.TAG_NAME1,name);
        cv.put(TableData.TableInfo.TAG_MOBILE1,mobile);
        cv.put(TableData.TableInfo.TAG_ADDRESS1,address);
        cv.put(TableData.TableInfo.TAG_NEW,id);
        Log.d("logging",cv.toString());
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME1,null,cv);
        cv.clear();
        SQ.close();
        Log.d("Database Operations","ONE ROW INSERTED");
    }
    public void putInformation(DatabaseOperations dop, String name, String mobile, String address){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(TableData.TableInfo.TAG_NAME,name);
        cv.put(TableData.TableInfo.TAG_MOBILE,mobile);
        cv.put(TableData.TableInfo.TAG_ADDRESS,address);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME,null,cv);
        cv.clear();
        SQ.close();
        //Log.d("Database Operations","ONE ROW INSERTED");
    }
    public Cursor getInformation(DatabaseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {TableData.TableInfo.TAG_NAME, TableData.TableInfo.TAG_MOBILE, TableData.TableInfo.TAG_ADDRESS};
        Cursor CR = SQ.query(TableData.TableInfo.TABLE_NAME, columns, null, null, null, null, null);
        return CR;
    }

    public Cursor getInformation1(DatabaseOperations dop,String id){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {TableData.TableInfo.TAG_NAME1, TableData.TableInfo.TAG_MOBILE1, TableData.TableInfo.TAG_ADDRESS1, TableData.TableInfo.TAG_NEW};
        String selection = TableData.TableInfo.TAG_NEW+"=?";
        Cursor CR2 = SQ.query(TableData.TableInfo.TABLE_NAME1, columns,selection, new String[]{id}, null, null, TableData.TableInfo.TAG_NAME1+ " ASC");
        return CR2;
    }

    public boolean deleteInformation(DatabaseOperations dop){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        return SQ.delete(TableData.TableInfo.TABLE_NAME, null,null)>0;
    }


}

