package com.samu.lecturaFicheiros;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

// Crearemos una librería para todos estos JOptionPane

        JOptionPane.showMessageDialog(null, " Bienvenido Jugador! " + "\n" + " Este juego está dividido en 3 minijuegos," +
                " que son los siguientes :" + "\n" +
                " 1. Piedra, Papel y Tijera " + "\n" + " 2. Adivinanza " +"\n" + " 3. Aun no se sabe ");

        /*JOptionPane.showMessageDialog(null," Antes de eligir el juego con el que empezará debe introducir sus datos");
        JOptionPane.showInputDialog(" Nombre y apellidos ");
        JOptionPane.showInputDialog(" Género ");
        JOptionPane.showInputDialog(" Edad ");*/

        JOptionPane.showMessageDialog(null, " Ahora que sabe cual es cada juego, para iniciar el que usted desee," +
                " lo único que debe hacer es teclear el número que corresponde con el juego que quiere jugar " + "\n"
                );

        JOptionPane.showMessageDialog(null, " ADVERTENCIA!" + "\n" + " si quiere salir del juego lo único que debe hacer es pulsar 0 ");

        JOptionPane.showInputDialog(" Pulsa un número para eligir el juego que quiera ");


    }
}
