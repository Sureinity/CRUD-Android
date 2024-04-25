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

public class Delete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        EditText txt_id_D = (EditText) findViewById(R.id.txt_id_D);

        TextView textView_D  = (TextView) findViewById(R.id.textView_D);
        Button btn_update_U = (Button) findViewById(R.id.btn_delete_D);

        btn_update_U.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = txt_id_D.getText().toString();

                // Instantiate the RequestQueue.o
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.86.134/PHP-Android/delete.php";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Success")){
                                    Toast.makeText(Delete.this, "Data Failed to Delete :(", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Delete.this, "Data Deleted!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView_D.setText("That didn't work!");
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("id", id);
                        return paramV;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }
}