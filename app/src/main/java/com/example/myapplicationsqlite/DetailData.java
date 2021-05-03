package com.example.myapplicationsqlite;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailData extends AppCompatActivity {
    static TextView tvnama,tvtelepon;
    String nm,id,tlp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        tvnama = findViewById(R.id.tvNamaKontak);
        tvtelepon = findViewById(R.id.tvNoTelepon);

        id = getIntent().getStringExtra("id");
        nm = getIntent().getStringExtra("nama");
        tlp = getIntent().getStringExtra("telepon");

        setTitle("Detail Data");
        tvnama.setText(nm);
        tvtelepon.setText(tlp);
    }
}
