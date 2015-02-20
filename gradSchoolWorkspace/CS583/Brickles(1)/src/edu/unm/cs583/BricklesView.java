package edu.unm.cs583;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *This class implements the View half of the Model/View architecture. It is an Applet to be able to run on remote browsers
 * It is a MouseMotionListener to "track" the mouse to move the paddle
 * It is a MouseListener to detect button presses to pause the game
 */
public class BricklesView extends JApplet implements MouseMotionListener, MouseListener {
    static final int fieldX = 0;
    static final int fieldY = 0;
    static final int fieldWidth = 440;
    static final int fieldHeight = 320;
    static final int _mouseSensitivity = 3;
    int flipflop;
    protected int xPaddle;
    protected int yPaddle;
    protected Puck thePuck;
    protected Paddle thePaddle;
    protected Brick theBrick;
    protected BrickPile theBrickPile;
    protected Dimension dim;
    protected Image offScreenImage;
    protected Graphics offScreenGraphics;
    protected BricklesGame _game;
    protected BricklesGame game;

    /** This is the default constructor that only adds the listeners */
    public BricklesView() {
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    /** This routine initializes the drawing field and the off screen image used to improve the animation quality */
    @Override
	public void init() {
        flipflop = 0;
        setBackground(Color.white);
        resize(fieldWidth, fieldHeight);
        dim = this.getSize();
        xPaddle = (fieldWidth / 2);
        yPaddle = (int)(fieldHeight * .75);
        offScreenImage = createImage(dim.width, dim.height);
        offScreenGraphics = offScreenImage.getGraphics();
    }

    /** THis is where the game starts It is the method called when the Applet is ready to start */
    @Override
	public void start() {
        _game = new BricklesGame(this);
        _game.start();
        try {
            Thread.sleep(10);
        }
        catch (InterruptedException e) { }
        repaint();
    }

    /** Standard applet routine */
    @Override
	public void update(Graphics g) {
        paint(g);
    }

    /**
     *This is the main routine for drawing the screen Several message are sent to the "model" to obtain current information
     * Only the location of the paddle is obtained directly from mouse events
     */
    @Override
	public void paint(Graphics g) {
        if (offScreenGraphics == null)
            showStatus("Graphics object is null");
        offScreenGraphics.setColor(getBackground());
        offScreenGraphics.fillRect(0, 0, dim.width, dim.height);
        offScreenGraphics.setColor(getForeground());
        offScreenGraphics.drawRect(1, 1, dim.width - 1, dim.height - 1);
        offScreenGraphics.drawImage(thePaddle.getBitMap(), thePaddle.getPosition().x, thePaddle.getPosition().y, this);
        if (!(thePuck == null)) {
            offScreenGraphics.drawImage(thePuck.getBitMap(), thePuck.getPosition().x, thePuck.getPosition().y, this);
        }
        if (!(theBrickPile == null)) {
            for (int i = 0; i < theBrickPile.getSize(); i++) {
                theBrick = theBrickPile.getBrickAt(i);
                if (!theBrick.isHit()) {
                    offScreenGraphics.drawImage(theBrick.getBitMap(),
                        theBrick.getPosition().x, theBrick.getPosition().y, this);
                }
            }
        }
        g.drawImage(offScreenImage, 0, 0, this);
        try {
            Thread.sleep(10);
        }
        catch (InterruptedException e) { }
    }

    /** Updates the position of the mouse FlipFlop allows some mouse events to be ignored */
    @Override
	public void mouseMoved(MouseEvent e) {
        flipflop++;
        //System.out.println("Inside\n");
        if ((flipflop % _mouseSensitivity) == 0) {
            //System.out.println("About to report x\n");
            //System.out.println("Mouse moved ! "+e.getX()+"\n");
            flipflop = 0;
            xPaddle = e.getX();
            thePaddle.setPosition(xPaddle, thePaddle.getPosition().y);
        }
        //	repaint();
    }

    @Override
	public void mouseDragged(MouseEvent evt) {
    }

    @Override
	public void mouseClicked(MouseEvent evt) {
    }

    @Override
	public void mouseEntered(MouseEvent evt) {
    }

    @Override
	public void mouseExited(MouseEvent evt) {
    }

    @Override
	public void mousePressed(MouseEvent evt) {
    }

    @Override
	public void mouseReleased(MouseEvent evt) {
    }

    /** Returns the width of the playingField */
    public int getFieldWidth() {
        return fieldWidth;
    }

    /** Returns the height of the playingField */
    public int getFieldHeight() {
        return fieldHeight;
    }

    protected void setPaddle(Paddle newPaddle) {
        thePaddle = newPaddle;
    }

    /** Gives the View a reference to the current Puck */
    protected void setPuck(Puck newPuck) {
        thePuck = newPuck;
    }

    /** Gives the View a reference to the BrickPile */
    protected void setBrickPile(BrickPile newBrickPile) {
        theBrickPile = newBrickPile;
    }

    /** Provides acces to the message window at the bottom of the Applet window */
    public void showMessage(String str) {
        showStatus(str);
    }

/*
	public static void Main(String args[]){
		BricklesView game = new BricklesView();
		game.init();
		game.start();
            game.setVisible(true);
	}
*/

    /** Provides access to the current Paddle */
    protected Paddle getPaddle() {
        return thePaddle;
    }

    /** Provides access to the BrickPile */
    protected BrickPile getBrickPile() {
        return theBrickPile;
    }
}
