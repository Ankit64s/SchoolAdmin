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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class admin_view_student extends AppCompatActivity {
Button vfecth;
EditText vaddr;
TextView vname,vfname,vmname,vmob,vclass,vsection,vct,vfee,vcmnt,vsadhr, vpadhr;
EditText vroll;
String temp;
String sname,sfname, smname, smob,saddr,sclass,ssection,sct,sfee,scmnt,ssadhr,spadhr;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_student);
        vfecth=findViewById(R.id.vfetch);
        vname=findViewById(R.id.vname);
        vfname=findViewById(R.id.vfname);
        vmname=findViewById(R.id.vmname);
        vmob=findViewById(R.id.vmob);
        vaddr=findViewById(R.id.vaddr);
        vclass=findViewById(R.id.vclass);
        vsection=findViewById(R.id.vsection);
        vct=findViewById(R.id.vclsteach);
        vfee=findViewById(R.id.vfee);
        vcmnt=findViewById(R.id.vcomnts);
        vsadhr=findViewById(R.id.vsadhr);
        vpadhr=findViewById(R.id.vpadhr);
        vroll=findViewById(R.id.vroll);

        /*vname.setMovementMethod(new ScrollingMovementMethod());
        vfname.setMovementMethod(new ScrollingMovementMethod());
        vmname.setMovementMethod(new ScrollingMovementMethod());
        vmob.setMovementMethod(new ScrollingMovementMethod());
        vaddr.setMovementMethod(new ScrollingMovementMethod());
        vclass.setMovementMethod(new ScrollingMovementMethod());
        vsection.setMovementMethod(new ScrollingMovementMethod());
        vct.setMovementMethod(new ScrollingMovementMethod());
        vfee.setMovementMethod(new ScrollingMovementMethod());
        vcmnt.setMovementMethod(new ScrollingMovementMethod());
        vsadhr.setMovementMethod(new ScrollingMovementMethod());
        vpadhr.setMovementMethod(new ScrollingMovementMethod());*/

        progressDialog=new ProgressDialog(this);

        vfecth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp=vroll.getText().toString();
                getData();
                new BackgroundJob().execute();
            }
        });


    }

    public void getData() {
        temp=vroll.getText().toString();
        RequestQueue rq= Volley.newRequestQueue(this);
        StringBuilder sb= new StringBuilder();
        sb.append(Config.DATA_URL1+temp);
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
        sname="";
        sfname="";
        smname="";
        smob="";
        saddr="";
        sclass="";
        ssection="";
        sct="";
        sfee="";
        scmnt="";
        ssadhr="";
        spadhr="";
        try {
            JSONObject jo=new JSONObject(response).getJSONArray(Config.KEY_RESULT).getJSONObject(0);
            sname=jo.getString(Config.KEY_NAME);
            sfname=jo.getString(Config.KEY_FATHER);
            smname=jo.getString(Config.KEY_MOTHER);
            smob=jo.getString(Config.KEY_Mobile);
            saddr=jo.getString(Config.KEY_Address);
            sclass=jo.getString(Config.KEY_CLASS);
            ssection=jo.getString(Config.KEY_Section);
            sct=jo.getString(Config.KEY_CLASSTEACHER);
            sfee=jo.getString(Config.KEY_FEES);
            scmnt=jo.getString(Config.KEY_COMMENTS);
            ssadhr=jo.getString(Config.KEY_Adhar);
            spadhr=jo.getString(Config.KEY_Paradhar);

        } catch (JSONException e) {

        }
        vname.setText(sname);
        vfname.setText(sfname);
        vmname.setText(smname);
        vmob.setText(smob);
        vaddr.setText(saddr);
        vclass.setText(sclass);
        vsection.setText(ssection);
        vct.setText(sct);
        vfee.setText(sfee);
        vcmnt.setText(scmnt);
        vsadhr.setText(ssadhr);
        vpadhr.setText(spadhr);
    }

    private class BackgroundJob extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Record");
            progressDialog.setMessage("Fetching Student's Record.....");
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
