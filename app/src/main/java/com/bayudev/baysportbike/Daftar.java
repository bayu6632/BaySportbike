package com.bayudev.baysportbike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Daftar extends AppCompatActivity {

    Button tmbl_daftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        tmbl_daftar = findViewById(R.id.tmbl_daftar);

        tmbl_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent (Daftar.this, Berhasil_Daftar.class);
                startActivity(a);
            }
        });
    }
}