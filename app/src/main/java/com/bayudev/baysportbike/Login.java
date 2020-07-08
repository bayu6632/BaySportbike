package com.bayudev.baysportbike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button tmbl_login, tmbl_daftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tmbl_login = findViewById(R.id.tmbl_login);
        tmbl_daftar = findViewById(R.id.tmbl_daftar);

        tmbl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Login.this, Pilih_Motor.class);
                startActivity(a);
            }
        });
        tmbl_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(Login.this, Daftar.class);
                startActivity(b);
            }
        });
    }
}