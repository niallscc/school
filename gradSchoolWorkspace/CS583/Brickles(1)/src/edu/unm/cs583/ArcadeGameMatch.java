package edu.unm.cs583;
/**
 *The abstract class that defines the interface for the ArcadeGameMatch A match consists of a timer, a playingField and a game
 *@author John D. McGregor
 *@version 1.0
 */
abstract class ArcadeGameMatch implements TimeObservable {
    protected ArcadeGame _game;
    protected PlayingField _fieldPtr;
    protected Timer _timer;

    /** The constructor sets the reference to the game that is part of the match */
    public ArcadeGameMatch(ArcadeGame theGame) {
        _game = theGame;
    }

    /**
     *The abstract signature for deleted method
     *@param aPiece is the ArcadeGamePiece to be deleted
     */
    public abstract void deleted(ArcadeGamePiece aPiece);

    public abstract void start();

    @Override
	public void tick() {
    }
}
