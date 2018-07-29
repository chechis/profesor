package com.example.chechis.profesor.almacenamiento;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.chechis.profesor.adapter.tarea.Tarea;

public class Servicio {

    private Tarea tarea;
    private Activity activity;

    public Servicio(Tarea tarea, Activity activity) {
        this.tarea = tarea;
        this.activity = activity;
    }

    public Servicio(Activity activity) {
        this.activity = activity;
    }

    public void guardarTarea (String tarea, String estudiante, String asignatura, String nota,
                                BaseDatos baseDatos, Activity activity){

        SQLiteDatabase sq = baseDatos.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put(Estructura.EstructuraBase.COLUMN_NAME_TAREA, tarea);
        content.put(Estructura.EstructuraBase.COLUMN_NAME_ESTUDIANTE, estudiante);
        content.put(Estructura.EstructuraBase.COLUMN_NAME_ASIGNATURA, asignatura);
        content.put(Estructura.EstructuraBase.COLUMN_NAME_NOTA, nota);
        sq.insert(Estructura.EstructuraBase.TABLE_NAME, null, content);
        Toast.makeText(activity, "Tarea " + tarea + " del estudiante "+estudiante+ " ha sido guardado", Toast.LENGTH_SHORT).show();

        sq.close();

    }

    public void modificarTarea (int id, String tarea, String estudiante, String asignatura, String nota,
                                  BaseDatos baseDatos, Activity activity){


        SQLiteDatabase sq = baseDatos.getWritableDatabase();
        ContentValues content = new ContentValues();

        String comparador = Estructura.EstructuraBase.COLUMN_NAME_ID +" LIKE "+ id;
        content.put(Estructura.EstructuraBase.COLUMN_NAME_TAREA, tarea);
        content.put(Estructura.EstructuraBase.COLUMN_NAME_ESTUDIANTE, estudiante);
        content.put(Estructura.EstructuraBase.COLUMN_NAME_ASIGNATURA, asignatura);
        content.put(Estructura.EstructuraBase.COLUMN_NAME_NOTA, nota);

        sq.update(Estructura.EstructuraBase.TABLE_NAME, content, comparador, null);

        Toast.makeText(activity, "Se ha actualizado la terea del estudiante  " + estudiante, Toast.LENGTH_SHORT).show();

        sq.close();
    }

    public void eliminarDonante (Tarea tarea, BaseDatos baseDatos, Activity activity){

        SQLiteDatabase sq = baseDatos.getWritableDatabase();

        sq.execSQL("DELETE FROM "+ Estructura.EstructuraBase.TABLE_NAME+" WHERE ID=?;",
                new Object[]{tarea.getId()});

        Toast.makeText(activity, "Se ha eliminado la tarea", Toast.LENGTH_SHORT).show();
        sq.close();
    }




}
