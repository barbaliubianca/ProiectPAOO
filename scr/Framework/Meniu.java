package Framework;

import game.BufferedImageLoader;
import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Meniu {

    public Rectangle playbutton = new Rectangle(Game.WIDTH / 2 - 120, 270, 220, 70);
    public Rectangle help = new Rectangle(Game.WIDTH / 2 - 240 , 390, 220, 70);
    public Rectangle quit = new Rectangle(Game.WIDTH / 2 + 40, 390, 200, 70);

    BufferedImageLoader loader = new BufferedImageLoader();
    private BufferedImage background = null;

    public void render(Graphics g){
        //g.setColor(Color.BLUE);
        //g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        background = loader.loadImage("/meniuok2.png");
        g.drawImage(background, 0, 0, null);
        //g.drawImage((new Spritesheet(loader.loadImage("/meniuok.png")).grabImage(1, 1, 500, 300)), 170,  80, 420, 400, null);

        /*Graphics2D g2d = (Graphics2D) g;
        Font fnt = new Font("Brush Script MT", Font.BOLD, 50);
        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("Save the fairies", Game.WIDTH / 2 - 170, 100);

        Font f = new Font("Brush Script MT", Font.BOLD, 30);
        g.setFont(f);
        g.drawString("Play", playbutton.x + 19, playbutton.y + 50);*/
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(playbutton);
        //g.drawString("Help", help.x + 19, help.y + 30);
        g2d.draw(help);
        //g.drawString("Quit", quit.x + 19, quit.y + 30);
        g2d.draw(quit);
    }
}
