package Tetris;

import Tetris.formas.formasTetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class tablero extends JPanel implements ActionListener {


    final int anchoTablero = 10;
    final int altoTablero = 22;

    Timer temporizador;
    boolean objetoCaido = false;
    boolean empezado = false;
    boolean pausado = false;
    int lineasConseguidas = 0;
    int actualX = 0;
    int actualY = 0;
    JLabel barraDeEstado;
    formas actualPieza;
    formasTetris[] tablero;



    public tablero(Tetris trabajo) {

        setFocusable(true);
        actualPieza = new formas();
        temporizador = new Timer(400, this);
        temporizador.start();

        barraDeEstado =  trabajo.getBarraDeEstado();
        tablero = new formasTetris[anchoTablero * altoTablero];
        addKeyListener(new TAdapter());
        limpiarTablero();
    }

    public void actionPerformed(ActionEvent e) {
        if (objetoCaido) {
           objetoCaido = false;
            nuevaPieza();
        } else {
            unaLineaAbajo();
        }
    }

    int anchoCuadrado() { return (int) getSize().getWidth() / anchoTablero; }
    int altoCuadrado() { return (int) getSize().getHeight() / altoTablero; }
    formasTetris formaEn(int x, int y) { return tablero[(y * anchoTablero) + x]; }


    public void empezar()
    {
        if (pausado)
            return;

        empezado = true;
        objetoCaido = false;
        lineasConseguidas = 0;
        limpiarTablero();

        nuevaPieza();
        temporizador.start();
    }

    private void pausar()
    {
        if (!empezado)
            return;

       pausado = !pausado;
        if (pausado) {
            temporizador.stop();
            barraDeEstado.setText("pausado");
        } else {
            temporizador.start();
            barraDeEstado.setText(String.valueOf(lineasConseguidas));
        }
        repaint();
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        Dimension tamaño = getSize();
        int topeTablero = (int) tamaño.getHeight() - altoTablero * altoCuadrado();


        for (int i = 0; i < altoTablero; ++i) {
            for (int j = 0; j < anchoTablero; ++j) {
                formasTetris forma = formaEn(j, altoTablero - i - 1);
                if (forma != formasTetris.SinForma)
                    dibujarCuadrado(g, 0 + j * anchoCuadrado(),
                            topeTablero + i * altoCuadrado(), forma);
            }
        }

        if (actualPieza.getForma() != formasTetris.SinForma) {
            for (int i = 0; i < 4; ++i) {
                int x = actualX + actualPieza.x(i);
                int y = actualY -actualPieza.y(i);
                dibujarCuadrado(g, 0 + x * anchoCuadrado(),
                       topeTablero + (altoTablero - y - 1) * altoCuadrado(),
                       actualPieza.getForma());
            }
        }
    }

    private void bajarFigura()
    {
        int nuevaY = actualY;
        while (nuevaY > 0) {
            if (!intentarMovimiento(actualPieza, actualX, nuevaY - 1))
                break;
            --nuevaY;
        }
        figuraCaida();
    }

    private void unaLineaAbajo()
    {
        if (!intentarMovimiento(actualPieza, actualX, actualY - 1))
            figuraCaida();
    }


    private void limpiarTablero()
    {
        for (int i = 0; i <altoTablero * anchoTablero; ++i)
           tablero[i] =formasTetris.SinForma;
    }

    private void figuraCaida() {
        for (int i = 0; i < 4; ++i) {
            int x = actualX + actualPieza.x(i);
            int y = actualY - actualPieza.y(i);
            tablero[(y * anchoTablero) + x] = actualPieza.getForma();
        }

        quitarLineas();

        if (!objetoCaido)
            nuevaPieza();
    }

    private void nuevaPieza()
    {
        actualPieza.setPiezaRandom();
        actualX = anchoTablero / 2 + 1;
        actualY = altoTablero - 1 + actualPieza.minY();

        if (!intentarMovimiento(actualPieza,actualX,actualY)) {
            actualPieza.setForma(formasTetris.SinForma);
            temporizador.stop();
            empezado = false;
            barraDeEstado.setText("game over");
        }
    }

    private boolean intentarMovimiento(formas nuevaPieza, int nuevaX, int nuevaY)
    {
        for (int i = 0; i < 4; ++i) {
            int x = nuevaX + nuevaPieza.x(i);
            int y = nuevaY - nuevaPieza.y(i);
            if (x < 0 || x >= anchoTablero || y < 0 || y >= altoTablero)
                return false;
            if (formaEn(x, y) != formasTetris.SinForma)
                return false;
        }

        actualPieza = nuevaPieza;
        actualX = nuevaX;
        actualY = nuevaY;
        repaint();
        return true;
    }

    private void quitarLineas() {
        int numLineasLlenas = 0;

        for (int i = altoTablero - 1; i >= 0; --i) {
            boolean lineaLlena = true;

            for (int j = 0; j < anchoTablero; ++j) {
                if (formaEn(j, i) == formasTetris.SinForma) {
                    lineaLlena = false;
                    break;
                }
            }

            if (lineaLlena) {
                ++numLineasLlenas;
                for (int k = i; k < altoTablero - 1; ++k) {
                    for (int j = 0; j < anchoTablero; ++j)
                        tablero[(k * anchoTablero) + j] = formaEn(j, k + 1);
                }
            }
        }

        if (lineasConseguidas > 0) {
            lineasConseguidas += numLineasLlenas;
           barraDeEstado.setText(String.valueOf(numLineasLlenas));
            objetoCaido = true;
            actualPieza.setForma(formasTetris.SinForma);
            repaint();
        }
    }

    private void dibujarCuadrado(Graphics g, int x, int y,formasTetris forma)
    {
        Color colores[] = { new Color(0, 0, 0), new Color(204, 102, 102),
                new Color(102, 204, 102), new Color(102, 102, 204),
                new Color(204, 204, 102), new Color(204, 102, 204),
                new Color(102, 204, 204), new Color(218, 170, 0)
        };


        Color color = colores[forma.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, anchoCuadrado() - 2, altoCuadrado() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + altoCuadrado() - 1, x, y);
        g.drawLine(x, y, x + anchoCuadrado() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + altoCuadrado() - 1,
                x + anchoCuadrado() - 1, y + altoCuadrado() - 1);
        g.drawLine(x + anchoCuadrado() - 1, y + altoCuadrado() - 1,
                x + anchoCuadrado() - 1, y + 1);
    }


    class TAdapter extends KeyAdapter {
        public void teclaPulsada(KeyEvent e) {

            if (!empezado || actualPieza.getForma() == formasTetris.SinForma) {
                return;
            }

            int codigoTeclas = e.getKeyCode();

            if (codigoTeclas == 'p' || codigoTeclas == 'P') {
                pausar();
                return;
            }

            if (pausado)
                return;

            switch (codigoTeclas) {
                case KeyEvent.VK_LEFT:
                    intentarMovimiento(actualPieza, actualX - 1, actualY);
                    break;
                case KeyEvent.VK_RIGHT:
                    intentarMovimiento(actualPieza, actualX + 1, actualY);
                    break;
                case KeyEvent.VK_DOWN:
                    intentarMovimiento(actualPieza.girarDerecha(), actualX, actualY);
                    break;
                case KeyEvent.VK_UP:
                    intentarMovimiento(actualPieza.girarIzquierda(), actualX, actualY);
                    break;
                case KeyEvent.VK_SPACE:
                    bajarFigura();
                    break;

                case 'd':
                    unaLineaAbajo();
                    break;

                case 'D':
                    unaLineaAbajo();
                    break;
            }

        }
    }
}



