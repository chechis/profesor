package com.example.chechis.profesor.adapter.asignatura;

public class Asignatura {

    private String id;

    private String asignatura;

    public Asignatura() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    @Override
    public String toString() {
        return "Id: " +id+"\nNombre Asignatura: "+asignatura+"\n\n";
    }
}
