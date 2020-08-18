package com.example.testing;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class admin_delete_student extends AppCompatActivity {
EditText rolls;
TextView name,classe,section,stuadhaar;
Button stufetch,studeregister;
String temp;
String s1,s2,s3,s4;
int stat=0;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_student);
        rolls=findViewById(R.id.rollstu);
        name=findViewById(R.id.namefet);
        classe=findViewById(R.id.classfet);
        section=findViewById(R.id.secfet);
        stuadhaar=findViewById(R.id.adharfet);
        stufetch=findViewById(R.id.fetch);
        studeregister=findViewById(R.id.deregister);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Fetching Records....");
        stufetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp=rolls.getText().toString();
                stat=1;
                new BackgroundJob().execute();

            }
        });

        studeregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=name.getText().toString();
                s2=classe.getText().toString();
                s3=section.getText().toString();
                s4=stuadhaar.getText().toString();
                delete();
                new delter().execute();

            }
        });
    }


    public void getData() {
        temp=rolls.getText().toString();
        RequestQueue rq= Volley.newRequestQueue(this);
        StringBuilder sb= new StringBuilder();
        sb.append(Config.DATA_URL+temp);
        rq.add(new StringRequest(sb.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(admin_delete_student.this, "Data Fetched", Toast.LENGTH_SHORT).show();
                ShowJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(admin_delete_student.this, "Data Not Fetched", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void ShowJson(String response) {
        String namee="";
        String classes="";
        String sect="";
        String adhr="";
        try {
            JSONObject jo=new JSONObject(response).getJSONArray(Config.KEY_RESULT).getJSONObject(0);
            namee=jo.getString(Config.KEY_NAME);
            classes=jo.getString(Config.KEY_CLASS);
            sect=jo.getString(Config.KEY_Section);
            adhr=jo.getString(Config.KEY_Adhar);

        } catch (JSONException e) {
            //e.printStackTrace();
        }
        name.setText(namee);
        classe.setText(classes);
        section.setText(sect);
        stuadhaar.setText(adhr);

    }

    public void delete() {
        RequestQueue rq=Volley.newRequestQueue(this);
        StringRequest r1= new StringRequest(Request.Method.POST, "https://prodigyanand.000webhostapp.com/studentapi/admin_delete_scrap.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(admin_delete_student.this, "Student De-Registered Successfully ", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(admin_delete_student.this, "Ohh Try Again", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap <String,String> hm =new HashMap<>();
                hm.put("r",temp);
                return hm;
            }
        };
        rq.add(r1);

    }


    private class BackgroundJob extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Records");
            progressDialog.setMessage("Fetching Records.....");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getData();
            try {
                Thread.sleep(3000);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
        }
    }

    private class delter extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Records");
            progressDialog.setMessage("DeRegistering the student.....");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Thread.sleep(2000);
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
