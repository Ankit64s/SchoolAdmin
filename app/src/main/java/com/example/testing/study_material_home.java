package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class study_material_home extends AppCompatActivity {
Button uploadfiles,viewuploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_material_home);
        uploadfiles=findViewById(R.id.uploadfiles);
        viewuploads=findViewById(R.id.viewuploads);

        uploadfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),upload_form_up.class);
                startActivity(i);
            }
        });

        viewuploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i=new Intent(getApplicationContext(),view_uploads_layout.class);
            startActivity(i);
            }
        });
    }
}
