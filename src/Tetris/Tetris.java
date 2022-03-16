package Tetris;

import java.awt.BorderLayout;

        import javax.swing.JFrame;
        import javax.swing.JLabel;


public class Tetris extends JFrame {

    JLabel barraDeEstado;


    public Tetris() {

        barraDeEstado = new JLabel(" 0");
        add(barraDeEstado, BorderLayout.SOUTH);
        tablero tablero = new tablero(this);
        add(tablero);
        tablero.empezar();

       setSize(200, 400);
       setTitle("Tetris");
       setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JLabel getBarraDeEstado() {
        return barraDeEstado;
    }

    public static void main(String[] args) {

        Tetris juego = new Tetris();
        juego.setLocationRelativeTo(null);
        juego.setVisible(true);

    }
}