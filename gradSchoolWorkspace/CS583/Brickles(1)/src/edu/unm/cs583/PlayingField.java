package edu.unm.cs583;
import java.util.Vector;

class PlayingField extends ArcadeGameObject {
    protected ArcadeGameMatch _match;
    protected ArcadeGame _game;
    protected Vector list;
    protected ArcadeGamePiece lastPiece = null;

    public PlayingField(ArcadeGame aGame) {
        _game = aGame;
        list = new Vector(10);
    }

    public void moved(MovablePiece mPiece) throws Collision {
        ArcadeGamePiece piece;
        boolean collided = false;
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                piece = (ArcadeGamePiece)list.elementAt(i);
                if ((piece.getBoundingBox()).intersects(mPiece.getBoundingBox()) && piece != lastPiece) {
                    lastPiece = piece;
                    throw new Collision(mPiece, piece, mPiece.getPosition());
                }
            }
        }
    }

    public void clearCollisions() {
        lastPiece = null;
    }

    public void deleted(MovablePiece mPiece) {
        _match.deleted(mPiece);
    }
};
