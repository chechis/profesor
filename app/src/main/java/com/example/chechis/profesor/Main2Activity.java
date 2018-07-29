package com.example.chechis.profesor;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText editText,editText2;
    Button boton1, boton2,boton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText=(EditText) findViewById(R.id.Texto);
        editText2=(EditText) findViewById(R.id.editText2);
        boton1= (Button)findViewById(R.id.Guardar);
        boton2= (Button)findViewById(R.id.Eliminar);
        boton3= (Button)findViewById(R.id.modificar);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contenido=editText.getText().toString();
                if(!contenido.equals("")) {
                    ContentValues values = new ContentValues();
                    values.put(ProviderContent.Texto, contenido);
                    getContentResolver().insert(ProviderContent.CONTENEDORURI, values);
                    Toast.makeText(getApplicationContext(), "Nuevo registro ingresado " + ProviderContent.CONTENEDORURI, Toast.LENGTH_LONG).show();
                }
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt= editText.getText().toString();
                ContentValues valores = new ContentValues();
                valores.put(ProviderContent.Texto,txt);
                getContentResolver().delete(ProviderContent.CONTENEDORURI, ProviderContent.Texto +" = "+"\""+txt+"\"",null);
                Toast.makeText(Main2Activity.this, "Se eliminó el registro", Toast.LENGTH_SHORT).show();
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contenido=editText2.getText().toString();//modificación
                String txt= editText.getText().toString();//texto
                ContentValues valores = new ContentValues();
                valores.put(ProviderContent.Texto,contenido);
                getContentResolver().update(ProviderContent.CONTENEDORURI,valores,ProviderContent.Texto +" = "+"\""+txt+"\"",null);
                Toast.makeText(Main2Activity.this, "Se modificó el registro", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
