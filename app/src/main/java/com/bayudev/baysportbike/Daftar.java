package com.bayudev.baysportbike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Daftar extends AppCompatActivity {


    //deklarasi variabel
    Button tmbl_daftar;
    EditText nama_lengkap, username, password;
    DatabaseReference reference;


    String USERNAME_KEY = "usernamekey";
    String username_key ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        nama_lengkap = findViewById(R.id.nama_lengkap);


        tmbl_daftar = findViewById(R.id.tmbl_daftar);
        tmbl_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ubah state menjadi loading
                tmbl_daftar.setEnabled(false);
                tmbl_daftar.setText("Loading ...");

                // menyimpan data kepada local storage (handphone)
                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(username_key, username.getText().toString());
                editor.apply();

                // simpan kepada database
                reference = FirebaseDatabase.getInstance().getReference()
                        .child("User").child(username.getText().toString());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("username").setValue(username.getText().toString());
                        dataSnapshot.getRef().child("password").setValue(password.getText().toString());
                        dataSnapshot.getRef().child("nama_lengkap").setValue(nama_lengkap.getText().toString());
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //pindah activity
                Intent godaft = new Intent(Daftar.this, Berhasil_Daftar.class);
                startActivity(godaft);
            }
        });
    }


}