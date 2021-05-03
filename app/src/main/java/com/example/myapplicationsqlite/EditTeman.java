package com.example.myapplicationsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationsqlite.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class EditTeman extends AppCompatActivity {
    private TextInputEditText tNama, tTelepon;
    private Button saveBtn;
    String nm, tp, id;
    DBController controller = new DBController(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_teman);

        tNama = findViewById(R.id.editNama);
        tTelepon = findViewById(R.id.editTelepon);
        saveBtn = findViewById(R.id.buttonEdit);

        id = getIntent().getStringExtra("id");
        nm = getIntent().getStringExtra("nama");
        tp = getIntent().getStringExtra("telepon");


        setTitle("Edit Data");
        tNama.setText(nm);
        tTelepon.setText(tp);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tNama.getText().toString().equals("") || tTelepon.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Data belum lengkap!", Toast.LENGTH_LONG).show();
                } else {
                    nm = tNama.getText().toString();
                    tp = tTelepon.getText().toString();
                    HashMap<String, String> val = new HashMap<>();
                    val.put("id", id);
                    val.put("nama", nm);
                    val.put("telepon", tp);
                    controller.updateData(val);
                    callHome();
                }
            }
        });
    }

    public void callHome() {
        Intent i = new Intent(EditTeman.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
