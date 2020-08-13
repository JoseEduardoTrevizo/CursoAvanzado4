package com.example.cursoavanzado;

public class Usuario {
    private int id;
    private String nombre;
    private String password;
    private String genero;
    private String rol;
    private String correo;


    public Usuario(int id, String nombre, String password, String genero, String rol, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.genero = genero;
        this.rol = rol;
        this.correo = correo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public String getGenero() {
        return genero;
    }

    public String getRol() {
        return rol;
    }

    public String getCorreo() {
        return correo;
    }
}
