package com.solidsafe.scoutapp;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Victor
 */
public class PlayerTV {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty nombre = new SimpleStringProperty();
    private SimpleStringProperty edad = new SimpleStringProperty();
    private SimpleStringProperty posicion = new SimpleStringProperty();

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

    public String getEdad() {
        return edad.get();
    }

    public void setEdad(String edad) {
        this.edad.set(edad);
    }

    public String getPosicion() {
        return posicion.get();
    }

    public void setPosicion(String posicion) {
        this.posicion.set(posicion);
    }
    
    
}
