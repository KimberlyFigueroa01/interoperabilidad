package edu.uptc.software.empresa;

public class Cliente {

    private String nombre;
    private String id;

    public Cliente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Cliente() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    
}
