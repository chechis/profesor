package com.example.chechis.profesor.modeloAlerta;

public class Tarea {

    private enum Cursos{
        Matematica, biologia, Lenguaje, Historia
    }

    private enum Estudiantes {
        Pedro, Lucas, Maria, Leydi
    }

    private String nombreTarea;
    private Cursos tarea;
    private Estudiantes estudiantes;
    private String notaTarea;

    public Tarea(String nombreTarea, Cursos tarea, Estudiantes estudiantes, String notaTarea) {
        this.nombreTarea = nombreTarea;
        this.tarea = tarea;
        this.estudiantes = estudiantes;
        this.notaTarea = notaTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public Cursos getTarea() {
        return tarea;
    }

    public void setTarea(Cursos tarea) {
        this.tarea = tarea;
    }

    public Estudiantes getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Estudiantes estudiantes) {
        this.estudiantes = estudiantes;
    }

    public String getNotaTarea() {
        return notaTarea;
    }

    public void setNotaTarea(String notaTarea) {
        this.notaTarea = notaTarea;
    }
}
