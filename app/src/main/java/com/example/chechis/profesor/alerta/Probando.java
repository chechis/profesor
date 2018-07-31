package com.example.chechis.profesor.alerta;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.example.chechis.profesor.R;
import com.example.chechis.profesor.adapter.tarea.Tarea;

public class Probando extends DialogFragment {

    private TareasListener listener;
    public interface TareasListener {
        void agregarTarea (Tarea tarea);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (TareasListener) context;
        }catch (ClassCastException ex){
            throw new ClassCastException("El contexto debe implementar la interfaz ModeloAlerta");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogo = inflater.inflate(R.layout.alerta_tareas, null);

        builder.setView(dialogo);

        return builder.create();



    }
}
