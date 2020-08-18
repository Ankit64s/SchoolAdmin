package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class admin_update_class_teacher extends AppCompatActivity {
EditText enterclass,entersectiion;
TextView currentteachername;
EditText newteachername;
Button updateclassteachername;
Button detailfetch;
String classentry,section, current, newentry;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_class_teacher);
        enterclass=findViewById(R.id.enterclass);
        entersectiion=findViewById(R.id.entersection);
        currentteachername=findViewById(R.id.currentteachername);
        newteachername=findViewById(R.id.newteachername);
        updateclassteachername=findViewById(R.id.updateclassteachername);
        detailfetch=findViewById(R.id.detailfetch);
        progressDialog=new ProgressDialog(this);
        detailfetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classentry=enterclass.getText().toString();
                section=entersectiion.getText().toString();
                if(TextUtils.isEmpty(classentry)){
                    Toast.makeText(admin_update_class_teacher.this, "Enter Class", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(section)){
                    Toast.makeText(admin_update_class_teacher.this, "Enter Section", Toast.LENGTH_SHORT).show();
                    return;
                }
                getData();
                new BackgroundJob().execute();

            }
        });

        updateclassteachername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classentry=enterclass.getText().toString();
                section=entersectiion.getText().toString();
                current=currentteachername.getText().toString();
                newentry=newteachername.getText().toString();
                if(TextUtils.isEmpty(current)){
                    Toast.makeText(admin_update_class_teacher.this, "Kindly Perform Fetch Operation", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(current==null){
                    Toast.makeText(admin_update_class_teacher.this, "No Such Class or Section Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                update();
                new progressor().execute();
            }
        });
    }

    public void update() {
        RequestQueue rq=Volley.newRequestQueue(this);
        StringRequest r1= new StringRequest(Request.Method.POST, "https://prodigyanand.000webhostapp.com/studentapi/admin_update_ct.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Student Updated Successfully ", Toast.LENGTH_SHORT).show();
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
                hm.put("cl",classentry);
                hm.put("s",section);
                hm.put("ct",newentry);
                return hm;
            }
        };
        rq.add(r1);
    }

    public void getData() {
        classentry=enterclass.getText().toString();
        section=entersectiion.getText().toString();
        RequestQueue rq= Volley.newRequestQueue(this);
        StringBuilder sb= new StringBuilder();
        sb.append(Config.DATA_URL3+classentry+"&s="+section);
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
        current="";

        try {
            JSONObject jo=new JSONObject(response).getJSONArray(Config.KEY_RESULT).getJSONObject(0);
            current=jo.getString(Config.KEY_CLASSTEACHER);

        } catch (JSONException e) {

        }
        currentteachername.setText(current);

    }

    public class BackgroundJob extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Teacher's Record");
            progressDialog.setMessage("Fetching From Database....");
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

    public class progressor extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Teacher's Record");
            progressDialog.setMessage("Updating into Database....");
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
