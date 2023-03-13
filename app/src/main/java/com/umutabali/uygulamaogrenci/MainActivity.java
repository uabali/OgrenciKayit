package com.umutabali.uygulamaogrenci;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.umutabali.uygulamaogrenci.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth Auth;
    //Firebase sınıfından bir obje oluşturdum

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    View view = binding.getRoot();
    setContentView(view);

    Auth = FirebaseAuth.getInstance();
    FirebaseUser user = Auth.getCurrentUser();
    // kullanıcı giriş gerçekleştirdiyse tekrardan giriş yapmaması için
     if( user != null ){
     Intent intent = new Intent(MainActivity.this,SecondScreen.class);
     startActivity(intent);
     finish();
     }
     }

    public void signinClick(View view) {
        //giriş
    String email = binding.emailText.getText().toString(); //Create email
    String password = binding.passwordText.getText().toString(); //Create password

    if(email.equals("") || password.equals("")){
    Toast.makeText(this, "email yada şifre boş", Toast.LENGTH_SHORT).show();
    // hata mesajı
    Auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
    @Override
    public void onSuccess(AuthResult authResult) {
    Intent intent = new Intent(MainActivity.this,SecondScreen.class);
    startActivity(intent);
    finish();
     }
     }).addOnFailureListener(new OnFailureListener() {
     @Override
     public void onFailure(@NonNull Exception e)
     {
         Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
     }
     });
    }
     }

     public void signupClick(View view){
    String email = binding.emailText.getText().toString();
    String password = binding.passwordText.getText().toString();

    if(email.equals("") || password.equals("")) // boş olup olmadığını kontrol ediyoruz
    {
    Toast.makeText(this, "email yada şifre boş", Toast.LENGTH_SHORT).show(); // hata mesajı
    }else
    {
    Auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
    @Override
     public void onSuccess(AuthResult authResult) {
    Intent intent = new Intent(MainActivity.this,SecondScreen.class);
    startActivity(intent);
    finish();
    }
    }).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
    Toast.makeText(MainActivity.this,e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
    });
    }
    }
}