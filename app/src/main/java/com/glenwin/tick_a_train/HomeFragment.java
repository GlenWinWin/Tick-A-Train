package com.glenwin.tick_a_train;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class HomeFragment extends Fragment {

    ReturnHost rhost = new ReturnHost();
    String host = rhost.returnHost();
    private String urlForImage = "http://"+host+"/sadtwo/uploads/";
    private String url = "http://"+host+"/sadtwo/login/get_user";
    ImageView ivProfile;
    String nameOfImage="",returnedEmail="";
    TextView reSult;
    String result;
    SharedPreferences preferences;
    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        returnedEmail = intent.getStringExtra("email_add");
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        preferences = this.getActivity().getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        ivProfile = (ImageView) rootView.findViewById(R.id.ivProfile);

        reSult = (TextView) rootView.findViewById(R.id.reSult);

        nameOfImage = returnedEmail + ".jpg";

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
        new AsyncTask<Void,String,String>(){

            @Override
            protected String doInBackground(Void... params) {
                InputStream isr = null;
                try{
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(url+"?EmailAddress="+returnedEmail); //YOUR PHP SCRIPT ADDRESS
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
                    returnSomething();
                }
                catch(Exception e){
                    Log.e("log_tag", "Error  converting result "+e.toString());
                }
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                reSult.setText(returnSomething());
            }
        }.execute();
        return rootView;
    }
    private String returnSomething(){
        SharedPreferences.Editor editor = preferences.edit();
        try {
            String s = "";
            JSONArray jArray = new JSONArray(result);

            for(int i=0; i<jArray.length();i++){
                JSONObject json = jArray.getJSONObject(i);

                s = s +
                        "Name : "+json.getString("firstname")+" "+json.getString("lastname")+"\n"+
                        "Gender : "+json.getString("gender")+"\n"+
                        "Bithday : "+json.getString("birthday")+"\n"+
                        "Email : "+json.getString("email")+"\n"+
                        "Home Address : "+json.getString("homeaddress")+"\n"+
                        "Mobile Using : "+json.getString("contactnumber")+"\n" +
                        "Load: " +((json.getString("ticket_load").isEmpty() )? "You don't have a load" : json.getString("ticket_load"))+"\n";
                editor.putString("email_key",json.getString("email"));
                editor.putString("ticket_load",json.getString("ticket_load"));
                //editor.putString("lat",String.valueOf(location.getLatitude()));
                //editor.putString("long",String.valueOf(location.getLongitude()));
                editor.commit();
            }
            return s;

        } catch (Exception e) {
            // TODO: handle exception
            Log.e("log_tag", "Error Parsing Data "+e.toString());
            return null;
        }
    }
}
