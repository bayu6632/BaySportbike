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

    //untuk menyimpan nilai string
    String USERKEY = "userkey";
    String user_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        //inisialisasi variabel
        tmbl_daftar = findViewById(R.id.tmbl_daftar);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        tmbl_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //menyimpan daata ke local storage
                SharedPreferences sharedPreferences = getSharedPreferences(USERKEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(user_key, username.getText().toString());
                editor.apply();

                //simpan data ke firebase (ke Folder User)
                reference = FirebaseDatabase.getInstance().getReference()
                        .child("User").child(username.getText().toString());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("nama_lengkap").setValue(nama_lengkap.getText().toString());
                        dataSnapshot.getRef().child("username").setValue(username.getText().toString());
                        dataSnapshot.getRef().child("password").setValue(password.getText().toString());

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent a = new Intent(Daftar.this, Berhasil_Daftar.class);
                startActivity(a);
            }
        });
    }
}

