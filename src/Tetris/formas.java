package Tetris;

import java.util.Random;

import java.lang.Math;


    public class formas {

        enum formasTetris { SinForma, FormadeZ, FormadeS, FormadeLinea, FormadeT, Cuadrado, FormadeL, LdelReves };

        private formasTetris formaPieza;
        private int posiciones[][];
        private int[][][] tablaPosiciones;


        public formas() {

            posiciones = new int[4][2];
            setForma(formasTetris.SinForma);

        }

        public void setForma(formasTetris forma) {

            tablaPosiciones = new int[][][] {
                    { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
                    { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
                    { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
                    { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
                    { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
                    { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
                    { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
                    { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
            };

            for (int i = 0; i < 4 ; i++) {
                for (int j = 0; j < 2; ++j) {
                    posiciones[i][j] = tablaPosiciones[forma.ordinal()][i][j];
                }
            }
            formaPieza = forma;

        }

        private void setX(int index, int x) { posiciones[index][0] = x; }
        private void setY(int index, int y) { posiciones[index][1] = y; }
        public int x(int index) {return posiciones[index][0];
        }
        public int y(int index) { return posiciones[index][1]; }
        public formasTetris getForma()  { return formaPieza; }

        public void setPiezaRandom() {
            Random r = new Random();
            int x = Math.abs(r.nextInt()) % 7 + 1;
            formasTetris[] valores = formasTetris.values();
            setForma(valores[x]);
        }

        public int minX()
        {
            int min = posiciones[0][0];
            for (int i=0; i < 4; i++) {
                min = Math.min(min, posiciones[i][0]);
            }
            return min;
        }


        public int minY()
        {
            int min = posiciones[0][1];
            for (int i=0; i < 4; i++) {
                min = Math.min(min, posiciones[i][1]);
            }
            return min;
        }

        public formas girarIzquierda(){
            if (formaPieza == formasTetris.Cuadrado)
                return this;

            formas resultado = new formas();
            resultado.formaPieza = formaPieza;

            for (int i = 0; i < 4; i++) {
                resultado.setX(i, y(i));
                resultado.setY(i, -x(i));
            }
            return resultado;
        }

        public formas girarDerecha(){

            if (formaPieza == formasTetris.Cuadrado)
                return this;

            formas resultado = new formas();
            resultado.formaPieza = formaPieza;

            for (int i = 0; i < 4; ++i) {
                resultado.setX(i, -y(i));
                resultado.setY(i, x(i));
            }
            return resultado;
        }
    }


