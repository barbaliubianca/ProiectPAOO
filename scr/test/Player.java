package test;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import game.Animation;
import game.Game;
import game.Handler;
import game.Camera;
import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {
    public float width = 32, height = 64;
    private float gravity = 0.5f;
    private final float Max_SPEED = 10;
    public static int dragon = 1;

    private static Handler handler;
    private Camera cam;

    Texture tex = Game.getInstance();

    private Animation playerWalk;
    private Animation playerWalkBack;

    public Player(float x, float y, Handler handler, Camera cam, ObjectId id){
        super(x, y, id);
        this.cam = cam;
        this.handler = handler;

        playerWalk = new Animation(10, tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5], tex.player[6]);
        playerWalkBack = new Animation(10, tex.player[7], tex.player[8], tex.player[9], tex.player[10], tex.player[11], tex.player[12]);
    }
    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;

        if(falling || jumping){
            velY += gravity;

            if(velY > Max_SPEED){
                velY = Max_SPEED;
            }
        }
        Collision(object);

        playerWalk.runAnimation();
        playerWalkBack.runAnimation();
    }

    private void Collision(LinkedList<GameObject> object){
        for(int i = 0; i< handler.object.size(); ++i){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ObjectId.Block){
                if(getBounds().intersects(tempObject.getBounds())){
                    y = tempObject.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                }else{
                    falling = true;
                }
                if(getBoundsUp().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + height/2;
                    velY = 0;
                }
                if(getBoundsDreapta().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - 35;
                }
                if(getBoundsStanga().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + 35;
                }
            }else if(tempObject.getId() == ObjectId.Stelute){
                if(getBounds().intersects(tempObject.getBounds())) {
                    handler.object.remove(tempObject);
                    Game.stelute++;
                }
            }else {
                if (tempObject.getId() == ObjectId.Dragon) {
                    if (getBoundsDreapta().intersects(tempObject.getBounds())) {
                        Game.viata = Game.viata - 50;
                        x -= 100;
                    }
                    if (getBoundsStanga().intersects(tempObject.getBounds())) {
                        Game.viata = Game.viata - 50;
                        x += 100;
                    }
                }else{
                    if(tempObject.getId() == ObjectId.Capcana){
                        if(getBounds().intersects(tempObject.getBounds())){
                            Game.viata = 0;
                        }
                    }
                }
            }
            if(Game.viata == 0) {
                removeObject();
                Game.State = Game.STATE.RESTART;
            }

        }
    }
    public void render(Graphics g) {
        //g.setColor(Color.blue);
        if(velX>0){
            playerWalk.drawAnimation(g, (int)x, (int)y-20, 48, 96);
            facing = 1;
        }else {
            if(velX<0) {
                playerWalkBack.drawAnimation(g, (int) x, (int) y - 20, 48, 96);
                facing = -1;
            }else if(facing == 1) {
                g.drawImage(tex.player[0], (int) x, (int) y - 20, 48, 96, null);
            }else{
                g.drawImage(tex.player[13], (int) x, (int) y - 20, 48, 96, null);
            }
        }
        if(dragon == 0 && Game.stelute >= 2 && Game.nivel == 1){
            handler.switchLevel();
        }
        if(dragon == 0 && Game.stelute >= 2 && Game.nivel == 2){
            handler.switchLevel();
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int)(x+(width/4)), (int)(y+(height/2)), (int)width/2, (int)height/2);
    }

    @Override
    public void removeObject() {
        handler.object.remove(this);
    }

    public Rectangle getBoundsUp() {
        return new Rectangle((int)(x+(width/4)), (int)y, (int)width/2, (int)height/2);
    }
    public Rectangle getBoundsDreapta() {
        return new Rectangle((int)(x+width-5), (int)(y+5), (int)5, (int)height-10);
    }
    public Rectangle getBoundsStanga() {
        return new Rectangle((int)x, (int)(y+5), (int)5, (int)height-10);
    }

}
