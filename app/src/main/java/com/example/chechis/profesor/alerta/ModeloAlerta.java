package com.example.chechis.profesor.alerta;

public class ModeloAlerta {

    public enum Cursos{
        Matematica, biologia, Lenguaje, Historia
    }

    public enum Estudiantes {
        Pedro, Lucas, Maria, Leydi
    }

    private int Id;
    private String nombreTarea;
    private Cursos cursos;
    private Estudiantes estudiantes;
    private String notaTarea;

    public ModeloAlerta(String nombreTarea, Cursos cursos, Estudiantes estudiantes, String notaTarea) {
        this.nombreTarea = nombreTarea;
        this.cursos = cursos;
        this.estudiantes = estudiantes;
        this.notaTarea = notaTarea;
    }

    public ModeloAlerta(int id, String nombreTarea, Cursos cursos, Estudiantes estudiantes, String notaTarea) {
        Id = id;
        this.nombreTarea = nombreTarea;
        this.cursos = cursos;
        this.estudiantes = estudiantes;
        this.notaTarea = notaTarea;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public Cursos getCursos() {
        return cursos;
    }

    public void setCursos(Cursos cursos) {
        this.cursos = cursos;
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
