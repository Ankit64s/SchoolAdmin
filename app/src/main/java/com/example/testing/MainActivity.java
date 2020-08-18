package com.example.testing;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(Build.VERSION.SDK_INT>16){
            getWindow().setFlags(1024,1024);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,home.class);
                startActivity(i);
                finish();
            }
        },3000);



        /*
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser()==null){
                    Intent i=new Intent(MainActivity.this,teacher_verification.class);
                    startActivity(i);
                }

                else {
                    Intent i = new Intent(MainActivity.this, profile_teacher.class);
                    startActivity(i);
                }

            }
        });*/

    }
}
