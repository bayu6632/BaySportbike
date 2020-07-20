package com.bayudev.baysportbike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Pilih_Motor extends AppCompatActivity {

    LinearLayout r15,r25,n250fi,n650se, cbr250, cbr150, ktm1, ktm2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih__motor);

        r15 = findViewById(R.id.r15);
        r25 = findViewById(R.id.r25);
        n250fi = findViewById(R.id.n250fi);
        n650se = findViewById(R.id.n650se);
        cbr250 = findViewById(R.id.cbr250);
        cbr150 = findViewById(R.id.cbr150);
        ktm1 = findViewById(R.id.ktm1);
        ktm2 = findViewById(R.id.ktm2);

        //untuk pindah aktiviti
        r15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Pilih_Motor.this, motor_r15.class);
                startActivity(a);
            }
        });
        r25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Pilih_Motor.this, motor_r25.class);
                startActivity(a);
            }
        });
        n250fi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Pilih_Motor.this, motor_ninja250.class);
                startActivity(a);
            }
        });
        n650se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Pilih_Motor.this, motor_ninja650.class);
                startActivity(a);
            }
        });
        cbr150.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Pilih_Motor.this, motor_cbr150r.class);
                startActivity(a);
            }
        });
        cbr250.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Pilih_Motor.this, motor_cbr250.class);
                startActivity(a);
            }
        });
        ktm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Pilih_Motor.this, motor_rc250.class);
                startActivity(a);
            }
        });
        ktm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Pilih_Motor.this, motor_rc390.class);
                startActivity(a);
            }
        });

    }
}