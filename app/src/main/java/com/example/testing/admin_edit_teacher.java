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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class admin_edit_teacher extends AppCompatActivity {
EditText teacherid,teachername,teachermobile,teacheraddress,teacheradhar,teachersalary,teachercomments,teachernotice;
String tid,name,mobile,address,adhar,salary,comments,notice;
Button teacherfetch,teacheredit;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_teacher);
        teacherid=findViewById(R.id.teacherid);
        teachername=findViewById(R.id.teachername);
        teachermobile=findViewById(R.id.teachermobile);
        teacheraddress=findViewById(R.id.teacheraddress);
        teacheradhar=findViewById(R.id.teacheradhar);
        teachersalary=findViewById(R.id.teachersalary);
        teachercomments=findViewById(R.id.teachercomments);
        teachernotice=findViewById(R.id.teachernotice);
        teacherfetch=findViewById(R.id.teacherfetch);
        teacheredit=findViewById(R.id.editteach);
        progressDialog=new ProgressDialog(this);
        teacherfetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tid=teacherid.getText().toString();
                getData();
                new BackgroundJob().execute();
            }
        });

        teacheredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tid=teacherid.getText().toString();
                name=teachername.getText().toString();
                mobile=teachermobile.getText().toString();
                address=teacheraddress.getText().toString();
                adhar=teacheradhar.getText().toString();
                salary=teachersalary.getText().toString();
                comments=teachercomments.getText().toString();
                notice=teachernotice.getText().toString();

                if (TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(), "Please Enter Teacher's Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mobile)){
                    Toast.makeText(getApplicationContext(), "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(address)){
                    Toast.makeText(getApplicationContext(), "Please Enter Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(adhar)){
                    Toast.makeText(getApplicationContext(), "Please Enter Aadhaar", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(salary)){
                    Toast.makeText(getApplicationContext(), "Please Enter Salary", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(comments)){
                    comments="No Comments";
                }
                if(TextUtils.isEmpty(notice)){
                    notice="No Notices Issued.";
                }
                if(mobile.length()<10){
                    Toast.makeText(getApplicationContext(), "Mobile Number Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(adhar.length()<12){
                    Toast.makeText(getApplicationContext(), "Adhar Number Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                update();
                new editor().execute();
            }
        });
    }

    public void update() {
        RequestQueue rq= Volley.newRequestQueue(this);
        StringRequest r1=new StringRequest(Request.Method.POST, "https://prodigyanand.000webhostapp.com/studentapi/admin_update_teacher.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Entry Updated", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Updation failed", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String,String> getParams () throws AuthFailureError {
                HashMap<String,String> hm=new HashMap<>();
                hm.put("r",tid);
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

    public void getData() {
        tid=teacherid.getText().toString();
        RequestQueue rq= Volley.newRequestQueue(this);
        StringBuilder sb= new StringBuilder();
        sb.append(Config.DATA_URL2+tid);
        rq.add(new StringRequest(sb.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Data Fetched", Toast.LENGTH_SHORT).show();
                ShowJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Data Not Fetched", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void ShowJson(String response) {
        name="";
        mobile="";
        address="";
        adhar="";
        salary="";
        comments="";
        notice="";
        try {
            JSONObject jo=new JSONObject(response).getJSONArray(Config.KEY_RESULT).getJSONObject(0);
            name=jo.getString(Config.KEY_TName);
            mobile=jo.getString(Config.KEY_TMobile);
            address=jo.getString(Config.KEY_TAddress);
            adhar=jo.getString(Config.KEY_TAdhar);
            salary=jo.getString(Config.KEY_TSalary);
            comments=jo.getString(Config.KEY_TComments);
            notice=jo.getString(Config.KEY_TNotice);
        } catch (JSONException e) {

        }
        teachername.setText(name);
        teachermobile.setText(mobile);
        teacheraddress.setText(address);
        teacheradhar.setText(adhar);
        teachersalary.setText(salary);
        teachercomments.setText(comments);
        teachernotice.setText(notice);
    }

    private class BackgroundJob extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Teacher's Record");
            progressDialog.setMessage("Getting Record From Database....");
            progressDialog.show();
            progressDialog.setCancelable(false);
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

    private class editor extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Teacher's Record");
            progressDialog.setMessage("Updating Record into Database....");
            progressDialog.show();
            progressDialog.setCancelable(false);
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
