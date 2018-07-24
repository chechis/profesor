package com.example.chechis.profesor.fragmento;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.chechis.profesor.adapter.asignatura.Asignatura;
import com.example.chechis.profesor.adapter.asignatura.AsignaturaAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FragmentAsignatura extends Fragment {

    private String url = "http://192.168.1.7:8084/respondiendo-HTTP/webapi/asignatura";
    private ArrayList<Asignatura> asignaturas= new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asignaturas, container, false);

        return view;
    }


}
