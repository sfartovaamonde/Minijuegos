package Snake;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    Color fondo = Color.gray;
    int tamañomax,tamaño,cantidad,recibir;

    public Panel(int tamañomax,int cantidad){

        this.tamañomax = tamañomax;
        this.cantidad=cantidad;
        this.tamaño=tamañomax/cantidad;
        this.recibir=tamañomax%cantidad;
    }

    public void paint(Graphics pinta){

        super.paint(pinta);

        pinta.setColor(fondo);

        for(int i=0;i<cantidad;i++){

            for(int j=0;j<cantidad;j++){

                pinta.fillRect(i*tamaño,j*tamaño,tamaño-1,tamaño-1);
            }
        }

    }

}
