package game;

import Framework.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import java.sql.SQLException;
import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Float.parseFloat;

public class Game extends Canvas implements Runnable{
    private static Game instance = null;
    private boolean running = false;
    private Thread thread;
    public static Statement stmt = null;
    public static Connection c = null;

    public static int stelute;
    public static int nivel;
    public static int viata;

    public static int WIDTH, HEIGHT;
    BufferedImageLoader loader = new BufferedImageLoader();
    private BufferedImage background = null;
    public static Handler handler;
    Camera cam;

    static Texture tex;
    private Meniu meniu;
    private Restart restart;
    private Castig castig;
    private Help help;


    private Game() {
        stelute = 0;
        nivel = 0;
        viata = 200;
    }

    public static enum STATE{
        MENU,
        GAME,
        RESTART,
        CASTIG,
        HELP
    };

    public static STATE State = STATE.MENU;

    public static Game instance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }
    private void init(){
        WIDTH = getWidth();
        HEIGHT = getHeight();

        tex = new Texture();

        background = loader.loadImage("/forest.png");
        cam = new Camera(0, 0);
        handler = new Handler(cam);
        meniu = new Meniu();
        restart = new Restart();
        castig = new Castig();
        help = new Help();

        handler.switchLevel();

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput());

    }
    public synchronized void start(){
        if(running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public void run(){
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                try {
                    tick();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void tick() throws SQLException {
        if(State == STATE.GAME) {
            handler.tick();
        }

        for(int i = 0; i< handler.object.size(); ++i) {
            if(handler.object.get(i).getId() == ObjectId.Player){
                cam.tick(handler.object.get(i));
            }
        }

    }
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D)g;
        //g.drawImage((new Spritesheet(loader.loadImage("/block_sheet.png")).grabImage(1, 2, 32, 32)),50, 20, 100, 100, null);
        if(State == STATE.GAME) {
            g2d.translate(cam.getX(), cam.getY());

            for (int i = 0; i < background.getWidth() * 5; i += background.getWidth()) {
                g.drawImage(background, i, 0, this);
            }

            handler.render(g);

            g2d.translate(-cam.getX(), -cam.getY());//sfarsitul camerei

            //afisare healthbar
            if(viata == 200) {
                g.drawImage((new Spritesheet(loader.loadImage("/helth bar.png")).grabImage(1, 1, 32, 32)), WIDTH - 305, -45, 350, 250, null);
            }else if(viata == 150){
                g.drawImage((new Spritesheet(loader.loadImage("/helth bar.png")).grabImage(2, 1, 32, 32)), WIDTH - 305, -45, 350, 250, null);
            }else if(viata == 100){
                g.drawImage((new Spritesheet(loader.loadImage("/helth bar.png")).grabImage(3, 1, 32, 32)), WIDTH - 305, -45, 350, 250, null);
            }else if(viata == 50){
                g.drawImage((new Spritesheet(loader.loadImage("/helth bar.png")).grabImage(1, 2, 32, 32)), WIDTH - 305, -45, 350, 250, null);
            }else if(viata == 0){
                g.drawImage((new Spritesheet(loader.loadImage("/helth bar.png")).grabImage(2, 1, 32, 32)), WIDTH - 305, -45, 350, 250, null);
            }

            g.drawImage((new Spritesheet(loader.loadImage("/stea.png")).grabImage(1, 1, 32, 32)), 0, 0, 55, 55, null);
            g.setColor(Color.white);
            g.setFont(new Font("Courier", Font.BOLD, 32));
            if(nivel == 1) {
                g.drawString("x " + stelute + " / 5", 55, 43);
            }else if(nivel == 2){
                g.drawString("x " + stelute + " / 10", 55, 43);
            }else if(nivel == 3){
                g.drawString("x " + stelute + " / 15", 55, 43);
            }
        }else if(State == STATE.MENU){
            meniu.render(g);
        }
        if(State == STATE.RESTART){
            restart.render(g);
        }else if(State == STATE.CASTIG){
                castig.render(g);
        }else if(State == STATE.HELP){
            help.render(g);
        }
        g.dispose();
        bs.show();
    }

    public static Texture getInstance(){
        return tex;
    }

    public static void main(String args[]){
        Game joc = Game.instance();
        new Window(800, 600, "Save the fairies", new Game());
    }
}
