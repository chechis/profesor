package com.example.chechis.profesor;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.chechis.profesor.adapter.tarea.Tarea;
import com.example.chechis.profesor.adapter.tarea.TareaAdapter;
import com.example.chechis.profesor.alerta.AlertaEditTarea;
import com.example.chechis.profesor.alerta.AlertaTareaNueva;
import com.example.chechis.profesor.alerta.ModeloAlerta;
import com.example.chechis.profesor.almacenamiento.BaseDatos;
import com.example.chechis.profesor.almacenamiento.Estructura;
import com.example.chechis.profesor.almacenamiento.PreferenceConstan;
import com.example.chechis.profesor.almacenamiento.Servicio;
import com.example.chechis.profesor.fragmento.FragmentProfesor;
import com.example.chechis.profesor.fragmento.FragmentAsignatura;
import com.example.chechis.profesor.fragmento.FragmentTareas;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
                                                    AlertaTareaNueva.NuevaListener, TareaAdapter.TareaListener,
                                                    AlertaEditTarea.EditarListener{
    private Servicio servicio;
    private SQLiteDatabase myDatabase;
    RecyclerView recyclerView;
    private TareaAdapter adapter;
    private ArrayList<Tarea> listaTarea;
    SharedPreferences pref;
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences(PreferenceConstan.PREFERENCE_NAME, MODE_PRIVATE);
        String urlPref = pref.getString(PreferenceConstan.PREF_KEY_USERNAME, null);
        url = "http://"+urlPref+"/respondiendo-HTTP/webapi/";

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_Layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle parametroBundle = this.getIntent().getExtras();
        String usuarioBundle = parametroBundle.getString("nombre");
        Toast.makeText(this, "Hola "+usuarioBundle, Toast.LENGTH_SHORT).show();
        toolbar.setSubtitle(usuarioBundle);

        listaTarea = new ArrayList<>();
        actualizarLista();
        llenandoAdapter(listaTarea);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.btn_flotatne);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertaTareaNueva alertaTareaNueva = new AlertaTareaNueva();
                alertaTareaNueva.show(getSupportFragmentManager(), "tarea nueva");

            }
        });


    }

    private void actualizarLista (){
        BaseDatos baseDatos = new BaseDatos(this);
        myDatabase = baseDatos.getWritableDatabase();

        Cursor cursor = myDatabase.rawQuery("SELECT * FROM "+ Estructura.EstructuraBase.TABLE_NAME+";", null);
        listaTarea.clear();
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Estructura.EstructuraBase.COLUMN_NAME_ID));
                String nombreTarea = cursor.getString(cursor.getColumnIndex(Estructura.EstructuraBase.COLUMN_NAME_TAREA));
                String estudiante = cursor.getString(cursor.getColumnIndex(Estructura.EstructuraBase.COLUMN_NAME_ESTUDIANTE));
                String asignatura = cursor.getString(cursor.getColumnIndex(Estructura.EstructuraBase.COLUMN_NAME_ASIGNATURA));
                String nota = cursor.getString(cursor.getColumnIndex(Estructura.EstructuraBase.COLUMN_NAME_NOTA));

                listaTarea.add(new Tarea(id, nombreTarea, estudiante, asignatura, nota));
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void llenandoAdapter (List<Tarea> lista){
        adapter = new TareaAdapter(lista);
        adapter.setTareaListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_tarea);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1,
                GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
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

        String nombreTarea = alerta.getNombreTarea();
        String curso = alerta.getCursos().toString();
        String estudiante = alerta.getEstudiantes().toString();
        String nota = alerta.getNotaTarea();

        BaseDatos baseDatos = new BaseDatos(this);
        SQLiteDatabase sq = baseDatos.getWritableDatabase();
        servicio = new Servicio(this);
        servicio.guardarTarea(nombreTarea, curso, estudiante, nota, baseDatos, this);
        sq.close();
        actualizarLista();
        adapter.notifyDataSetChanged();

    }

    @Override
    public void deleteTarea(int position) {
        final int posicion = position;
        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("¿Está seguro de que desea eliminar la tarea?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BaseDatos baseDatos = new BaseDatos(MainActivity.this);
                        SQLiteDatabase sq = baseDatos.getWritableDatabase();
                        Tarea tarea = listaTarea.get(posicion);
                        Servicio servicio = new Servicio(tarea, MainActivity.this);
                        servicio.eliminarTarea(tarea, baseDatos, MainActivity.this);
                        sq.close();
                        actualizarLista();
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).create().show();


    }

    @Override
    public void editTarea(int position) {

        Tarea tarea = listaTarea.get(position);
        int id= tarea.getId();
        String nombreTarea = tarea.getTarea();
        String asignatura = tarea.getAsignatura();
        String estudiante = tarea.getEstudiante();
        String nota = tarea.getNota();

        AlertaEditTarea editTarea = new AlertaEditTarea();
        editTarea.show(getSupportFragmentManager(), "Editar tarea");

        editTarea.getTxtId(id);
        editTarea.getTxtTarea(nombreTarea);
        editTarea.getTxtNota(nota);
    }

    @Override
    public void editarTarea(ModeloAlerta tarea) {
        int id = tarea.getId();
        String nombreTarea = tarea.getNombreTarea();
        String asignatura = tarea.getCursos().toString();
        String estudiante = tarea.getEstudiantes().toString();
        String nota = tarea.getNotaTarea();

        BaseDatos baseDatos = new BaseDatos(this);
        SQLiteDatabase sq = baseDatos.getWritableDatabase();
        Servicio servicio = new Servicio(this);
        servicio.modificarTarea(id, nombreTarea, estudiante, asignatura, nota, baseDatos, this);
        sq.close();
        actualizarLista();
        adapter.notifyDataSetChanged();
    }

}
