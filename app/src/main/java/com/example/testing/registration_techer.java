package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class registration_techer extends AppCompatActivity {
EditText mobilepass;
String passed;
Button sendcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_techer);
        mobilepass=findViewById(R.id.mobilepass);
        sendcode=findViewById(R.id.sendcode);
        Intent i=getIntent();
        passed=i.getStringExtra("mob");
        mobilepass.setText(passed);

        sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),verify_teacher_code.class);
                i.putExtra("mobile",passed);
                startActivity(i);
            }
        });
    }
}
