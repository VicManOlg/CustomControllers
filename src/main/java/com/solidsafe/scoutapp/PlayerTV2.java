package com.solidsafe.scoutapp;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Victor
 */
public class PlayerTV2 {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty nombre = new SimpleStringProperty();
    private SimpleStringProperty a�o = new SimpleStringProperty();
    private SimpleStringProperty nacionalidad = new SimpleStringProperty();

    public int getId() {
        return id.get();
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
