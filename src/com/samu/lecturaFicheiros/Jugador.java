package com.samu.lecturaFicheiros;

import java.io.Serializable;

public class Jugador implements Serializable {
    String nombre_apellidos;

    String genero;

    int edad;

    public Jugador(String nombre_apellidos, String genero, int edad) {
        this.nombre_apellidos = nombre_apellidos;
        this.genero = genero;
        this.edad = edad;
    }

    public Jugador() {
        
    }

    public String getNombre_apellidos() {
        return nombre_apellidos;
    }

    public void setNombre_apellidos(String nombre_apellidos) {
        this.nombre_apellidos = nombre_apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre y apellidos = " + nombre_apellidos + '\'' +
                ", genero='" + genero + '\'' +
                ", edad=" + edad ;
    }






    }


