package com.samu.lecturaFicheiros;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

// Crearemos una librería para todos estos JOptionPane

        JOptionPane.showMessageDialog(null, " MINIJUEGOS.SS ");


        JOptionPane.showMessageDialog(null, " Bienvenido Jugador! " + "\n" + " Este juego está dividido en 3 minijuegos," +
                " que son los siguientes :" + "\n" +
                " 1. Piedra, Papel y Tijera " + "\n" + " 2. Adivinanza " + "\n" + " 3. T-Rex ");


        /*JOptionPane.showMessageDialog(null," Antes de eligir el juego con el que empezará debe introducir sus datos");
        JOptionPane.showInputDialog(" Nombre y apellidos ");
        JOptionPane.showInputDialog(" Género ");
        JOptionPane.showInputDialog(" Edad ");*/

        JOptionPane.showMessageDialog(null, " Ahora que sabe cual es cada juego, para iniciar el que usted desee," +
                " lo único que debe hacer es teclear el número que corresponde con el juego que quiere jugar " + "\n"
        );


        boolean salir = false;
        int opcion = 0;


        while (!salir) {
            JOptionPane.showMessageDialog(null, " El menú tiene 4 opciones : " + " Las tres primeras son para los minijuegos correspondientes y" +
            " la cuarta opción es para salir del juego " + "\n" + "\n" + " Opción 1" + "\n" + "Opción 2 " + "\n" + "Opción 3" + "\n" + "Opción 4");


            opcion = Integer.parseInt(JOptionPane.showInputDialog(" Introduce la opción que desee"));


            switch (opcion) {

                case 1:
                    JOptionPane.showMessageDialog(null, " Has seleccionado la opción 1 ");
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null, " Has seleccionado la opción 2 ");
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, " Has seleccionado la opción 3 ");
                    break;

                case 4:
                    JOptionPane.showMessageDialog(null, " FIN DEL MINIJUEGO ");
                    salir = true;
                    break;


                case 5:

                    break;


                default:
                    JOptionPane.showMessageDialog(null, " Solo números entre el 1 y el 4 ");
            }
        }


    }
}
