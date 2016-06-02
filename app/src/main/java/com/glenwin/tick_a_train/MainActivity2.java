package com.glenwin.tick_a_train;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.glenwin.host.ReturnHost;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class MainActivity2 extends Activity{
    /** Called when the activity is first created. */
    ReturnHost rhost = new ReturnHost();
    String host = rhost.returnHost();
    private String urlForImage = "http://"+host+"/train/images/";
    Button btnAdd,btnView,btnLogout;
    ImageView ivProfile;
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String returnedEmail = intent.getStringExtra("email_add");
        StrictMode.enableDefaults(); //STRICT MODE ENABLED
        btnAdd = (Button) findViewById(R.id.btnAddPassenger);
        btnView = (Button) findViewById(R.id.btnViewPassengers);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        ivProfile = (ImageView) findViewById(R.id.ivProfile);
        btnAdd.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(MainActivity2.this, AddPassenger.class);
                startActivity(i);
            }
        });
        btnView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(MainActivity2.this, ListPassengers.class);
                startActivity(i);
            }
        });
        btnLogout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this,"Successfully Logout!",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity2.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        String nameOfImage = returnedEmail + ".jpg";

        String urlForImageServer = urlForImage + nameOfImage;

        new AsyncTask<String,Void,Bitmap>(){

            @Override
            protected Bitmap doInBackground(String... params) {
                String url = params[0];
                Bitmap icon = null;

                try {
                    InputStream in = new java.net.URL(url).openStream();
                    icon = BitmapFactory.decodeStream(in);
                } catch(MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return icon;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                ivProfile.setImageBitmap(bitmap);
            }
        }.execute(urlForImageServer);
    }

}
