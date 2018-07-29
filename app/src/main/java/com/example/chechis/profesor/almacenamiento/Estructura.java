package com.example.chechis.profesor.almacenamiento;

import android.provider.BaseColumns;

public class Estructura {

    public Estructura (){

    }

    public static abstract class EstructuraBase implements BaseColumns{
        public static final String TABLE_NAME = "Tarea";
        public static final String COLUMN_NAME_ID ="Id";
        public static final String COLUMN_NAME_TAREA = "Nombre Tarea";
        public static final String COLUMN_NAME_ESTUDIANTE = "Estudiante";
        public static final String COLUMN_NAME_ASIGNATURA = "Asignatura";
        public static final String COLUMN_NAME_NOTA = "Nota";
    }

}
