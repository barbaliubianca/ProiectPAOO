package Framework;

import game.BufferedImageLoader;

import java.awt.image.BufferedImage;


public class Texture {
    Spritesheet bs, ps, ss, ds, ns, ds2, ds3, cs;
    private BufferedImage block_sheet = null;
    private BufferedImage player_sheet = null;
    private BufferedImage star_sheet = null;
    private BufferedImage dragon_sheet = null;
    private BufferedImage dragon_sheet2 = null;
    private BufferedImage dragon_sheet3 = null;
    private BufferedImage cloud_sheet = null;
    private BufferedImage capcana_sheet = null;

    public BufferedImage[] block = new BufferedImage[6];
    public BufferedImage[] player = new BufferedImage[14];
    public BufferedImage[] star = new BufferedImage[6];
    public BufferedImage[] dragon = new BufferedImage[9];
    public BufferedImage[] dragon2 = new BufferedImage[9];
    public BufferedImage[] dragon3 = new BufferedImage[9];
    public BufferedImage[] cloud = new BufferedImage[3];
    public BufferedImage[] capcana = new BufferedImage[9];
    public Texture(){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            block_sheet = loader.loadImage("/tilebunn.png");
            player_sheet = loader.loadImage("/pri.png");
            star_sheet = loader.loadImage("/stea.png");
            dragon_sheet = loader.loadImage("/dragon1.png");
            dragon_sheet2 = loader.loadImage("/sheepy .png");
            dragon_sheet3 = loader.loadImage("/water_dragon.png");
            cloud_sheet = loader.loadImage("/cloud-2.png");
            capcana_sheet = loader.loadImage("/trap.png");
        }catch(Exception e){
            e.printStackTrace();
        }
        bs = new Spritesheet(block_sheet);
        ps = new Spritesheet(player_sheet);
        ss = new Spritesheet(star_sheet);
        ds = new Spritesheet(dragon_sheet);
        ds2 = new Spritesheet(dragon_sheet2);
        ds3 = new Spritesheet(dragon_sheet3);
        ns = new Spritesheet(cloud_sheet);
        cs = new Spritesheet(capcana_sheet);
        getTextures();
    }
    private void getTextures(){
        BufferedImageLoader loader = new BufferedImageLoader();
        block[0] = bs.grabImage(1, 2, 32, 32);
        block[1] = bs.grabImage(1, 3, 32, 32);
        block[2] = bs.grabImage(3, 1, 32, 32);
        block[3] = bs.grabImage(4, 2, 32, 32);
        block[5] = bs.grabImage(5, 2, 32, 32);
        block[4] = bs.grabImage(1, 5, 32, 32);

        cloud[0] = ns.grabImage(1, 1, 32, 32);
        cloud[1] = ns.grabImage(2, 1, 32, 32);
        cloud[2] = ns.grabImage(3, 1, 32, 32);

        player[0] = ps.grabImage(1, 2, 32, 64);
        player[1] = ps.grabImage(1, 3, 32, 64);
        player[2] = ps.grabImage(2, 3, 32, 64);
        player[3] = ps.grabImage(3, 3, 32, 64);
        player[4] = ps.grabImage(4, 3, 32, 64);
        player[5] = ps.grabImage(5, 3, 32, 64);
        player[6] = ps.grabImage(6, 3, 32, 64);
        player[7] = ps.grabImage(1, 4, 32, 64);
        player[8] = ps.grabImage(2, 4, 32, 64);
        player[9] = ps.grabImage(3, 4, 32, 64);
        player[10] = ps.grabImage(4, 4, 32, 64);
        player[11] = ps.grabImage(5, 4, 32, 64);
        player[12] = ps.grabImage(6, 4, 32, 64);
        player[13] = ps.grabImage(2, 2, 32, 64);

        star[0] = ss.grabImage(1, 1, 32, 32);
        star[1] = ss.grabImage(2, 1, 32, 32);
        star[2] = ss.grabImage(3, 1, 32, 32);
        star[3] = ss.grabImage(4, 1, 32, 32);
        star[4] = ss.grabImage(5, 1, 32, 32);
        star[5] = ss.grabImage(6, 1, 32, 32);

        dragon[0] = ds.grabImage(1, 1, 32, 32);
        dragon[1] = ds.grabImage(2, 1, 32, 32);
        dragon[2] = ds.grabImage(3, 1, 32, 32);
        dragon[3] = ds.grabImage(4, 1, 32, 32);
        dragon[4] = ds.grabImage(4, 2, 32, 32);
        dragon[5] = ds.grabImage(1, 3, 32, 32);
        dragon[6] = ds.grabImage(1, 2, 32, 32);
        dragon[7] = ds.grabImage(2, 2, 32, 32);
        dragon[8] = ds.grabImage(3, 2, 32, 32);

        dragon2[0] = ds2.grabImage(1, 1, 32, 32);
        dragon2[1] = ds2.grabImage(2, 1, 32, 32);
        dragon2[2] = ds2.grabImage(3, 1, 32, 32);
        dragon2[3] = ds2.grabImage(2, 2, 32, 32);
        dragon2[4] = ds2.grabImage(3, 2, 32, 32);

        dragon3[0] = ds3.grabImage(1, 1, 32, 32);
        dragon3[1] = ds3.grabImage(2, 1, 32, 32);
        dragon3[2] = ds3.grabImage(3, 1, 32, 32);
        dragon3[3] = ds3.grabImage(4, 1, 32, 32);
        dragon3[4] = ds3.grabImage(3, 2, 32, 32);
        dragon3[5] = ds3.grabImage(4, 2, 32, 32);

        capcana[0] = cs.grabImage(1, 1, 32, 64);
        capcana[1] = cs.grabImage(2, 1, 32, 64);
        capcana[2] = cs.grabImage(3, 1, 32, 64);
        capcana[3] = cs.grabImage(4, 1, 32, 64);
        capcana[4] = cs.grabImage(5, 1, 32, 64);
        capcana[5] = cs.grabImage(6, 1, 32, 64);
        capcana[6] = cs.grabImage(7, 1, 32, 64);
        capcana[7] = cs.grabImage(8, 1, 32, 64);
        capcana[8] = cs.grabImage(9, 1, 32, 64);
    }
}
