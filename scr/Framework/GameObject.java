package Framework;

import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {
    protected float x, y;
    protected ObjectId id;
    protected float velX = 0, velY = 0;
    protected boolean falling = true;
    protected boolean jumping = false;
    protected int facing = 1;

    public GameObject(float x, float y, ObjectId id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void removeObject() {
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }

    public float getVelX(){
        return velX;
    }
    public float getVelY(){
        return velY;
    }
    public void setVelX(float VelX){
        this.velX = VelX;
    }
    public void setVelY(float VelY){
        this.velY = VelY;
    }

    public boolean getFalling(){
        return falling;
    }
    public boolean getJumping(){
        return jumping;
    }
    public void setFalling(boolean falling){
        this.falling = falling;
    }
    public void setJumping(boolean jumping){
        this.jumping = jumping;
    }

    public ObjectId getId(){
        return id;
    }
}
