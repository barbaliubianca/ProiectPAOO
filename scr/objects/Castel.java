package objects;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Spritesheet;
import game.BufferedImageLoader;
import game.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Castel extends GameObject {
    BufferedImageLoader loader = new BufferedImageLoader();
    private Handler handler;
    public Castel(float x, float y, ObjectId id) {
        super(x, y, id);

    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        //Collision(object);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage((new Spritesheet(loader.loadImage("/castle_grey.png")).grabImage(1, 1, 204, 182)),(int)x + 32, (int)y - 90, 200, 160, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 150, 100);
    }


    public void removeObject() {
        handler.object.remove(this);
    }

    // private void Collision(LinkedList<GameObject> object){
    //for(int i = 0; i< handler.object.size(); ++i) {
    //  GameObject tempObject = handler.object.get(i);

            /*if (tempObject.getId() == ObjectId.Block) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    removeObject();
                }
            }*/
    //if(tempObject.getId() == ObjectId.Player){
    //
    //}
    //}
    //}
}
