package com.umutabali.uygulamaogrenci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class SecondScreen extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    // Menüyü SecondScreene bağlıyoruz
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.islemler,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // işlemi seçtikten sonra ne olcağını belirlediğimiz yer
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.option_menu){

            auth.signOut(); // çıkış yapacak
            // giriş ekranına döndürülecek
            Intent intent = new Intent(SecondScreen.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}