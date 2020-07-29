package com.bayudev.baysportbike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button tmbl_login, tmbl_daftar;
    EditText logusername, logpassword;


    //Memanggil firebase
    DatabaseReference reference;



    String USERNAME_KEY = "usernamekey";
    String username_key ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tmbl_daftar = findViewById(R.id.tmbl_daftar);
        tmbl_login = findViewById(R.id.tmbl_login);
        logusername = findViewById(R.id.logusername);
        logpassword = findViewById(R.id.logpassword);

    tmbl_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent godaftar = new Intent(Login.this, Daftar.class);
                startActivity(godaftar);
            }
        });
        tmbl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ubah state menjadi loading
                tmbl_login.setEnabled(false);
                tmbl_login.setText("TUNGGU...");

                final String username = logusername.getText().toString();
                final String password = logpassword.getText().toString();

                reference = FirebaseDatabase.getInstance().getReference().child("User").child(username);

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            //ambil data pass dari DB
                            String passwordFromDB = dataSnapshot.child("password").getValue().toString();


                            //validasi password dg DB
                            if (password.equals(passwordFromDB)) {

                                //simpan usernamekey  ke local

                                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(username_key, logusername.getText().toString());
                                editor.apply();

                                //pindah activity
                                Intent gohome = new Intent(Login.this, Pilih_Motor.class);
                                startActivity(gohome);

                            } else {

                                Toast.makeText(getApplicationContext(), "Password SALAH!", Toast.LENGTH_SHORT).show();

                            }



                        } else {
                            Toast.makeText(getApplicationContext(), "Username Tidak Ada", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });
    }
}