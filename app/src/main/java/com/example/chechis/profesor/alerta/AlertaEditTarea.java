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
import com.example.chechis.profesor.alerta.ModeloAlerta;

public class AlertaEditTarea extends DialogFragment {

    private EditarListener editarListener;
    public interface EditarListener{
        void editarTarea (ModeloAlerta tarea);
    }

    private Button btnRegistrar, btnCancelar;
    private TextInputLayout editTarea, editNota;
    private Spinner spnEstudiante, spnAsignatura;
    private EditText tareaEdit, notaEdit;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            editarListener = (EditarListener) context;
        }catch (ClassCastException ex){
            throw new ClassCastException("El contexto se debe implementar");
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

        tareaEdit = (EditText) dialogo.findViewById(R.id.edit_nombre_tarea2);
        notaEdit = (EditText) dialogo.findViewById(R.id.edit_nota21);

        spnEstudiante = (Spinner) dialogo.findViewById(R.id.spinner_estudiante);
        spnAsignatura = (Spinner) dialogo.findViewById(R.id.spinner_asignatura);

        llenando();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarTarea();
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

    private void editarTarea(){

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
                    estudiantes = ModeloAlerta.Estudiantes.Lucas;
                    break;
                case 2:
                    estudiantes= ModeloAlerta.Estudiantes.Maria;
                    break;
                case 3:
                    estudiantes = ModeloAlerta.Estudiantes.Leydi;
                    break;
            }
            ModeloAlerta.Cursos cursos = ModeloAlerta.Cursos.biologia;
            switch (spnAsignatura.getSelectedItemPosition()){
                case 0:
                    cursos= ModeloAlerta.Cursos.Matematica;
                    break;
                case 1:
                    cursos = ModeloAlerta.Cursos.biologia;
                    break;
                case 2:
                    cursos = ModeloAlerta.Cursos.Lenguaje;
                    break;
                case 3:
                    cursos = ModeloAlerta.Cursos.Historia;
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

                ModeloAlerta alerta = new ModeloAlerta(this.txtId,tarea, cursos, estudiantes, nota);
                editarListener.editarTarea(alerta);
                dismiss();

            }


        }

    }


    private void llenando (){
        tareaEdit.setText(this.txtTarea);
        notaEdit.setText(this.txtNota);

    }

    private int txtId;
    private String txtTarea;
    private String txtNota;


    public int getTxtId(int id) {
        return txtId= id;
    }

    public String getTxtTarea(String tarea) {
        return txtTarea= tarea;
    }

    public String getTxtNota(String nota) {
        return txtNota= nota;
    }

}
