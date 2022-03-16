package Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Juego extends JPanel {

    Color colsnake = Color.green;

    Color colcomida = Color.red;

    int tamañomax, tamaño, cantidad, recibir;

    List<int[]> snake = new ArrayList<>();
    int[] comida = new int[2];
    String direccion = "de";

    String siguientedir="de";

    Thread hilo;
    Avanzar camino;


    public Juego(int tamañomax, int cantidad) {

        this.tamañomax = tamañomax;
        this.cantidad = cantidad;
        this.tamaño = tamañomax / cantidad;
        this.recibir = tamañomax % cantidad;
        int[] a = {cantidad / 2 - 1, cantidad / 2 - 1};
        int[] b = {cantidad / 2, cantidad / 2 - 1};
        snake.add(a);
        snake.add(b);
        crearComida();

        camino = new Avanzar(this);

        hilo= new Thread(camino);

        hilo.start();

    }

    public void paint(Graphics pinta) {

        super.paint(pinta);

        pinta.setColor(colsnake);

       /* for(int i=0;i<snake.size();i++){

                pinta.fillRect(recibir/2+i*tamaño,recibir/2+j*tamaño,tamaño-1,tamaño-1);
            }*/
        for (int[] valor : snake) {

            pinta.fillRect(recibir / 2 + valor[0] * tamaño, recibir / 2 + valor[1] * tamaño, tamaño - 1, tamaño - 1);
        }

        //pintamos la comida

        pinta.fillRect(recibir / 2 + comida[0] * tamaño, recibir / 2 + comida[1] * tamaño, tamaño - 1, tamaño - 1);

    }

    public void moverse() {

        igualardireccion();

        int[] actual = snake.get(snake.size() - 1);
        int sumx = 0;
        int sumy = 0;
        switch (direccion) {

            case "de":
                sumx = 1;
                break;

            case "iz":
                sumx = -1;
                break;

            case "ar":
                sumy = -1;
                break;

            case "ab":
                sumy = 1;
                break;

        }

        int[] nuevo = {Math.floorMod(actual[0] + sumx, cantidad),
                Math.floorMod(actual[1] + sumy, cantidad)};

        boolean existe = false;

        for (int i = 0; i < snake.size(); i++) {

            if (actual[0] == snake.get(i)[0] && actual[1] == snake.get(i)[1]) {

                existe = true;
                break;

            }

        }

        if (existe) {

            JOptionPane.showMessageDialog(this, "has perdido");
        } else if (actual[0] == comida[0] && actual[1] == comida[1]){
            snake.add(actual);

    }else{
            snake.add(actual);
            snake.remove(0);
        }

        }

        public void crearComida() {

            boolean existe = false;

            int a = (int) (Math.random() * cantidad);
            int b = (int) (Math.random() * cantidad);

            for (int[] valor : snake) {

                if (valor[0] == a && valor[1] == b) {

                    existe = true;
                    crearComida();
                    break;

                }

                if (!existe) {

                    this.comida[0] = a;
                    this.comida[1] = b;

                }

            }
        }

            public void cambiardireccion(String dir) {

                if ((this.direccion.equals("de") || this.direccion.equals("iz")) && (dir.equals("ar") || dir.equals("ab"))) {

                    this.siguientedir = dir;
                }

                if ((this.direccion.equals("ar") || this.direccion.equals("ab")) && (dir.equals("iz") || dir.equals("de"))) {

                    this.siguientedir = dir;
                }

            }

            public void igualardireccion(){

                this.direccion=this.siguientedir;

            }









}
