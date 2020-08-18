package com.example.testing;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class admin_delete_teacher extends AppCompatActivity {
    TextView teacherid,teachername,teachermobile,teacheradhar;
    String tid,name,mobile,adhar;
    Button teacherfetch,teacherdel;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_teacher);
        teacherid=findViewById(R.id.teacherid);
        teachername=findViewById(R.id.teachername);
        teachermobile=findViewById(R.id.teachermobile);
        teacheradhar=findViewById(R.id.teacheradhar);
        teacherfetch=findViewById(R.id.teacherfetch);
        teacherdel=findViewById(R.id.delteach);
        progressDialog=new ProgressDialog(this);
        teacherfetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tid=teacherid.getText().toString();
                getData();
                new BackgroundJob().execute();
            }
        });

        teacherdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tid=teacherid.getText().toString();
                name=teachername.getText().toString();
                mobile=teachermobile.getText().toString();
                adhar=teacheradhar.getText().toString();
                delete();
                new deletor().execute();
            }
        });
    }
    public void delete() {
        RequestQueue rq=Volley.newRequestQueue(this);
        StringRequest r1= new StringRequest(Request.Method.POST, "https://prodigyanand.000webhostapp.com/studentapi/admin_delete_teacher.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Teacher Terminated Successfully ", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Ohh Try Again", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hm =new HashMap<>();
                hm.put("r",tid);
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
        String address="";
        adhar="";
        String salary="";
        String comments="";
        String notice="";
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
        teacheradhar.setText(adhar);
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

    private class deletor extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Teacher's Record");
            progressDialog.setMessage("Deleting Entry From Database....");
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
