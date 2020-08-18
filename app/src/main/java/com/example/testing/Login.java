package com.example.testing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
EditText uer,pass;
Button login,forgt;
String email,password;
SharedPreferences sp;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uer=findViewById(R.id.users);
        pass=findViewById(R.id.pass);
        login=findViewById(R.id.login);
        forgt=findViewById(R.id.forgot);
        mAuth=FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             email=uer.getText().toString();
             password=pass.getText().toString();
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "A Field is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "A Field is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
             mAuth.signInWithEmailAndPassword(email,password)
                     .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if(task.isSuccessful()) {
                                 Toast.makeText(Login.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
                                 Intent i=new Intent(Login.this,second.class);
                                 startActivity(i);
                                 finish();
                             }else {
                                 Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG).show();
                             }
                         }
                     });
            }
        });
       /* signon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,Registration.class);
                startActivity(i);
                finish();
            }
        });*/

        forgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=uer.getText().toString();
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Please Enter Email First then Click", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                              if(task.isSuccessful()){
                                  Toast.makeText(Login.this, "Reset Link Send to Mail", Toast.LENGTH_SHORT).show();
                              }
                              else{
                                  Toast.makeText(Login.this, "Unable to send reset link. Try After Some time", Toast.LENGTH_SHORT).show();
                              }
                            }
                        });
            }
        });
    }
}
