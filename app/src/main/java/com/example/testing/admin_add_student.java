package com.example.testing;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class admin_add_student extends AppCompatActivity {
EditText roll,name,fname,mname,mob,addrs,cls,sectn,clstechname,fee,commnts,stuadhar, paradhar;
Button submit;
String sroll,sname,sfname,smname,smob,saddrs,scls,ssectn,sclstexhname,sfee,scommnts,sstuadhar,sparadhar;
String Homework,notice,atten,totald;
ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_student);
        roll=findViewById(R.id.rollstudent);
        name=findViewById(R.id.namestudent);
        fname=findViewById(R.id.fathersname);
        mname=findViewById(R.id.mothersname);
        mob=findViewById(R.id.mobile);
        addrs=findViewById(R.id.address);
        cls=findViewById(R.id.classes);
        sectn=findViewById(R.id.section);
        clstechname=findViewById(R.id.classteacher);
        fee=findViewById(R.id.fees);
        commnts=findViewById(R.id.addinfo);
        stuadhar=findViewById(R.id.adhaarstu);
        paradhar=findViewById(R.id.adhaarpar);
        submit=findViewById(R.id.submit);
        Homework="No Homework";
        notice="No";
        atten="0";
        totald="0";
        progressDialog=new ProgressDialog(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sroll=roll.getText().toString();
                sname=name.getText().toString();
                sfname=fname.getText().toString();
                smname=mname.getText().toString();
                smob=mob.getText().toString();
                saddrs=addrs.getText().toString();
                scls=cls.getText().toString();
                ssectn=sectn.getText().toString();
                sclstexhname=clstechname.getText().toString();
                sfee=fee.getText().toString();
                scommnts=commnts.getText().toString();
                sstuadhar=stuadhar.getText().toString();
                sparadhar=paradhar.getText().toString();
                if(TextUtils.isEmpty(sroll)){
                    Toast.makeText(admin_add_student.this, "Please Enter Roll Num", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sname)){
                    Toast.makeText(admin_add_student.this, "Please Enter Student Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sfname)){
                    Toast.makeText(admin_add_student.this, "Please Enter Father's Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(smname)){
                    Toast.makeText(admin_add_student.this, "Please Enter Mother's Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(smob)){
                    Toast.makeText(admin_add_student.this, "Please Enter Mobile", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(saddrs)){
                    Toast.makeText(admin_add_student.this, "Please Enter Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(scls)){
                    Toast.makeText(admin_add_student.this, "Please Enter Class", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(ssectn)){
                    Toast.makeText(admin_add_student.this, "Please Enter Section", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sclstexhname)){
                    Toast.makeText(admin_add_student.this, "Please Enter Class Teacher Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sfee)){
                    Toast.makeText(admin_add_student.this, "Please Enter Fee Due", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sstuadhar)){
                    Toast.makeText(admin_add_student.this, "Please Enter Student's Aadhaar", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sparadhar)){
                    Toast.makeText(admin_add_student.this, "Please Enter Parent's Aadhaar", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(scommnts)){
                    scommnts="No Comments";
                }
                if(smob.length()<10){
                    Toast.makeText(admin_add_student.this, "Mobile Number Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(stuadhar.length()<12){
                    Toast.makeText(admin_add_student.this, "Student Adhaar Number Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(sparadhar.length()<12){
                    Toast.makeText(admin_add_student.this, "Parent Adhaar Number Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                registers();
                new BackgroundJob().execute();
            }
        });
    }

    private class BackgroundJob extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Register Student");
            progressDialog.setMessage("Student Adding to Database....");
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

    public void registers(){
        RequestQueue rq= Volley.newRequestQueue(this);
        StringRequest r1=new StringRequest(Request.Method.POST,"https://prodigyanand.000webhostapp.com/studentapi/register.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(admin_add_student.this, "Registered", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(admin_add_student.this, "Not Registered", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError {
                HashMap<String, String> hm = new HashMap<>();
                hm.put("r",sroll);
                hm.put("n",sname);
                hm.put("f",sfname);
                hm.put("mn",smname);
                hm.put("m",smob);
                hm.put("ad",saddrs);
                hm.put("cl",scls);
                hm.put("s",ssectn);
                hm.put("ct",sclstexhname);
                hm.put("fe",sfee);
                hm.put("co",scommnts);
                hm.put("as",sstuadhar);
                hm.put("ap",sparadhar);
                hm.put("hw",Homework);
                hm.put("no",notice);
                hm.put("att",atten);
                hm.put("td",totald);
                return hm;
            }
        };
        rq.add(r1);
    }
}
