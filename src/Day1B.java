
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class Day1B extends BasicGame {

    private Image diamond;
    private Rectangle dbox, box;
    private Color boxColor;
    int dx, dy;

    public Day1B(String title) {
        super(title);
    }

    public void init(GameContainer gc) throws SlickException {
        diamond = new Image("Images/diamond.PNG");
        dx = 200;
        dy = 200;
        dbox = new Rectangle(dx, dy, diamond.getWidth(), diamond.getHeight());
        box = new Rectangle(100, 100, 200, 50);
        boxColor=Color.yellow;
    }
    
    public void changeColor(){
        boxColor= new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
    }

    public void update(GameContainer gc, int i) throws SlickException {
        Input in = gc.getInput();

        int mx = in.getMouseX();
        int my = in.getMouseY();

        if (box.contains(mx, my) && in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
           changeColor();
        }

        if (in.isKeyDown(Input.KEY_LEFT)) {
            dx--;
        }
        if (in.isKeyDown(Input.KEY_RIGHT)) {
            dx++;
        }
        if (in.isKeyDown(Input.KEY_UP)) {
            dy--;
        }
        if (in.isKeyDown(Input.KEY_DOWN)) {
            dy++;
        }

        if (in.isKeyDown(Input.KEY_SPACE)) { //one time event
            dx = (int) (Math.random() * 750);
            dy = (int) (Math.random() * 550);
        }
        if(dbox.contains(mx,my) && in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            dx=(int)(Math.random()*750);
            dy=(int)(Math.random()*550);
        }

        dbox.setX(dx);
        dbox.setY(dy);

    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.setColor(boxColor);
        g.fill(box);
        diamond.draw(dbox.getX(), dbox.getY());
    }

    public static void main(String args[]) throws SlickException {
        Day1B game = new Day1B("Testing Game");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(100);
        app.start();
    }

}
