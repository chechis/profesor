package com.example.chechis.profesor.adapter.profesor;

public class Tarea {

    private String id;
    private String tarea;
    private String nota;

    public Tarea() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Id: "+id+"\nTarea: "+tarea+"\nNota: "+nota+"\n\n";
    }
}
