package test;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Spritesheet;
import game.BufferedImageLoader;
import game.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Shot extends GameObject {
    BufferedImageLoader loader = new BufferedImageLoader();
    private Handler handler;
    public Shot(float x, float y, ObjectId id, Handler handler, int velX) {
        super(x, y, id);
        this.velX = velX;
        this.handler = handler;

    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        x+=velX;
        Collision(object);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage((new Spritesheet(loader.loadImage("/star+of+dawn.png")).grabImage(1, 1, 72, 72)),(int)x + 32, (int)y - 16, 32, 32, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }


    public void removeObject() {
        handler.object.remove(this);
    }

    private void Collision(LinkedList<GameObject> object){
        for(int i = 0; i< handler.object.size(); ++i) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ObjectId.Block) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    removeObject();
                }
            }

            /*if (tempObject.getId() == ObjectId.Dragon) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    removeObject();
                }
            }*/
        }
    }
}
