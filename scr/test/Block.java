package test;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import game.Game;
import game.Handler;

import java.awt.*;
import java.util.LinkedList;

public class Block extends GameObject {
    Texture tex = Game.getInstance();
    Handler handler;
    private int type;
    public Block(float x, float y, int type, ObjectId id){
        super(x, y, id);
        this.type = type;
    }

    public void tick(LinkedList<GameObject> object){

    }

    public void render(Graphics g){
        if(type == 0){
            g.drawImage(tex.block[0], (int)x, (int)y, null);
        }
        if(type == 1){
            g.drawImage(tex.block[1], (int)x, (int)y, null);
        }
        if(type == 2){
            g.drawImage(tex.block[2], (int)x, (int)y, null);
        }
        if(type == 3){
            g.drawImage(tex.block[3], (int)x, (int)y, null);
        }
        if(type == 4){
            g.drawImage(tex.block[4], (int)x, (int)y, null);
        }
        if(type == 5){
            g.drawImage(tex.block[5], (int)x, (int)y, null);
        }
        if(type == 6){
            g.drawImage(tex.cloud[0], (int)x, (int)y, null);
        }
        if(type == 7){
            g.drawImage(tex.cloud[1], (int)x, (int)y, null);
        }
        if(type == 8){
            g.drawImage(tex.cloud[2], (int)x, (int)y, null);
        }
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    @Override
    public void removeObject() {
        handler.object.remove(this);
    }
}
