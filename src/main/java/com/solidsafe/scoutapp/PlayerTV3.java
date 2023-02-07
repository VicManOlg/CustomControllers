package com.solidsafe.scoutapp;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Victor
 */
public class PlayerTV3 {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty nombre = new SimpleStringProperty();
    private SimpleStringProperty a�o = new SimpleStringProperty();
    private SimpleStringProperty nacionalidad = new SimpleStringProperty();
    private SimpleDoubleProperty precio = new SimpleDoubleProperty();

    public int getId() {
        return id.get();
    }

    public Double getPrecio() {
        return precio.get();
    }

    public void setPrecio(Double precio) {
        this.precio.set(precio);
    }
    
    public void setId(int id) {
        this.id.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getA�o() {
        return a�o.get();
    }

    public void setA�o(String a�o) {
        this.a�o.set(a�o);
    }

    public String getNacionalidad() {
        return nacionalidad.get();
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad.set(nacionalidad);
    }
}
