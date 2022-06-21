package Framework;

import game.Camera;
import game.Game;
import game.Handler;

import java.awt.*;
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
        if(Game.State == Game.STATE.MENU || Game.State == Game.STATE.RESTART || Game.State == Game.STATE.CASTIG) {
            if (mx >= Game.WIDTH / 2 - 120 && mx <= Game.WIDTH / 2 + 100) {
                if (my >= 270 && my <= 340) {
                    if(Game.State == Game.STATE.RESTART) {
                        Game.State = Game.STATE.GAME;
                        Game.nivel = 0;
                        Game.handler.switchLevel();
                        Game.handler.resetLevel();
                    }else {
                        Game.State = Game.STATE.GAME;
                    }
                }
            }//Quit Game
            if (mx >= Game.WIDTH / 2 + 40 && mx <= Game.WIDTH / 2 + 240) {
                if (my >= 390 && my <= 460) {
                    System.exit(1);
                }
            }
            if(mx >= Game.WIDTH / 2 - 240 && mx <= Game.WIDTH / 2 - 40){
                if (my >= 390 && my <= 460) {
                    Game.State = Game.STATE.HELP;
                }
            }
        }else if(Game.State == Game.STATE.HELP){
            if(mx >= Game.WIDTH / 2 - 150 && mx <= Game.WIDTH / 2 + 170){
                if (my >= 530 && my <= 600) {
                    Game.State = Game.STATE.MENU;
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
