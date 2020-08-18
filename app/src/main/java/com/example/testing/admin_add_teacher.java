package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class admin_add_teacher extends AppCompatActivity {
EditText idteacher,nameteacher,mobteacher,addressteacher,adharteacher,salaryteacher,commentsteacher,noticeteacher;
Button submit;
String id,name,mobile,address,adhar,salary,comments,notice;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_teacher);
        idteacher=findViewById(R.id.idteacher);
        nameteacher=findViewById(R.id.nameteacher);
        mobteacher=findViewById(R.id.mobteacher);
        addressteacher=findViewById(R.id.addressteacher);
        adharteacher=findViewById(R.id.adharteacher);
        salaryteacher=findViewById(R.id.salaryteacher);
        commentsteacher=findViewById(R.id.commentsteacher);
        noticeteacher=findViewById(R.id.noticeteacher);
        submit=findViewById(R.id.submitteacher);

        progressDialog=new ProgressDialog(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=idteacher.getText().toString();
                name=nameteacher.getText().toString();
                mobile=mobteacher.getText().toString();
                address=addressteacher.getText().toString();
                adhar=adharteacher.getText().toString();
                salary=salaryteacher.getText().toString();
                comments=commentsteacher.getText().toString();
                notice=noticeteacher.getText().toString();

                if(TextUtils.isEmpty(id)){
                    Toast.makeText(admin_add_teacher.this, "Please Enter Teacher Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(admin_add_teacher.this, "Please Enter Teacher's Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mobile)){
                    Toast.makeText(admin_add_teacher.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(address)){
                    Toast.makeText(admin_add_teacher.this, "Please Enter Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(adhar)){
                    Toast.makeText(admin_add_teacher.this, "Please Enter Aadhaar", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(salary)){
                    Toast.makeText(admin_add_teacher.this, "Please Enter Salary", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(comments)){
                    comments="No Comments";
                }
                if(TextUtils.isEmpty(notice)){
                    notice="No Notices Issued.";
                }
                if(mobile.length()<10){
                    Toast.makeText(admin_add_teacher.this, "Mobile Number Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(adhar.length()<12){
                    Toast.makeText(admin_add_teacher.this, "Adhar Number Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                registers();
                new BackgroundJob().execute();

            }
        });
    }

    public void registers() {
        RequestQueue rq= Volley.newRequestQueue(this);
        StringRequest r1=new StringRequest(Request.Method.POST, "https://prodigyanand.000webhostapp.com/studentapi/adminaddteacher.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(admin_add_teacher.this, "Registered", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(admin_add_teacher.this, "Not Registered", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String,String> getParams () throws AuthFailureError{
                HashMap <String,String> hm=new HashMap<>();
                hm.put("r",id);
                hm.put("n",name);
                hm.put("m",mobile);
                hm.put("ad",address);
                hm.put("adhr",adhar);
                hm.put("sal",salary);
                hm.put("co",comments);
                hm.put("noti",notice);
                return hm;

            }
        };
        rq.add(r1);
    }


    private class BackgroundJob extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Teacher Record");
            progressDialog.setMessage("Entry Adding to Databse...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
        }
    }
}
