package objects;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import game.Animation;
import game.Game;
import game.Handler;

import java.awt.*;
import java.util.LinkedList;

public class Stelute extends GameObject {
    Texture tex = Game.getInstance();
    Handler handler;
    private Animation star;
    public Stelute(float x, float y, ObjectId id){
        super(x, y, id);
        star = new Animation(10, tex.star[0], tex.star[1], tex.star[2], tex.star[3], tex.star[4], tex.star[5]);
    }
    public void tick(LinkedList<GameObject> object) {
        star.runAnimation();
    }

    public void render(Graphics g) {
        star.drawAnimation(g, (int)x-10, (int)y-10, 48, 48);
        //g.setColor(Color.yellow);
        //g.fillRect((int)x, (int)y, 32, 32);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    @Override
    public void removeObject() {
        handler.object.remove(this);
    }
}
