package com.example.chechis.profesor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chechis.profesor.adapter.tarea.Tarea;
import com.example.chechis.profesor.alerta.AlertaEditTarea;
import com.example.chechis.profesor.alerta.AlertaTareaNueva;
import com.example.chechis.profesor.alerta.Probando;

public class InicioActivity extends AppCompatActivity {

    Button btnInicio, btnAlerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnInicio =(Button) findViewById(R.id.btn_inicio);
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InicioActivity.this, MainActivity.class);
                startActivity(intent);
                //Intent intent = new Intent(InicioActivity.this, Main2Activity.class);
                //startActivity(intent);
            }
        });


    }


}
