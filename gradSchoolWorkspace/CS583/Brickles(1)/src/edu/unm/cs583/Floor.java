package edu.unm.cs583;
import java.awt.*;

class Floor extends StationaryPiece {
    protected Point _currentPosition2;
    protected BricklesView _theView;

    public Floor(PlayingField fieldPtr, Point atPoint1, Point atPoint2) {
        super(fieldPtr, atPoint1);
        _currentPosition2 = atPoint2;
        _theView = ((BricklesPlayingField)_fieldPtr).getView();
        this.newBoundingRect();
    }

    void newBoundingRect() {
        _boundingBox = new Rectangle(_currentPosition.x, _currentPosition.y, _currentPosition.x + _currentPosition2.x,
            _currentPosition.y + _currentPosition2.y);
    }

    public void collideWith(ArcadeGamePiece aPiece, Point atPoint) {
    }

    @Override
	public void collideWithPaddle(Paddle aPaddle, Point atPoint) {
    }

    @Override
	public void collideWithPuck(Puck aPuck, Point atPoint) {
        // _theView.showStatus("Now invoking aPuck.deleted");
        aPuck.deleted();
    }
}
