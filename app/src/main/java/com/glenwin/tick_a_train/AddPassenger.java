package com.glenwin.tick_a_train;

import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import com.glenwin.host.ReturnHost;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPassenger extends Activity{

    ReturnHost rhost = new ReturnHost();
    String host = rhost.returnHost();
    EditText etfname,etlname,etage,etphone_num;
    Button btnInsert;
    private String url = "http://"+host+"/train/addCustomers.php";

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_passenger);
        StrictMode.enableDefaults(); //STRICT MODE ENABLED
        etfname = (EditText) findViewById(R.id.etFirstname);
        etlname = (EditText) findViewById(R.id.etLastname);
        etage = (EditText) findViewById(R.id.etAge);
        etphone_num = (EditText) findViewById(R.id.etPhoneNumber);
        btnInsert = (Button) findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(etfname.getText().toString().isEmpty() && etlname.getText().toString().isEmpty() && etage.getText().toString().isEmpty() && etphone_num.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_LONG).show();
                }
                else{
                    doAdd();
                }
            }
        });

    }
    private void doAdd(){
        String result = "";
        String FirstName = etfname.getText().toString();
        String LastName = etlname.getText().toString();
        String Age = etage.getText().toString();
        String Mobile = etphone_num.getText().toString();
        InputStream isr = null;
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url+"?FirstName="+FirstName+"&LastName="
                    + LastName+"&Age="+Age+"&Mobile="+Mobile); //YOUR PHP SCRIPT ADDRESS
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            isr = entity.getContent();
            Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_LONG).show();
            startActivity(new Intent(AddPassenger.this,MainActivity2.class));
        }
        catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
        }


    }
}

