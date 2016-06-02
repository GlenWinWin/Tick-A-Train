package com.glenwin.tick_a_train;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.glenwin.host.ReturnHost;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;


public class AddSchedule extends ActionBarActivity {
    ReturnHost rhost = new ReturnHost();
    String host = rhost.returnHost();
    private String url = "http://"+host+"/sadtwo/login/add_schedule";
    ProgressDialog pDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        final String finalDateYear = i.getStringExtra("dateYear");
        final String finalDateMonth = i.getStringExtra("dateMonth");
        final String finalDateDay = i.getStringExtra("dateDay");
        final String finalHour = i.getStringExtra("timehour");
        final String finalMinute = i.getStringExtra("timeminute");
        final String finalAMPM = i.getStringExtra("timeAMPM");
        final String finalEmail = i.getStringExtra("email");
        final String finalTo = i.getStringExtra("to");
        final String finalFrom = i.getStringExtra("from");
        final String LRT = i.getStringExtra("what_lrt");
        final String lane = i.getStringExtra("lane");
        new AsyncTask<String,String,String>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(AddSchedule.this);
                pDialog.setMessage("Creating Schedule. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                InputStream isr = null;
                try{
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(url+"?dateYear="+finalDateYear+"&dateMonth="
                            +finalDateMonth+"&dateDay="+finalDateDay+"&timeHour="+finalHour+"&timeMinute="+finalMinute+"&timeAMPM="+finalAMPM+"&email="+finalEmail+"&to="+finalTo+"&from="+finalFrom+"&lrt="+LRT+"&lane="+lane); //YOUR PHP SCRIPT ADDRESS
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                    isr = entity.getContent();

                }
                catch(Exception e){
                    Log.e("log_tag", "Error in http connection " + e.toString());
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddSchedule.this,MainActivity.class);
                intent.putExtra("email_add",finalEmail);
                startActivity(intent);
                finish();
            }
        }.execute();
    }
}
