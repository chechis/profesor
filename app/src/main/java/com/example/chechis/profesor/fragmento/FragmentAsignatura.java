package com.example.chechis.profesor.fragmento;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.example.chechis.profesor.adapter.tarea.Tarea;
import com.example.chechis.profesor.adapter.tarea.TareaAdapter;
import com.example.chechis.profesor.almacenamiento.PreferenceConstan;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentAsignatura extends Fragment {

    private String url = "http://192.168.1.10:8080/respondiendo-HTTP/webapi/asignatura";
    private ArrayList<Asignatura> asignaturas = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asignaturas, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.recycler_asignatura);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2,
                GridLayoutManager.VERTICAL, false));

        final AsignaturaAdapter adapter = new AsignaturaAdapter(getContext(), asignaturas);
        recyclerView.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(getContext());
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Por favor espere...");
        dialog.show();

        String urlAsignatura = "ll";


        Toast.makeText(getContext(), urlAsignatura, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getContext(), "Error al realizar la peticion\n "+error.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), "Error al procesar la respuesta de la peticion", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Asignaturas");
    }



}
