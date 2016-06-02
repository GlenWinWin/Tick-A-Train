package com.glenwin.tick_a_train;

import com.glenwin.host.ReturnHost;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ErrorActivity extends Activity{

    ReturnHost rhost = new ReturnHost();
    String host = rhost.returnHost();
    private String url = "http://"+host+"/sadtwo/login/do_login";
    EditText etEmail,etPassword;
    Button btnLogin;
    ProgressDialog pDialog,progressDialog;
    TextView tvCreateAccount,refresh;
    MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        doFirst();
    }
    private void doFirst(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle(Html.fromHtml("<font color='#FF0000'>Login Error!!</font>"));
        builder1.setMessage(Html.fromHtml("<font color='#FF0000'>Incorrect Email or Password</font>"));
        builder1.setIcon(android.R.drawable.ic_menu_close_clear_cancel);
        builder1.setCancelable(true);
        builder1.setNegativeButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();

        Intent intent = getIntent();
        String returnedEmail = intent.getStringExtra("email_add");
        tvCreateAccount = (TextView) findViewById(R.id.tvCreateAccount);
        refresh = (TextView) findViewById(R.id.refresh);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etEmail.setText(returnedEmail);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new OnClickListener() {

            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(etEmail.getText().toString().trim().isEmpty() && etPassword.getText().toString().trim().isEmpty()){
                    etEmail.setError("Email is required");
                    etPassword.setError("Password is required");
                }
                else if(etEmail.getText().toString().trim().isEmpty()){
                    etEmail.setError("Email is required");
                }
                else if(etPassword.getText().toString().trim().isEmpty()){
                    etPassword.setError("Password is required");
                }
                else{
                    new ValidateUser().execute();
                }
            }
        });
        tvCreateAccount.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
        refresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new Refresh().execute();
            }
        });
    }
    private void createAccount(){
        mp = MediaPlayer.create(ErrorActivity.this, R.raw.create);
        mp.start();
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ErrorActivity.this);
        builder1.setTitle(Html.fromHtml("<font color='#0000FF'>Create Account Information!!</font>"));
        builder1.setMessage("Go to the nearest LRT or MRT station and please be guided by the Officer in Charge in order for you to have an account." + "\n\n" + "Thank You for patronizing LRT/MRT" + "\n\n" + "~LRTA");
        builder1.setIcon(android.R.drawable.ic_menu_info_details);
        builder1.setCancelable(true);
        builder1.setNegativeButton("Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        mp.stop();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    class Refresh extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ErrorActivity.this);
            progressDialog.setMessage("Refreshing. Please wait...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            doThis();
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            progressDialog.dismiss();
            doFirst();
        }

    }
    private void doThis(){
        String result = "";
        InputStream isr = null;
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url+"?EmailAddress="+email+"&Password="+password); //YOUR PHP SCRIPT ADDRESS
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            isr = entity.getContent();
        }
        catch(Exception e){
            Log.e("log_tag", "Error in http connection " + e.toString());
        }
//convert response to string
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();
            result=sb.toString();
        }
        catch(Exception e){
            Log.e("log_tag", "Error  converting result "+e.toString());
        }
    }
    class ValidateUser extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ErrorActivity.this);
            pDialog.setMessage("Validating account. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            validate();
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
        }

    }
    private void validate(){
        String result = "";
        InputStream isr = null;
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url+"?EmailAddress="+email+"&Password="+password); //YOUR PHP SCRIPT ADDRESS
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            isr = entity.getContent();
        }
        catch(Exception e){
            Log.e("log_tag", "Error in http connection " + e.toString());
        }
//convert response to string
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line;
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
        if(result.contains("1")){
            Intent intent = new Intent(ErrorActivity.this,MainActivity.class);
            intent.putExtra("email_add",etEmail.getText().toString().trim());
            startActivity(intent);
            finish();
        }
        else if(result.contains("0")){
            Intent intent = new Intent(ErrorActivity.this,ErrorActivity.class);
            intent.putExtra("email_add",etEmail.getText().toString().trim());
            startActivity(intent);
            finish();
        }
    }
}
