package Framework;
import game.BufferedImageLoader;
import game.Game;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Castig {
    /*public Rectangle playbutton = new Rectangle(Game.WIDTH / 2 - 120, 270, 220, 70);
    public Rectangle help = new Rectangle(Game.WIDTH / 2 - 240 , 390, 220, 70);
    public Rectangle quit = new Rectangle(Game.WIDTH / 2 + 40, 390, 200, 70);*/
    BufferedImageLoader loader = new BufferedImageLoader();
    private BufferedImage background = null;
    public void render(Graphics g) {
        background = loader.loadImage("/castig.png");
        g.drawImage(background, 0, 0, null);

        Graphics2D g2d = (Graphics2D) g;
        /*g2d.draw(playbutton);
        g2d.draw(help);
        g2d.draw(quit);*/
    }
}