package objects;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import game.*;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class Player extends GameObject {
    public float width = 32, height = 64;
    private float gravity = 0.5f;
    private final float Max_SPEED = 10;
    public static int dragon = 1;
    //public static Statement stmt = null;
    public static Connection c;

    static {
        try {
            c = DriverManager.getConnection("jdbc:sqlite:JOC.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Handler handler;
    private Camera cam;

    Texture tex = Game.getInstance();
    BazaDate baza2 = BazaDate.create(c);

    private Animation playerWalk;
    private Animation playerWalkBack;

    public Player(float x, float y, Handler handler, Camera cam, ObjectId id){
        super(x, y, id);
        this.cam = cam;
        this.handler = handler;
        //BazaDate baza = BazaDate.create();

        playerWalk = new Animation(10, tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5], tex.player[6]);
        playerWalkBack = new Animation(10, tex.player[7], tex.player[8], tex.player[9], tex.player[10], tex.player[11], tex.player[12]);
    }
    public void tick(LinkedList<GameObject> object) throws SQLException {
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

    private void Collision(LinkedList<GameObject> object) throws SQLException {
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
                    String sql = "";

                    if(Game.nivel == 1) {
                        //BazaDate baza = Game.baza;
                        baza2.addRecord(c, Game.stelute, Game.nivel, 5);
                        // sql = "INSERT INTO JOC (NrCoin, Nivel, NrMaximCoin) " + "VALUES (" + Game.stelute + ", " + Game.nivel + ", 5);";
                    }else if(Game.nivel == 2){
                        baza2.addRecord(c, Game.stelute, Game.nivel, 10);
                        //sql = "INSERT INTO JOC (NrCoin, Nivel, NrMaximCoin) " + "VALUES (" + Game.stelute + ", " + Game.nivel + ", 10);";
                    }else if(Game.nivel == 3){
                        baza2.addRecord(c, Game.stelute, Game.nivel, 15);
                        //sql = "INSERT INTO JOC (NrCoin, Nivel, NrMaximCoin) " + "VALUES (" + Game.stelute + ", " + Game.nivel + ", 15);";

                    }
                    System.out.println(sql);
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
                    }else{
                        if(tempObject.getId() == ObjectId.Castel){
                            if(getBounds().intersects(tempObject.getBounds()) && Game.stelute >= 5){
                                if(Game.stelute == 15 && dragon == 0) {
                                    baza2.afisare();
                                    Game.State = Game.STATE.CASTIG;
                                }
                            }
                        }
                    }
                }
            }
            if(Game.viata == 0) {
                deadPlayer();
                removeObject();
                Game.State = Game.STATE.RESTART;
                baza2.afisare();
                baza2.close();
                baza2.finalize();
                //Game.afisare(Game.stmt);
                //Game.stmt.close();
                //Game.c.commit();
                //Game.c.close();
            }

        }
    }
    public void deadPlayer(){
        Game.viata = 200;
        handler.resetLevel();
        Game.stelute = 0;
        cam.setX(0);
    }
    public void render(Graphics g) {
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
        if(dragon == 0 && Game.stelute >= 5 && Game.nivel == 1){
            handler.switchLevel();
        }
        if(dragon == 0 && Game.stelute == 10 && Game.nivel == 2){
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
