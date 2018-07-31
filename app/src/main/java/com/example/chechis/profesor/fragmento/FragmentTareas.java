package com.example.chechis.profesor.fragmento;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.chechis.profesor.R;
import com.example.chechis.profesor.adapter.tarea.Tarea;
import com.example.chechis.profesor.adapter.tarea.TareaAdapter;
import com.example.chechis.profesor.alerta.AlertaEditTarea;
import com.example.chechis.profesor.alerta.AlertaTareaNueva;
import com.example.chechis.profesor.alerta.ModeloAlerta;
import com.example.chechis.profesor.alerta.Probando;
import com.example.chechis.profesor.almacenamiento.BaseDatos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentTareas extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tareas, container, false);
        return view;
    }



}
