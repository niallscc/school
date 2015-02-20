package edu.unm.cs583;
import java.awt.*;

class BrickPile extends CompositePiece {
    public BrickPile(PlayingField fieldPtr, Point atPoint1, BricklesView theView) {
        super(fieldPtr, atPoint1);
        int initialX = atPoint1.x;
        int initialY = atPoint1.y;
        int x = initialX;
        int y = initialY;
        int numRows = 2;
        int numberOfBricksPerRow = 6;
        final int numberOfBricks = 12;
        for (int i = 0; i < numRows; i++) {
            x = initialX;
            for (int j = 0; j < numberOfBricksPerRow; j++) {
                _list.addElement(new Brick((BricklesPlayingField)fieldPtr, new Point(x, y), theView));
                x = x + 70;
            }
            y = y + 25;
        }
        _extent = new Dimension(440, numRows * 20 + (numRows - 1) * 5); //20);
        _boundingBox = new Rectangle(_currentPosition, _extent);
    }
    public int getNumberOfBricks(){
    	return _list.size();
    }
    public void collideWith(ArcadeGamePiece aPiece, Point atPoint) {
    }

    @Override
	public void collideWithPaddle(Paddle aPaddle, Point atPoint) {
    }

    public void moved(MovablePiece mPiece) throws Collision {
        for (int i = 0; i < this.getSize(); i++) {
            if (((Brick)_list.elementAt(i)).getBoundingBox().intersects(mPiece.getBoundingBox())) {
                _fieldPtr.clearCollisions();
                throw new Collision(mPiece, ((Brick)_list.elementAt(i)), mPiece.getPosition());
            }
        }
    }

    @Override
	public void collideWithPuck(Puck aPuck, Point atPoint) {
        try {
            moved(aPuck);
        }
        catch (Collision c) {
            c.occur();
        }
    }

    public Brick getBrickAt(int i) {
        return (Brick)_list.elementAt(i);
    }

    public int getSize() {
        return _list.size();
    }
}
