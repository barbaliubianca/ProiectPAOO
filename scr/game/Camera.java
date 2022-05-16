package game;

import Framework.GameObject;

public class Camera {
    private float x, y;

    public Camera(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject player){
        x = Game.WIDTH/2 - player.getX();
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
}
