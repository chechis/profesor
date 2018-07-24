package com.example.chechis.profesor;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.chechis.profesor.adapter.asignatura.Asignatura;
import com.example.chechis.profesor.adapter.asignatura.AsignaturaAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private String url = "http://192.168.1.7:8084/respondiendo-HTTP/webapi/asignatura";
    private ArrayList<Asignatura> asignaturas= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2,
                GridLayoutManager.VERTICAL, false));

        final AsignaturaAdapter adapter = new AsignaturaAdapter(this, asignaturas);
        recyclerView.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(this);
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Por favor espere...");
        dialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        deserializarJSON(response);
                        adapter.notifyDataSetChanged();
                        if (dialog.isShowing()) dialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Main2Activity.this, "Error al realizar la peticion\n "+error.getMessage(), Toast.LENGTH_SHORT).show();
                        if (dialog.isShowing()) dialog.dismiss();
                    }
                });
        queue.add(jsonArrayRequest);


    }


    public void deserializarJSON (JSONArray jsonArray){

        for (int i=0; i < jsonArray.length(); i++){
            try {
                JSONObject item = jsonArray.getJSONObject(i);
                Asignatura asignatura = new Asignatura();
                asignatura.setId(item.getString("id"));
                asignatura.setAsignatura(item.getString("asignatura"));

                asignaturas.add(asignatura);
            }catch (JSONException e){
                Toast.makeText(Main2Activity.this, "Error al procesar la respuesta de la peticion", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
