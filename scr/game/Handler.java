package game;

import Framework.GameObject;
import Framework.ObjectId;
import game.Game;
import test.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Handler {
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    private Camera cam;
    private GameObject tempObject;

    private BufferedImage level = null, level2 = null, level3 = null;
    BufferedImageLoader loader = new BufferedImageLoader();

    public Handler(Camera cam){
        this.cam = cam;
    }

    public void tick(){
        for(int i = 0; i < object.size(); ++i){
            tempObject = object.get(i);

            tempObject.tick(object);
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

                if(red == 0 && green == 0 && blue == 0) addObject(new Block(i*32, j*32, 0, ObjectId.Block));
                if(red == 255 && green == 0 && blue == 0) addObject(new Block(i*32, j*32, 1, ObjectId.Block));
                if(red == 255 && green == 255 && blue == 0) addObject(new Block(i*32, j*32, 2, ObjectId.Block));
                if(red == 255 && green == 0 && blue == 255) addObject(new Block(i*32, j*32, 3, ObjectId.Block));
                if(red == 0 && green == 255 && blue == 0) addObject(new Block(i*32, j*32, 4, ObjectId.Block));
                if(red == 0 && green == 255 && blue == 255) addObject(new Block(i*32, j*32, 5, ObjectId.Block));
                if(red == 0 && green == 0 && blue == 255) addObject(new Player(i*32, j*32, this, cam, ObjectId.Player));
                if(red == 255 && green == 210 && blue == 0) addObject(new Stelute(i*32, j*32, ObjectId.Stelute));
                if(red == 167 && green == 137 && blue == 203) addObject(new Dragon(i*32, j*32, ObjectId.Dragon, this));
                if(red == 255 && green == 100 && blue == 0) addObject(new Block(i*32, j*32, 6, ObjectId.Block));
                if(red == 255 && green == 100 && blue == 50) addObject(new Block(i*32, j*32, 7, ObjectId.Block));
                if(red == 255 && green == 100 && blue == 100) addObject(new Block(i*32, j*32, 8, ObjectId.Block));
                if(red == 157 && green == 0 && blue == 157) addObject(new Capcana(i*32, j*32, ObjectId.Capcana));
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
