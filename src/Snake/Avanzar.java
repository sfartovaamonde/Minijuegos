package Snake;

import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Avanzar implements Runnable{

    Juego panel;
    boolean estado=true;

    public Avanzar(Juego panel){

        this.panel=panel;

    }



    @Override
    public void run() {

        while(estado) {

            panel.moverse();
            panel.repaint();
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                Logger.getLogger(Avanzar.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    private void teclasPulsadas(java.awt.event.KeyEvent evt){

        switch (evt.getKeyCode()){

            case KeyEvent.VK_LEFT:
                panel.cambiardireccion("iz");
                break;

            case KeyEvent.VK_RIGHT:
                panel.cambiardireccion("de");
                break;

            case KeyEvent.VK_UP:
                panel.cambiardireccion("ar");
                break;

            case KeyEvent.VK_DOWN:
                panel.cambiardireccion("ab");
                break;

            default:
                break;

        }

    }


}
