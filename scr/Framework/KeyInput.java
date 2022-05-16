package Framework;
import game.Game;
import game.Handler;
import test.Shot;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
    }
    public void keyPressed(KeyEvent k){
        int key = k.getKeyCode();

        for(int i = 0; i< handler.object.size(); ++i){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectId.Player){
                if(key == KeyEvent.VK_RIGHT) {tempObject.setVelX(5);}
                if(key == KeyEvent.VK_LEFT){ tempObject.setVelX(-5);}
                if(key == KeyEvent.VK_UP && !tempObject.jumping){
                    tempObject.setJumping(true);
                    tempObject.setVelY(-10);
                }
                if(key == KeyEvent.VK_SPACE){
                    handler.addObject(new Shot(tempObject.getX(), tempObject.getY() + 25, ObjectId.Shot, handler,tempObject.facing * 5));

                }
                if(key == KeyEvent.VK_ENTER){
                    Game.nivel = 0;
                }
            }
        }
        if(key == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent k) {
        int key = k.getKeyCode();

        for(int i = 0; i< handler.object.size(); ++i){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectId.Player){
                if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
                if(key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
            }
        }
    }
}
