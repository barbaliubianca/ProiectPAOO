package Framework;

import objects.*;
import game.Handler;
import game.Camera;

public class GameObjectFactory {

    public GameObject createGameObject(float x, float y, Handler handler, Camera cam, ObjectId id, int type, int velX) {
        GameObject newGameObject = null;

        switch (id) {
            case Player: {
                return new Player(x, y, handler, cam, id);
            }
            case Block: {
                return new Block(x, y, type, id);
            }
            case Stelute: {
                return new Stelute(x, y, id);
            }
            case Shot: {
                return new Shot(x, y, id, handler, velX);
            }
            case Dragon: {
                return new Dragon(x, y, id, handler);
            }
            case Capcana: {
                return new Capcana(x, y, id);
            }
            case Castel: {
                return new Castel(x, y, id);
            }
        }
        return null;
    }
}
