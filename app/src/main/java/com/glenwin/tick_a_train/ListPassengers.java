package com.glenwin.tick_a_train;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.glenwin.host.ReturnHost;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListPassengers extends ListActivity{

    ReturnHost rhost = new ReturnHost();
    String host = rhost.returnHost();
    ListView lView = null;
    ArrayList<HashMap<String, String>> customerList;
    private String url = "http://"+host+"/train/getAllCustomers.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_passengers);
        customerList = new ArrayList<HashMap<String, String>>();
        lView = (ListView) findViewById(android.R.id.list);
        lView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    final int position, final long id) {
                final CharSequence[] options = {"View Details", "Edit", "Delete"};

                new AlertDialog.Builder(ListPassengers.this).setTitle("Options")
                        .setItems(options, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                String pid = ((TextView) view.findViewById(R.id.pid)).getText().toString();
                                if (item == 0) {
                                    Intent intent = new Intent(ListPassengers.this, View_Details.class);
                                    intent.putExtra("id", pid);
                                    startActivity(intent);
                                }
                                else if (item == 1) {
                                    Intent updateintent = new Intent(ListPassengers.this, UpdateCustomer.class);
                                    updateintent.putExtra("id",pid);
                                    startActivity(updateintent);
                                }
                                else{
                                    Intent d_intent = new Intent(ListPassengers.this, RemoveCustomer.class);
                                    d_intent.putExtra("id",pid);
                                    startActivity(d_intent);
                                }
                                dialog.dismiss();

                            }
                        }).show();
            }
        });
        String result = "";
        InputStream isr = null;
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url); //YOUR PHP SCRIPT ADDRESS
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

            for(int i=0; i<jArray.length();i++){
                JSONObject json = jArray.getJSONObject(i);

                String id = json.getString("id");
                String fname = json.getString("firstname");
                String lname = json.getString("lastname");
                String age = json.getString("age");
                String phone_number = json.getString("phone_number");

                HashMap<String, String> map = new HashMap<String, String>();

                // adding each child node to HashMap key => value
                map.put("id", id);
                map.put("firstname",fname);
                map.put("lastname",lname);
                map.put("age",age);
                map.put("phone_number",phone_number);

                // adding HashList to ArrayList
                customerList.add(map);
            }
            doDisplay();

        } catch (Exception e) {
            // TODO: handle exception
            Log.e("log_tag", "Error Parsing Data "+e.toString());
        }
    }
    private void doDisplay(){
        ListAdapter adapter = new SimpleAdapter(
                ListPassengers.this, customerList,
                R.layout.list_customer, new String[] { "id",
                "firstname","lastname","age","phone_number"},
                new int[] { R.id.pid, R.id.fName,R.id.lName,R.id.aGe,R.id.phone_Number});
        // updating listview
        setListAdapter(adapter);
    }
}

