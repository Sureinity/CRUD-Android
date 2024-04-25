package com.androidproject.android_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class Retrieve extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        EditText text_user_id = findViewById(R.id.text_user_id);
        TextView text_uname = findViewById(R.id.text_uname);
        TextView text_pword = findViewById(R.id.text_pword);
        TextView text_email = findViewById(R.id.text_email);
        Button btn_retrieve = findViewById(R.id.btn_retrieve);

        btn_retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = text_user_id.getText().toString();

                // Change URL to the PHP file responsible for retrieving data
                String url ="http://192.168.86.134/PHP-Android/retrieve.php";

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String uname = jsonObject.getString("uname");
                                    String pword = jsonObject.getString("pword");
                                    String email = jsonObject.getString("email");

                                    // Set retrieved data to EditText fields
                                    text_uname.setText("Username: " + uname);
                                    text_pword.setText("Password: " + pword);
                                    text_email.setText("Email: " + email);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(Retrieve.this, "User's data doesn't exist", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.getLocalizedMessage());
                        Toast.makeText(Retrieve.this, "Error occurred while retrieving data", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("id", id);
                        return params;
                    }
                };

                queue.add(stringRequest);
            }
        });
    }
}