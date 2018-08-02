package com.example.chechis.profesor.alerta;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.chechis.profesor.R;

public class AlertaTareaNueva extends DialogFragment{


    private NuevaListener listener;
    public interface NuevaListener{
        void nuevaTarea (ModeloAlerta alerta);
    }

    private Button btnRegistrar, btnCancelar;
    private TextInputLayout editTarea, editNota;
    private Spinner spnEstudiante, spnAsignatura;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (NuevaListener) context;
        }catch (ClassCastException ex){
            throw new ClassCastException("El contexto debe implementar la interfaz ModeloAlerta");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogo= inflater.inflate(R.layout.alerta_tareas, null);

        builder.setView(dialogo);

        btnRegistrar=(Button) dialogo.findViewById(R.id.btn_registrar);
        btnCancelar=(Button) dialogo.findViewById(R.id.btn_cancelar);

        editTarea=(TextInputLayout) dialogo.findViewById(R.id.edit_nombre_tarea1);
        editNota=(TextInputLayout) dialogo.findViewById(R.id.edit_nota2);

        spnEstudiante = (Spinner) dialogo.findViewById(R.id.spinner_estudiante);
        spnAsignatura = (Spinner) dialogo.findViewById(R.id.spinner_asignatura);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevaTarea();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        return builder.create();
    }

    private void nuevaTarea(){

        boolean login = true;

        if (editTarea !=null && editNota!=null){

            String tarea= editTarea.getEditText().getText().toString();
            String nota = editNota.getEditText().getText().toString();

            ModeloAlerta.Estudiantes estudiantes = ModeloAlerta.Estudiantes.Pedro;
            switch (spnEstudiante.getSelectedItemPosition()){
                case 0:
                    estudiantes= ModeloAlerta.Estudiantes.Pedro;
                    break;
                case 1:
                    estudiantes = ModeloAlerta.Estudiantes.Leydi;
                    break;
                case 2:
                    estudiantes= ModeloAlerta.Estudiantes.Lucas;
                    break;
                case 3:
                    estudiantes = ModeloAlerta.Estudiantes.Maria;
                    break;
            }
            ModeloAlerta.Cursos cursos = ModeloAlerta.Cursos.biologia;
            switch (spnAsignatura.getSelectedItemPosition()){
                case 0:
                    cursos= ModeloAlerta.Cursos.biologia;
                    break;
                case 1:
                    cursos = ModeloAlerta.Cursos.Historia;
                    break;
                case 2:
                    cursos = ModeloAlerta.Cursos.Lenguaje;
                    break;
                case 3:
                    cursos = ModeloAlerta.Cursos.Matematica;
                    break;
            }


            if (editTarea.getEditText().getText().toString().equals("")){
                editTarea.setError("La tarea es requerida");
                login = false;
            }

            if (editNota.getEditText().getText().toString().equals("")){
                editNota.setError("La nota es requerida");
                login = false;
            }

            if (login){

                ModeloAlerta alerta = new ModeloAlerta(tarea, cursos, estudiantes, nota);
                listener.nuevaTarea(alerta);
                dismiss();

            }


        }

    }


}
