package game;

import Framework.GameObject;
import Framework.GameObjectFactory;
import Framework.ObjectId;
import game.Game;
import objects.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.LinkedList;

public class Handler {
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    private Camera cam;
    private GameObject tempObject;
    private GameObjectFactory gameObjFactory = new GameObjectFactory();

    private BufferedImage level = null, level2 = null, level3 = null;
    BufferedImageLoader loader = new BufferedImageLoader();

    public Handler(Camera cam){
        this.cam = cam;
    }

    public void tick() throws SQLException {
        for(int i = 0; i < object.size(); ++i){
            tempObject = object.get(i);

            tempObject.tick(object);
        }
    }
    public void resetLevel(){
        cam.setX(0);
        Game.stelute = 0;
        switch (Game.nivel) {
            case 1: {LoadImageLevel(level); break;}
            case 2:  {LoadImageLevel(level2); break;}
            case 3: { LoadImageLevel(level3); break;}
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); ++i){
            tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public void LoadImageLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();

        System.out.println("Width, height "+ w + " "+ h);

        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                int pixel = image.getRGB(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 0 && green == 0 && blue == 0)
                    addObject(gameObjFactory.createGameObject(i*32,j*32,null, null, ObjectId.Block, 0, 0));
                if(red == 255 && green == 0 && blue == 0)
                    addObject(gameObjFactory.createGameObject(i*32,j*32,null, null, ObjectId.Block, 1, 0));
                if(red == 255 && green == 255 && blue == 0)
                    addObject(gameObjFactory.createGameObject(i*32,j*32,null, null, ObjectId.Block, 2, 0));
                if(red == 255 && green == 0 && blue == 255)
                    addObject(gameObjFactory.createGameObject(i*32,j*32,null, null, ObjectId.Block, 3, 0));
                if(red == 0 && green == 255 && blue == 0)
                    addObject(gameObjFactory.createGameObject(i*32,j*32,null, null, ObjectId.Block, 4, 0));
                if(red == 0 && green == 255 && blue == 255)
                    addObject(gameObjFactory.createGameObject(i*32,j*32,null, null, ObjectId.Block, 5, 0));
                if(red == 0 && green == 0 && blue == 255)
                    addObject(gameObjFactory.createGameObject(i*32,j*32,this,cam,ObjectId.Player,0,0));
                if(red == 255 && green == 210 && blue == 0)
                    addObject(gameObjFactory.createGameObject(i*32,j*32,this,cam,ObjectId.Stelute,0,0));
                if(red == 167 && green == 137 && blue == 203)
                    addObject(gameObjFactory.createGameObject(i*32,j*32,this,cam,ObjectId.Dragon,0,0));
                if(red == 255 && green == 100 && blue == 0)
                    addObject(gameObjFactory.createGameObject(i*32,j*32,this,cam,ObjectId.Block,6,0));
                if(red == 255 && green == 100 && blue == 50)
                    addObject(gameObjFactory.createGameObject(i*32,j*32,this,cam,ObjectId.Block,7,0));
                if(red == 255 && green == 100 && blue == 100)
                    addObject(gameObjFactory.createGameObject(i*32,j*32,this,cam,ObjectId.Block,8,0));
                if(red == 157 && green == 0 && blue == 157)
                    addObject(gameObjFactory.createGameObject(i*32,j*32,this,cam,ObjectId.Capcana,0,0));
                if(red == 157 && green == 157 && blue == 157)
                    addObject(gameObjFactory.createGameObject(i*32,j*32,this,cam,ObjectId.Castel,0,0));
            }
        }
    }
    public void removeGameObject(GameObject e){
        object.remove(e);
    }

    public void stergeNivel(){
        object.clear();
    }

    public void switchLevel(){
        stergeNivel();
        Game.nivel++;
        System.out.println(Game.nivel);
        cam.setX(0);
        Game.stelute = 0;
        Player.dragon = 1;
        Dragon.shots = 0;
          Dragon.type++;
        switch (Game.nivel){
            case 1:{
                level = loader.loadImage("/ook.png");
                LoadImageLevel(level);
                break;}
            case 2:{
                level2 = loader.loadImage("/nivel2.png");
                LoadImageLevel(level2);
                break;
            }
            case 3:{
                level2 = loader.loadImage("/nivel3.png");
                LoadImageLevel(level2);
            }
        }
    }
    public void addObject(GameObject object){
        this.object.add(object);
    }
}
