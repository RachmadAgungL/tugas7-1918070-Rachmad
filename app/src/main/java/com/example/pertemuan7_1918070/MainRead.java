package com.example.pertemuan7_1918070;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import pertemuan7_1918070.R;

public class MainRead extends AppCompatActivity implements
        AdapterView.OnItemClickListener{
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private MyDatabase db;
    private List<Perabotan> ListPerabotan = new
            ArrayList<Perabotan>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new MyDatabase(this);
        adapter_off = new CustomListAdapter(this, ListPerabotan
        );
        mListView = (ListView) findViewById(R.id.list_Perabotan);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        ListPerabotan.clear();
        List<Perabotan> Perabotan = db.ReadPerabotan();
        for (Perabotan mhs : Perabotan) {
            Perabotan daftar = new Perabotan();
            daftar.set_id(mhs.get_id());
            daftar.set_nama(mhs.get_nama());
            daftar.set_harga(mhs.get_harga());
            ListPerabotan.add(daftar);
            if ((ListPerabotan.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int
            i, long l) {
        Object o = mListView.getItemAtPosition(i);
        Perabotan detailMhs = (Perabotan)o;
        String Sid = detailMhs.get_id();
        String Snama = detailMhs.get_nama();
        String Skelas = detailMhs.get_harga();
        Intent goUpdel = new Intent(MainRead.this,
                MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Ikelas", Skelas);
        startActivity(goUpdel);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ListPerabotan.clear();
        mListView.setAdapter(adapter_off);
        List<Perabotan> Perabotan = db.ReadPerabotan();
        for (Perabotan mhs : Perabotan) {
            Perabotan daftar = new Perabotan();
            daftar.set_id(mhs.get_id());
            daftar.set_nama(mhs.get_nama());
            daftar.set_harga(mhs.get_harga());
            ListPerabotan.add(daftar);
            if ((ListPerabotan.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
}
