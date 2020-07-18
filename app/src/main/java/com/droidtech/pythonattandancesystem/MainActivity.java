package com.droidtech.pythonattandancesystem;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    ArrayList<Data> list = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        getData();


    }

    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", "onResponse: " + response);
                progressDialog.dismiss();
                try {
                    JSONObject object = new JSONObject(response);
                    boolean error = object.getBoolean("error");
                    if (!error) {
                        JSONArray array = object.getJSONArray("records");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject single = array.getJSONObject(i);
                            String name = single.getString("data_name");
                            String id = single.getString("data_user");
                            String time = single.getString("data_time");
                            String date = single.getString("data_date");
                            Data data = new Data(id, name, time, date);
                            list.add(data);
                        }
                        setAdapter(list);
                    } else {
                        Toast.makeText(getApplicationContext(), "No record found", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "onErrorResponse: " + error.getMessage());

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setAdapter(ArrayList<Data> list) {
        DataAdapter adapter = new DataAdapter(list, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}