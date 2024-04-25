package com.androidproject.android_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class Update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        EditText txt_id_U = (EditText) findViewById(R.id.txt_id_U);
        EditText txt_uname_U = (EditText)findViewById(R.id.txt_uname_U);
        EditText txt_pword_U = (EditText)findViewById(R.id.txt_pword_U);
        EditText txt_email_U = (EditText)findViewById(R.id.txt_email_U);
        TextView textView_U  = (TextView) findViewById(R.id.textView_U);
        Button btn_update_U = (Button) findViewById(R.id.btn_update_U);

        btn_update_U.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = txt_id_U.getText().toString();
                String uname = txt_uname_U.getText().toString();
                String pword = txt_pword_U.getText().toString();
                String email = txt_email_U.getText().toString();

                // Instantiate the RequestQueue.o
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.86.134/PHP-Android/update.php";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Success")){
                                    Toast.makeText(Update.this, "Data Failed to Update :(", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Update.this, "Data Updated", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView_U.setText("That didn't work!");
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("id", id);
                        paramV.put("uname", uname);
                        paramV.put("pword", pword);
                        paramV.put("email", email);
                        return paramV;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }
}