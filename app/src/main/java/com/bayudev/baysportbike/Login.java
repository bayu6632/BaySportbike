package com.bayudev.baysportbike;

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



    //memanggil key string
    String USERKEY = "userkey";
    String user_key = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //inisiasi variabel
        logusername = findViewById(R.id.logusername);
        logpassword = findViewById(R.id.logpassword);
        tmbl_login = findViewById(R.id.tmbl_login);
        tmbl_daftar = findViewById(R.id.tmbl_daftar);

        tmbl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //inisialisasi string username dan password
                final String username = logpassword.getText().toString();
                final String password = logpassword.getText().toString();


                //jika username kosong maka ada notice
                if(username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username kosong!", Toast.LENGTH_SHORT).show();
                }
                else {
                    //jika password kosong maka ada notice
                    if(password.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Password kosong!", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        reference = FirebaseDatabase.getInstance().getReference()
                                .child("User").child(username);

                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){

                                    // ambil data password dari firebase
                                    String passwordFromFirebase = dataSnapshot.child("password").getValue().toString();

                                    // validasi password dengan password firebase
                                    if(password.equals(passwordFromFirebase)){

                                        // simpan username (key) kepada local
                                        SharedPreferences sharedPreferences = getSharedPreferences(USERKEY, MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(user_key, logusername.getText().toString());
                                        editor.apply();

                                        // berpindah activity
                                        Intent gotohome = new Intent(Login.this,Pilih_Motor.class);
                                        startActivity(gotohome);

                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Password salah!", Toast.LENGTH_SHORT).show();

                                    }


                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Username/Password tidak ada!", Toast.LENGTH_SHORT).show();

                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Toast.makeText(getApplicationContext(), "Database Error!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
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