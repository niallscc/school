package edu.unm.cs583;
import java.awt.*;
import java.util.*;

public class Collision extends Exception {
    protected MovablePiece mPiece;
    protected ArcadeGamePiece aPiece;
    protected Point location;
    protected Vector vector;

    public Collision(MovablePiece movablePiece, ArcadeGamePiece arcadeGamePiece, Point point) {
        location = point;
        mPiece = movablePiece;
        aPiece = arcadeGamePiece;
    }

    public void occur() {
        //System.out.println("collision" + aPiece.getClass() + " "+mPiece.getClass());
        mPiece.collideWith(aPiece, location);
    }
}
