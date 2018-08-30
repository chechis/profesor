package com.example.chechis.profesor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.chechis.profesor.Inicio.BaseDatosInicio;
import com.example.chechis.profesor.Inicio.ServicioInicio;
import com.example.chechis.profesor.almacenamiento.PreferenceConstan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InicioActivity extends AppCompatActivity {


    private SharedPreferences pref;
    private Button btnInicio;
    private TextInputLayout usuarioEdit, contrasenaEdit;
    private String url = null;
    private String urlBundle = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        TextView textView = (TextView) findViewById(R.id.txt_inicio_dir);

        Bundle parametro = this.getIntent().getExtras();
        urlBundle = parametro.getString("url");
        url = "http://" + urlBundle + "/respondiendo-HTTP/webapi/profesor";
        Toast.makeText(this, url, Toast.LENGTH_LONG).show();


        btnInicio =(Button) findViewById(R.id.btn_inicio);
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciar(view);
            }
        });



        if (urlBundle!= null && url != null){
            json(url);

        }if (urlBundle== ""){
            pref = getSharedPreferences(PreferenceConstan.PREFERENCE_NAME, MODE_PRIVATE);
            String urlPref = pref.getString(PreferenceConstan.PREF_KEY_USERNAME, null);
            url = "http://"+urlPref+"/respondiendo-HTTP/webapi/profesor";
            textView.setText(url);
            json(url);
        }else {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
            Toast.makeText(this, "Error vuelve a ingresar la direccion y puerto", Toast.LENGTH_SHORT).show();

        }







    }

    private void json (String url){

        RequestQueue queue = Volley.newRequestQueue(InicioActivity.this);
        final ProgressDialog dialog = new ProgressDialog(InicioActivity.this);
        dialog.setMessage("Por favor espere...");
        dialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        deserializarJSON(response);
                        if (dialog.isShowing()) dialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InicioActivity.this, "Error al realizar la peticion",
                                Toast.LENGTH_LONG).show();
                        if (dialog.isShowing()) dialog.dismiss();
                    }
                });
        queue.add(jsonArrayRequest);
    }

    public void deserializarJSON (JSONArray jsonArray){

        BaseDatosInicio baseDatos = new BaseDatosInicio(InicioActivity.this);

        for (int i=0; i < jsonArray.length(); i++){
            try {
                JSONObject item = jsonArray.getJSONObject(i);

                String id = item.getString("id");
                String usuario = item.getString("nombre");
                String contrasena = item.getString("contrasena");
                String rol = item.getString("rol");

                ServicioInicio servicioInicio = new ServicioInicio(usuario, contrasena, rol, this);
                servicioInicio.almacenarUsuario(id, usuario, contrasena, rol, baseDatos);


            }catch (JSONException e){
                Toast.makeText(InicioActivity.this, "Error al procesar la respuesta de la peticion", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void iniciar (View view){

        boolean login = true;
        Bundle parametro = new Bundle();

        String rol= "1";

        usuarioEdit = (TextInputLayout) findViewById(R.id.edit_inicio_username);
        contrasenaEdit = (TextInputLayout) findViewById(R.id.edit_inicio_password);

        if (usuarioEdit.getEditText().getText().toString() != null && contrasenaEdit.getEditText().getText().toString()!=null){

            String nombreUsuario = usuarioEdit.getEditText().getText().toString();
            String contra = contrasenaEdit.getEditText().getText().toString();

            if (nombreUsuario.equals("")){
                usuarioEdit.setError("El usuairo es requerido");
                login=false;
            }
            if (contra.equals("")){
                contrasenaEdit.setError("La contraseña es requerida");
                login=false;
            }
            if (login){
                BaseDatosInicio baseDatos = new BaseDatosInicio(this);

                ServicioInicio servicioInicio = new ServicioInicio(nombreUsuario, contra, rol, this);
                if (servicioInicio.confirmarUsuario(nombreUsuario, contra, rol, baseDatos, this)==2 ){
                    Intent intent = new Intent(this, MainActivity.class);
                    parametro.putString("nombre", nombreUsuario);
                    intent.putExtras(parametro);
                    startActivity(intent);

                }

            }


        }
    }

}
