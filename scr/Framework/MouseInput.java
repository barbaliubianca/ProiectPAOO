package Framework;

import game.Camera;
import game.Game;
import game.Handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //Play Button
        if(Game.State == Game.STATE.MENU || Game.State == Game.STATE.RESTART) {
            if (mx >= Game.WIDTH / 2 - 120 && mx <= Game.WIDTH / 2 + 100) {
                if (my >= 270 && my <= 340) {
                    /*if(Game.State == Game.STATE.RESTART) {
                        Game.nivel = 0;
                        Game.handler.switchLevel();
                    }*/
                    Game.State = Game.STATE.GAME;
                }
            }//Quit Game
            if (mx >= Game.WIDTH / 2 + 40 && mx <= Game.WIDTH / 2 + 240) {
                if (my >= 390 && my <= 460) {
                    System.exit(1);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
