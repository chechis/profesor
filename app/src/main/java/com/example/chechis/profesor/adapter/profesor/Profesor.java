package com.example.chechis.profesor.adapter.profesor;

public class Profesor {

    private String id;
    private String nombreUsuario;
    private String contrasena;
    private String rol;

    public Profesor() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString()
         {return "Id: "+id+"\nNombre Asignatura: "+nombreUsuario+"\nContrasena: "+contrasena+"\nRol :"+rol+"\n\n";
    }
}
