package edu.unm.cs583;
import java.awt.*;

/**
 *ArcadeGamePiece supports objects being placed ona PlayingField
 * This is an abstract class because it does not know how to handle collisions
 *@author John D. McGregor
 *@version 1.0
 */
public abstract class ArcadeGamePiece extends ArcadeGameObject {
    protected Point _currentPosition;
    protected PlayingField _fieldPtr;

    /**
     *The constructor sets state
     *@param fieldPtr reference to the PlayingField that contains the ArcadeGamePiece
     *@param initialLocation where the ArcadeGamePiece is located
     */
    ArcadeGamePiece(PlayingField fieldPtr, Point initialLocation) {
        _currentPosition = initialLocation;
        _fieldPtr = fieldPtr;
    }

    /**
     *Simple accessor of the current position of the piece pre: instance exists
     *@return the current position
     */
    public Point getPosition() {
        return _currentPosition;
    }

    /**
     *Simple modifier of the current position of the piece
     *@param newPoint the new position of the ArcadeGamePiece pre:  newPoint.x >= 0 && newPoint.x <= fieldWidth && newPoint.y
     * >= 0 && newPoint.y <= fieldHeight post: currentPosition has been updated
     */
    public void setPosition(Point newPoint) {
        _currentPosition = newPoint;
    }

    /**
     *Modifier of the current position of the piece
     *@param x the x component of the new position of the ArcadeGamePiece
     *@param y the y component of the new position of the ArcadeGamePiece
     * pre:  x >= 0 && x <= fieldWidth && y >= 0 && y <= fieldHeight post: currentPosition has been updated
     */
    public void setPosition(int x, int y) {
        _currentPosition.x = x;
        _currentPosition.y = y;
    }

    abstract void collideWithPaddle(Paddle aPaddle, Point aPoint);

    abstract void collideWithPuck(Puck aPuck, Point aPoint);
}
