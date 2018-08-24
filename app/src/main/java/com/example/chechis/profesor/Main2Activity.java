package com.example.chechis.profesor;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button bton = (Button) findViewById(R.id.btn_direccion);
        final TextInputLayout editDireccion = (TextInputLayout) findViewById(R.id.edit_direccion);
        final TextInputLayout editPuerto = (TextInputLayout) findViewById(R.id.edit_puerto);

        final Bundle parametro = new Bundle();

        bton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editDireccion!= null && editPuerto!= null){
                    String link = editDireccion.getEditText().getText().toString()+":"+editPuerto.getEditText().getText().toString();

                    Intent intent = new Intent(Main2Activity.this, InicioActivity.class);
                    parametro.putString("link", link);
                    intent.putExtras(parametro);
                    startActivity(intent);
                }


            }
        });

    }
}
