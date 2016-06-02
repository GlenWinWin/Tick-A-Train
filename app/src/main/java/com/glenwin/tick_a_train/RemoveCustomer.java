package com.glenwin.tick_a_train;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.glenwin.host.ReturnHost;

public class RemoveCustomer extends Activity{

    ReturnHost rhost = new ReturnHost();
    String host = rhost.returnHost();
    private String delete_url = "http://"+host+"/train/deleteCustomer.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String PassengerID = intent.getStringExtra("id");
        InputStream isr = null;
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(delete_url+"?PassengerID="+PassengerID); //YOUR PHP SCRIPT ADDRESS
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            isr = entity.getContent();
            Toast.makeText(getApplicationContext(),"Successfully Deleted",Toast.LENGTH_LONG).show();
            startActivity(new Intent(RemoveCustomer.this,MainActivity2.class));
        }
        catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
        }
    }
}