package objects;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import game.Animation;
import game.Game;
import game.Handler;

import java.awt.*;
import java.util.LinkedList;

public class Dragon extends GameObject {

    Texture tex = Game.getInstance();
    private final Animation dragon;
    Handler handler;
    public static int shots = 0;
    public static int type = 0;

    public Dragon(float x, float y, ObjectId id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        if(type == 1) {
            dragon = new Animation(30, tex.dragon2[0], tex.dragon2[1], tex.dragon2[2], tex.dragon2[3], tex.dragon2[4]);
        }else{
            if(type == 2) {
                dragon = new Animation(30, tex.dragon3[0], tex.dragon3[1], tex.dragon3[2], tex.dragon3[3], tex.dragon3[4], tex.dragon3[5]);
            }else{
                dragon = new Animation(30, tex.dragon[0], tex.dragon[1], tex.dragon[2], tex.dragon[3], tex.dragon[4], tex.dragon[5]);
            }
        }
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        Collision(object);
        dragon.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        dragon.drawAnimation(g, (int)x, (int)y-70, 150, 150);
        /*for(int i = 0; i < 255; i++){
            handler.addObject(new Shot(x, y + 25, ObjectId.Shot, handler, -5));
        }*/

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 150, 150);
    }

    public void removeObject() {
        handler.object.remove(this);
    }

    private void Collision(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size(); ++i) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ObjectId.Shot) {
                if(getBounds().intersects(tempObject.getBounds())){
                    shots++;
                    tempObject.removeObject();
                }
                if(shots == 3 && Game.nivel == 1){
                    removeObject();
                    Player.dragon = 0;
                }else if(shots == 5 && Game.nivel == 2){
                    removeObject();
                    Player.dragon = 0;
                }else if(shots == 10 && Game.nivel == 3){
                    removeObject();
                    Player.dragon = 0;
                }
            }
        }
    }
}