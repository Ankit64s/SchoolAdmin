package com.example.testing;

import android.content.Intent;
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

public class Registration extends AppCompatActivity {
EditText user,pass;
Button signup,login;
String s1,s2;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        user=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        signup=findViewById(R.id.signup);
        login=findViewById(R.id.login);
        mAuth=FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=user.getText().toString();
                s2=pass.getText().toString();
                if (TextUtils.isEmpty(s1)){
                    Toast.makeText(getApplicationContext(), "A Field is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(s2)){
                    Toast.makeText(getApplicationContext(), "A Field is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(s1,s2)
                        .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                try{
                                    if (task.isSuccessful()) {
                                        //User is successfully registered and logged in
                                        //start Profile Activity here
                                        Toast.makeText(Registration.this, "registration successful",
                                                Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), Login.class));
                                    }else{
                                        Toast.makeText(Registration.this, "Couldn't register, try again",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                        e.printStackTrace();
                                }
                            }
                        });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });

    }
}
