package Framework;
import game.BufferedImageLoader;
import game.Game;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Help {
    //public Rectangle playbutton = new Rectangle(Game.WIDTH / 2 - 150, 530, 320, 70);

    BufferedImageLoader loader = new BufferedImageLoader();
    private BufferedImage background = null;
    public void render(Graphics g) {
        background = loader.loadImage("/help.png");
        g.drawImage(background, 0, 0, null);

        Graphics2D g2d = (Graphics2D) g;
        //g2d.draw(playbutton);
    }
}
