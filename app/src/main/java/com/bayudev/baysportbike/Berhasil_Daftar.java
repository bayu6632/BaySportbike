package com.bayudev.baysportbike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Berhasil_Daftar extends AppCompatActivity {

    Button tmbl_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berhasil__daftar);

        tmbl_next = findViewById(R.id.tmbl_next);

        tmbl_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Berhasil_Daftar.this, Pilih_Motor.class);
                startActivity(a);
            }
        });
    }
}