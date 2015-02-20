package edu.unm.cs583;
import java.awt.*;

public class Paddle extends MovablePiece {
    public Paddle(PlayingField fieldPtr, Point initialPosition, Velocity initialVelocity) {
        super(fieldPtr, initialPosition, initialVelocity);
        _theView = ((BricklesPlayingField)_fieldPtr).getView();
        _bitmap = _theView.getImage(_theView.getCodeBase(), "images/paddle.gif");
        if (_bitmap == null)
            _theView.showStatus("BitMap not loaded");
        _extent = new Dimension(60, 20);
        _leadingPoint = new Point(_currentPosition.x, _currentPosition.y);
        newLeadingPoint();
        _boundingBox = new Rectangle(_currentPosition, _extent);
    }

    public void newLeadingPoint() {
        int direction = _currentVelocity.getDirection();
        _leadingPoint.y = _currentPosition.y;
        if (direction >= 0 && direction < 90) {
            _leadingPoint.x = _currentPosition.x;
        }
        else if (direction >= 90 && direction < 180) {
            _leadingPoint.x = _currentPosition.x + _extent.width;
        }
        else if (direction >= 180 && direction < 270) {
            _leadingPoint.x = _currentPosition.x;
        }
        else if (direction >= 270 && direction < 360) {
            _leadingPoint.x = _currentPosition.x + _extent.width;
        }
    }

    @Override
	public void tick() {
        move();
    }

    @Override
	public void collideWithPaddle(Paddle aPaddle, Point aPoint) {
    }

    @Override
	public void collideWith(ArcadeGamePiece aPiece, Point aPoint) {
        aPiece.collideWithPaddle(this, aPoint);
    }

    @Override
	public void collideWithPuck(Puck aPuck, Point aPoint) {
        aPuck.reverseY();
    }

    @Override
	public void move() {
        if (_currentPosition.x + _extent.width >= _theView.getFieldWidth())
            _currentPosition.x = _theView.getFieldWidth() - _extent.width;
        _boundingBox = new Rectangle(_currentPosition, _extent);
        newLeadingPoint();
    }
}
