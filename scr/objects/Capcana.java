package objects;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import game.Animation;
import game.Game;
import game.Handler;

import java.awt.*;
import java.util.LinkedList;

public class Capcana extends GameObject {
    Texture tex = Game.getInstance();
    Handler handler;
    private Animation star;
    public Capcana(float x, float y, ObjectId id){
        super(x, y, id);
        star = new Animation(10, tex.capcana[0], tex.capcana[1], tex.capcana[2], tex.capcana[3], tex.capcana[4], tex.capcana[5], tex.capcana[6], tex.capcana[7], tex.capcana[8]);
    }
    public void tick(LinkedList<GameObject> object) {
        star.runAnimation();
    }

    public void render(Graphics g) {
        star.drawAnimation(g, (int)x-10, (int)y-160, 96, 192);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 64);
    }

    @Override
    public void removeObject() {
        handler.object.remove(this);
    }
}
