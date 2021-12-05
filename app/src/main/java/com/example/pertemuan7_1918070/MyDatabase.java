package com.example.pertemuan7_1918070;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "db_Rumah";
    private static final String tb_Perabotan = "tb_Perabotan";
    private static final String tb_Perabotan_id = "id";
    private static final String tb_Perabotan_nama = "nama";
    private static final String tb_Perabotan_harga = "harga";
    private static final String CREATE_TABLE_Perabotan = "CREATE TABLE " +
    tb_Perabotan +"("
            + tb_Perabotan_id + " INTEGER PRIMARY KEY ,"
            + tb_Perabotan_nama + " TEXT ,"
            + tb_Perabotan_harga + " TEXT " + ")";
    public MyDatabase (Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_Perabotan);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
    public void CreatePerabotan(Perabotan data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_Perabotan_id, data.get_id());
        values.put(tb_Perabotan_nama, data.get_nama());
        values.put(tb_Perabotan_harga, data.get_harga());
        db.insert(tb_Perabotan, null, values);
        db.close();
    }
    public List<Perabotan> ReadPerabotan() {
        List<Perabotan> listMhs = new ArrayList<Perabotan>();
        String selectQuery = "SELECT * FROM " + tb_Perabotan;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Perabotan data = new Perabotan();
                data.set_id(cursor.getString(0));
                data.set_nama(cursor.getString(1));
                data.set_harga(cursor.getString(2));
                listMhs.add(data);
            } while (cursor.moveToNext());
        }
        db.close();
        return listMhs;
    }
    public int UpdatePerabotan (Perabotan data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_Perabotan_nama, data.get_nama());
        values.put(tb_Perabotan_harga, data.get_harga());
        return db.update(tb_Perabotan, values, tb_Perabotan_id +
                        " = ?",
                new String[]{String.valueOf((data.get_id()))});
    }
    public void DeletePerabotan(Perabotan data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_Perabotan,tb_Perabotan_id+ " = ?",
                new String[]{String.valueOf(data.get_id())});
        db.close();
    }
}
