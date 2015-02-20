package edu.unm.cs583;
import java.awt.*;

class Wall extends StationaryPiece {
    protected Point _currentPosition2;

    public Wall(PlayingField fieldPtr, Point atPoint1, Point atPoint2) {
        super(fieldPtr, atPoint1);
        _currentPosition2 = atPoint2;
        this.newBoundingRect();
    }

    void newBoundingRect() {
        _boundingBox = new Rectangle(_currentPosition.x, _currentPosition.y,
            Math.abs(_currentPosition.x) + _currentPosition2.x, Math.abs(_currentPosition.y) + _currentPosition2.y);
    }

    void collideWith(ArcadeGamePiece aPiece, Point atPoint) {
    }

    @Override
	void collideWithPaddle(Paddle aPaddle, Point atPoint) {
        aPaddle.reverseX();
    }

    @Override
	void collideWithPuck(Puck aPuck, Point atPoint) {
        aPuck.reverseX();
    }
}
