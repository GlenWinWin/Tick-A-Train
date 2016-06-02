package com.glenwin.tick_a_train;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.glenwin.host.ReturnHost;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCustomer extends Activity{

    ReturnHost rhost = new ReturnHost();
    String host = rhost.returnHost();
    EditText etFname,etLname,etAge,etPhone;
    private String url = "http://"+host+"/train/getCustomer.php";
    private String update_url = "http://"+host+"/train/updateCustomer.php";
    private String PassengerID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_passengers);
        Intent intent = getIntent();
        PassengerID = intent.getStringExtra("id");
        String result = "";
        InputStream isr = null;
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url+"?PassengerID="+PassengerID); //YOUR PHP SCRIPT ADDRESS
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            isr = entity.getContent();
        }
        catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
        }
//convert response to string
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();
            result=sb.toString();
        }
        catch(Exception e){
            Log.e("log_tag", "Error  converting result "+e.toString());
        }

//parse json data
        try {
            String s = "";
            JSONArray jArray = new JSONArray(result);
            JSONObject json = jArray.getJSONObject(0);

            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View formElementsView = inflater.inflate(R.layout.activity_update_customer, null, false);
            etFname = (EditText) formElementsView.findViewById(R.id.editTextFirstname);
            etLname = (EditText) formElementsView.findViewById(R.id.editTextLastname);
            etAge = (EditText) formElementsView.findViewById(R.id.editTextAge);
            etPhone = (EditText) formElementsView.findViewById(R.id.editTextPhone);

            etFname.setText(json.getString("firstname"));
            etLname.setText(json.getString("lastname"));
            etAge.setText(json.getString("age"));
            etPhone.setText(json.getString("phone_number"));

            new AlertDialog.Builder(this)
                    .setView(formElementsView)
                    .setTitle("Edit Record")
                    .setPositiveButton("Save Changes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    upDate();
                                    dialog.cancel();
                                }

                            }).show();

        } catch (Exception e) {
            // TODO: handle exception
            Log.e("log_tag", "Error Parsing Data "+e.toString());
        }
    }

    private void upDate(){
        InputStream isr = null;
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(update_url+"?FirstName="+etFname.getText().toString()+"&LastName="
                    +etLname.getText().toString()+"&Age="+etAge.getText().toString()+"&Mobile="+etPhone.getText().toString()+"&PassengerID="+PassengerID); //YOUR PHP SCRIPT ADDRESS
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            isr = entity.getContent();
            Toast.makeText(getApplicationContext(),"Successfully Updated",Toast.LENGTH_LONG).show();
            startActivity(new Intent(UpdateCustomer.this,MainActivity2.class));
        }
        catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
        }

    }
}