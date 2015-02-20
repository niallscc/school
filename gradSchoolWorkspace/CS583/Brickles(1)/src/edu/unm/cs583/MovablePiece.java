package edu.unm.cs583;
import java.awt.*;

public abstract class MovablePiece extends ArcadeGamePiece {
    protected Velocity _currentVelocity;
    protected BricklesView _theView;
    protected Point _leadingPoint;

    public MovablePiece(PlayingField fieldPtr, Point initialLocation, Velocity initialVelocity) {
        super(fieldPtr, initialLocation);
        _currentVelocity = initialVelocity;
    }

    @Override
	public Point getPosition() {
        //	return _currentPosition;
        return _leadingPoint;
    }

    public Velocity getVelocity() {
        return _currentVelocity;
    }

    public void setVelocity(Velocity newVelocity) {
        _currentVelocity = newVelocity;
    }

    public void tick() {
        this.move();
        try {
            //System.out.println("In try/moved\n");
            _fieldPtr.moved(this);
        }
        catch (Collision aCollision) {
            aCollision.occur();
        }
    }

    public abstract void move();

    public void collideWith(ArcadeGamePiece aPiece, Point aPoint) { }

    @Override
	public abstract void collideWithPaddle(Paddle aPaddle, Point aPoint);

    @Override
	public abstract void collideWithPuck(Puck aPuck, Point aPoint);

    public void reverseY() {
        _currentVelocity.reverseY();
    }

    public void reverseX() {
        _currentVelocity.reverseX();
    }
}
