package edu.unm.cs583;
import java.awt.*;

public class Puck extends MovablePiece {
    protected static int _radius = 20;
    protected static int _halfradius = 10;
    protected Dimension _playingFieldDimension;
    protected boolean _kaput;
    protected Paddle tmpPaddle;
    protected BrickPile tmpBrickPile;

    public Puck() {
        super(null, new Point(150, 90), new Velocity(8, 225));
        _extent = new Dimension(_radius, _radius);
        _boundingBox = new Rectangle(_currentPosition, _extent);
        _leadingPoint = new Point(_currentPosition.x, _currentPosition.y);
        newLeadingPoint();
    }

    public Puck(PlayingField fieldPtr) {
        //This sets the initial position of a new puck
        super(fieldPtr, new Point(150, 90), new Velocity(8, 225));
        _fieldPtr = fieldPtr;
        _theView = ((BricklesPlayingField)_fieldPtr).getView();
        tmpPaddle = _theView.getPaddle();
        tmpBrickPile = _theView.getBrickPile();
        _extent = new Dimension(_radius, _radius);
        _boundingBox = new Rectangle(_currentPosition, _extent);
        _leadingPoint = new Point(_currentPosition.x, _currentPosition.y);
        newLeadingPoint();
        _bitmap = _theView.getImage(_theView.getCodeBase(), "images/puck.gif");
        if (_bitmap == null)
            _theView.showStatus("BitMap not loaded");
        _kaput = false;
    }

    public void newLeadingPoint() {
        int direction = _currentVelocity.getDirection();
        if (direction >= 0 && direction < 90) {
            _leadingPoint.y = _currentPosition.y;
            _leadingPoint.x = _currentPosition.x + _radius;
        }
        else if (direction >= 90 && direction < 180) {
            _leadingPoint.x = _currentPosition.x;
            _leadingPoint.y = _currentPosition.y;
        }
        else if (direction >= 180 && direction < 270) {
            _leadingPoint.y = _currentPosition.y + _radius;
            _leadingPoint.x = _currentPosition.x;
        }
        else if (direction >= 270 && direction < 360) {
            _leadingPoint.x = _currentPosition.x + _radius;
            _leadingPoint.y = _currentPosition.y + _radius;
        }
    }

    @Override
	public void move() {
        int hitbrickcount = 0;
        GameOverDialog wonDialog;
        if (_kaput == true)
            _fieldPtr.deleted(this);
        _playingFieldDimension = _theView.getSize();
        _currentPosition.x = _currentPosition.x + _currentVelocity.getSpeedX();
        _currentPosition.y = _currentPosition.y - _currentVelocity.getSpeedY();
        newLeadingPoint();
        _boundingBox = new Rectangle(_currentPosition, _extent);
        try {
            _fieldPtr.moved(this);
        }
        catch (Collision c) {
            c.occur();
        }
        //_playingFieldDimension = _theView.getSize();
        //_currentPosition.x = _currentPosition.x+ _currentVelocity.getSpeedX();
        //_currentPosition.y = _currentPosition.y - _currentVelocity.getSpeedY();
        newLeadingPoint();
        _boundingBox = new Rectangle(_currentPosition, _extent);
        for (int i = 0; i < tmpBrickPile.getSize(); i++) {
            Brick tmpBrick = tmpBrickPile.getBrickAt(i);
            if (tmpBrick.isHit()) {
                hitbrickcount++;
            }
        }
        if (hitbrickcount == tmpBrickPile.getSize())
            wonDialog = new GameOverDialog(new Frame(), "You Won!", false);
    }

    @Override
	public void collideWith(ArcadeGamePiece aPiece, Point aPoint) {
        aPiece.collideWithPuck(this, _leadingPoint);
        if (_kaput == true) {
            _fieldPtr.deleted(this);
        }
    }

    @Override
	public void collideWithPaddle(Paddle aPaddle, Point aPoint) {
        aPaddle.reverseY();
    }

    @Override
	public void collideWithPuck(Puck aPuck, Point aPoint) { }

    public void deleted() {
        _kaput = true;
    }
}
