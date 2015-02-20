package edu.unm.cs583;
import java.awt.*;

/**
 *This class specializes the PlayingField to the Brickles game.
 *@author John D. McGregor
 *@version 1.0
 */
public class BricklesPlayingField extends PlayingField {
    protected static int _minimumXInc = -100;
    protected static int _minimumYInc = -100;
    protected static int _maximumXInc = 100;
    protected static int _maximumYInc = 100;
    protected BricklesView _theView;
    protected Paddle _thePaddle;
    protected int xPaddle;
    protected int yPaddle;
    protected Puck _thePuck;
    protected Brick _theBrick;
    protected BrickPile _theBrickPile;

    /**
     *The constructor for BricklesPlayingField constructs the obstacles that are
     * the boundaries for the room. It also provides the View class with the references needed to update state.
     *@param game is a reference to the game being played on the playingField
     */
    public BricklesPlayingField(BricklesGame game) {
        super(game);
        _theView = ((BricklesGame)_game).getView();
        _match = ((BricklesGame)_game).getMatch();
        list.addElement(
            new Ceiling(this, new Point(_minimumXInc, _minimumYInc), new Point(_theView.getFieldWidth() + _maximumXInc, 0)));
        list.addElement(
            new Wall(this, new Point(_minimumXInc, _minimumYInc), new Point(0, _theView.getFieldHeight() + _maximumYInc)));
        list.addElement(
            new Wall(this, new Point(_theView.getFieldWidth(), _minimumYInc),
            new Point(_theView.getFieldWidth() + _maximumXInc, _theView.getFieldHeight() + _maximumYInc)));
        list.addElement(
            new Floor(this, new Point(_minimumXInc, _theView.getFieldHeight()),
            new Point(_theView.getFieldWidth() + _maximumXInc, _theView.getFieldHeight() + _maximumYInc)));
        xPaddle = (_theView.getFieldWidth() / 2);
        yPaddle = (int)(_theView.getFieldHeight() * .75);
        _thePaddle = new Paddle(this, new Point(xPaddle, yPaddle), new Velocity(0, 0));
        list.addElement(_thePaddle);
        _theView.setPaddle(_thePaddle);
        _theBrickPile = new BrickPile(this, new Point(5, 20), _theView);
        list.addElement(_theBrickPile);
        _theView.setBrickPile(_theBrickPile);
        _theView.repaint();
    }

    public Paddle getPaddle() {
        return _thePaddle;
    }

    public void setPuck(Puck newPuck) {
        _thePuck = newPuck;
        _theView.setPuck(_thePuck);
        lastPiece = _thePuck;
    }

    public BrickPile getBrickPile() {
        return _theBrickPile;
    }

    public BricklesView getView() {
        return _theView;
    }
};
