package Snake;

import java.awt.*;

public class Ventana extends javax.swing.JFrame{

    public Ventana(){

        this.setLocationRelativeTo(null);


        Panel fondo = new Panel(800,30);
        this.add(fondo);
        fondo.setBounds(10,10,800,800);
        fondo.setOpaque(false);

        Juego fondo2 = new Juego(800,30);
        this.add(fondo2);
        fondo2.setBounds(10,10,800,800);

        this.requestFocus(true);
    }



    public static void main(String args[]){

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ventana().setVisible(true);
            }
        });

    }
}
