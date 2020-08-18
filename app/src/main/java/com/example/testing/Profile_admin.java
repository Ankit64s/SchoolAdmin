package com.example.testing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Profile_admin extends AppCompatActivity {
Button addstudent,deletestudent, viewstudent, editstudent;
Button addteacher,viewteacher,editteacher,deleteteacher,updateclassteacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_admin);
        addstudent=findViewById(R.id.addstudent);
        deletestudent=findViewById(R.id.deletestu);
        viewstudent=findViewById(R.id.viewstudent);
        editstudent=findViewById(R.id.editstudent);
        addteacher=findViewById(R.id.addteacher);
        viewteacher=findViewById(R.id.viewteacher);
        editteacher=findViewById(R.id.editteacher);
        deleteteacher=findViewById(R.id.deleteteacher);
        updateclassteacher=findViewById(R.id.updateclassteacher);

        addstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),admin_add_student.class);
                startActivity(i);
            }
        });
        deletestudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),admin_delete_student.class);
                startActivity(i);
            }
        });
        viewstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),admin_view_student.class);
                startActivity(i);
            }
        });

        editstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),admin_edit_student.class);
                startActivity(i);
            }
        });

        addteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),admin_add_teacher.class);
                startActivity(i);
            }
        });

        viewteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),admin_view_teacher.class);
                startActivity(i);
            }
        });

        editteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),admin_edit_teacher.class);
                startActivity(i);
            }
        });

        deleteteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),admin_delete_teacher.class);
                startActivity(i);
            }
        });

        updateclassteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),admin_update_class_teacher.class);
                startActivity(i);
            }
        });
    }
}
