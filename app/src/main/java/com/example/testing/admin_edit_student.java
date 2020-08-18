package com.example.testing;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class admin_edit_student extends AppCompatActivity {
    Button vfecth,updaterecord;
    EditText vname,vfname,vmname,vmob,vaddr,vclass,vsection,vct,vfee,vcmnt,vsadhr, vpadhr;
    EditText vroll;
    String temp;
    String sname,sfname, smname, smob,saddr,sclass,ssection,sct,sfee,scmnt,ssadhr,spadhr;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_student);
        vfecth=findViewById(R.id.vfetch);
        updaterecord=findViewById(R.id.updaterecord);
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
        progressDialog=new ProgressDialog(this);


        vfecth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp=vroll.getText().toString();
                new BackgroundJob().execute();
            }
        });

        updaterecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp=vroll.getText().toString();
                sname=vname.getText().toString();
                sfname=vfname.getText().toString();
                smname=vmname.getText().toString();
                smob=vmob.getText().toString();
                saddr=vaddr.getText().toString();
                sclass=vclass.getText().toString();
                ssection=vsection.getText().toString();
                sct=vct.getText().toString();
                sfee=vfee.getText().toString();
                scmnt=vcmnt.getText().toString();
                ssadhr=vsadhr.getText().toString();
                spadhr=vpadhr.getText().toString();
                update();
                new updater().execute();
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


    public void update() {
        RequestQueue rq=Volley.newRequestQueue(this);
        StringRequest r1= new StringRequest(Request.Method.POST, "https://prodigyanand.000webhostapp.com/studentapi/admin_update_student.php", new Response.Listener<String>() {
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
                hm.put("r",temp);
                hm.put("n",sname);
                hm.put("f",sfname);
                hm.put("mn",smname);
                hm.put("m",smob);
                hm.put("ad",saddr);
                hm.put("cl",sclass);
                hm.put("s",ssection);
                hm.put("ct",sct);
                hm.put("fe",sfee);
                hm.put("co",scmnt);
                hm.put("as",ssadhr);
                hm.put("ap",spadhr);
                return hm;
            }
        };
        rq.add(r1);
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
            getData();
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

    private class updater extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Record");
            progressDialog.setMessage("Database updation on the way.....");
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
