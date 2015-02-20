package edu.unm.cs583;
import java.awt.Point;

public abstract class StationaryPiece extends ArcadeGamePiece {
    StationaryPiece(PlayingField field, Point point) {
        super(field, point);
    }

    @Override
	abstract void collideWithPaddle(Paddle paddle, Point point);

    @Override
	abstract void collideWithPuck(Puck puck, Point point);
}
