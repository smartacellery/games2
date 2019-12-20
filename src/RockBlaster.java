
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class RockBlaster extends BasicGame {

    private Image rock;
    private Image dmd;
    private Rectangle dbox;
    private ArrayList<Rectangle> rocks;
    private int t1;
    private String score, gameover;

    public RockBlaster(String title) {
        super(title);
    }

    public void init(GameContainer gc) throws SlickException {
        rock = new Image("Images/astroid.png");
        dmd = new Image("Images/diamond.png");
        dbox = new Rectangle(0, 0, dmd.getWidth(), dmd.getHeight());
        rocks = new ArrayList();
        for (int i = 0; i < 20; i++) {
            Rectangle r = new Rectangle(0, 0, rock.getWidth(), rock.getHeight());
            r.setX((int) (Math.random() * 750));
            r.setY((int) (Math.random() * 550));
            rocks.add(r);
        }
        t1 = 0;
        score = "Rocks to Go: " + rocks.size();
        gameover = "Game over!!!!";
    }

    public void update(GameContainer gc, int i) throws SlickException {
        t1++; //framerate=100 =100 times per second 
        if (t1 == 50) {
            t1 = 0;
            if (rocks.size() > 0) {
                Rectangle r = new Rectangle(0, 0, rock.getWidth(), rock.getHeight());
                r.setX((int) (Math.random() * 750));
                r.setY((int) (Math.random() * 550));
                rocks.add(r);
            }
            Input in = gc.getInput();
            //follow mouse with diamond
            int mx = in.getMouseX();
            int my = in.getMouseY();
            dbox.setX(mx - dbox.getWidth() / 2);
            dbox.setY(my - dbox.getHeight() / 2);

            //see if diamond hits any rocks
            for (int j = 0; j < rocks.size(); j++) {
                Rectangle r = rocks.get(j);
                if (r.intersects(dbox) && in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    rocks.remove(j);
                }
            }
            score = "Rocks to Go: " + rocks.size();

        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {

        for (Rectangle r : rocks) {
            rock.draw(r.getX(), r.getY());
        }
        dmd.draw(dbox.getX(), dbox.getY());

    }

    public static void main(String args[]) throws SlickException {
        RockBlaster game = new RockBlaster("Rock Blaster");
        AppGameContainer app = new AppGameContainer(game);
        app.setMouseGrabbed(true); //hides the mouse
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(100);
        app.start();
    }

}
