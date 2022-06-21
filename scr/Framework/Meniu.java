package Framework;

import game.BufferedImageLoader;
import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Meniu {

    BufferedImageLoader loader = new BufferedImageLoader();
    private BufferedImage background = null;

    public void render(Graphics g){
        background = loader.loadImage("/meniuok2.png");
        g.drawImage(background, 0, 0, null);

    }
}
