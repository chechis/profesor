package com.example.chechis.profesor.almacenamiento;

import android.provider.BaseColumns;

public class Estructura {

    public Estructura (){

    }

    public static abstract class EstructuraBase implements BaseColumns{
        public static final String TABLE_NAME = "Profesor";
        public static final String COLUMN_NAME_ID ="Id";
        public static final String COLUMN_NAME_TAREA = "actividad";
        public static final String COLUMN_NAME_ESTUDIANTE = "estudiante";
        public static final String COLUMN_NAME_ASIGNATURA = "asignatura";
        public static final String COLUMN_NAME_NOTA = "nota";
    }

}
