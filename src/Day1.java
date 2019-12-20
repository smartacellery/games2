
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class Day1 extends BasicGame {

    private Rectangle redbox;
    private Circle dot;
    private int doty, dotdir;
    private String status;
    private Image andy;
    private Rectangle andybox; //Hitbox for image
    private boolean hitdot = false;

    public Day1(String title) {
        super(title);
    }

    public void init(GameContainer gc) throws SlickException {
        redbox = new Rectangle(100, 100, 200, 50);
        doty = 580;
        dotdir = -5;
        dot = new Circle(200, doty, 10);
        status = "Not Touching";
        andy = new Image("Images/android.PNG");
        andybox = new Rectangle(500, 200, andy.getWidth(), andy.getHeight());
    }

    public void update(GameContainer gc, int i) throws SlickException {
        if (hitdot == false) {
            andybox.setX(andybox.getX() - 1);
        }
        doty -= dotdir;
        dot.setY(doty);
        //bounce dot up and down
        if (doty <= 5 || doty >= 590) {
            dotdir = -dotdir;
        }

        if (dot.intersects(redbox) || redbox.contains(dot)) {
            status = "Touching";
        } else {
            status = "Not touching";
        }

        if (dot.intersects(andybox)) {
            hitdot = true;
        }

    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.setColor(Color.red);
        g.fill(redbox);
        g.setColor(Color.yellow);
        g.fill(dot);
        g.drawString(status, 10, 10);
        andy.draw(andybox.getX(), andybox.getY());
    }

    public static void main(String args[]) throws SlickException {
        Day1 game = new Day1("Testing Game");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(100);
        app.start();
    }

}
