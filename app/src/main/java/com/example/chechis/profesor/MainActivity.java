package com.example.chechis.profesor;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.chechis.profesor.adapter.tarea.Tarea;
import com.example.chechis.profesor.adapter.tarea.TareaAdapter;
import com.example.chechis.profesor.alerta.AlertaTareaNueva;
import com.example.chechis.profesor.alerta.ModeloAlerta;
import com.example.chechis.profesor.fragmento.FragmentProfesor;
import com.example.chechis.profesor.fragmento.FragmentAsignatura;
import com.example.chechis.profesor.fragmento.FragmentTareas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
                                                    AlertaTareaNueva.NuevaListener{

    private String url = "http://192.168.1.7:8084/respondiendo-HTTP/webapi/tarea";
    private ArrayList<Tarea> tareas= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_Layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,
                new FragmentProfesor()).commit();


        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_tarea);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2,
                GridLayoutManager.VERTICAL, false));

        final TareaAdapter adapter = new TareaAdapter(MainActivity.this, tareas);
        recyclerView.setAdapter(adapter);

        //RequestQueue queue = Volley.newRequestQueue(getContext());
        //final ProgressDialog dialog = new ProgressDialog(getContext());
        //dialog.setMessage("Por favor espere...");
        //dialog.show();


        //JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
        //new Response.Listener<JSONArray>() {
        //@Override
        //public void onResponse(JSONArray response) {
        //deserializarJSON(response);
        //adapter.notifyDataSetChanged();
        //if (dialog.isShowing()) dialog.dismiss();
        // }
        //},
        //new Response.ErrorListener() {
        //@Override
        //public void onErrorResponse(VolleyError error) {
        //Toast.makeText(getContext(), "Error al realizar la peticion\n "+error.getMessage(), Toast.LENGTH_SHORT).show();
        //if (dialog.isShowing()) dialog.dismiss();
        //}
        //});
        //queue.add(jsonArrayRequest);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.btn_flotatne);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertaTareaNueva alertaTareaNueva = new AlertaTareaNueva();
                alertaTareaNueva.show(getSupportFragmentManager(), "tarea nueva");

            }
        });


    }

    public void deserializarJSON (JSONArray jsonArray){

        for (int i=0; i < jsonArray.length(); i++){
            try {
                JSONObject item = jsonArray.getJSONObject(i);
                Tarea tarea = new Tarea();
                //tarea.setId(item.getString("id"));
                tarea.setTarea(item.getString("tarea"));
                tarea.setNota(item.getString("nota"));

                tareas.add(tarea);

            }catch (JSONException e){
                Toast.makeText(MainActivity.this, "Error al procesar la respuesta de la peticion", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_Layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.nav1:
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,
                        new FragmentProfesor()).commit();
                break;

            case R.id.nav2:
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,
                        new FragmentAsignatura()).commit();
                break;

            case R.id.nav3:
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,
                        new FragmentTareas()).commit();

                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_Layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void nuevaTarea(ModeloAlerta alerta) {
        
    }
}
