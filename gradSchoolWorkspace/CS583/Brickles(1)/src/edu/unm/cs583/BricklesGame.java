package edu.unm.cs583;
import java.awt.*;

/**
 *This class specializes the concept of a Game to that of a Brickles Game. It produces the correct type of PlayingField
 * and the correct type of Match.
 *@author John D. McGregor
 *@version 1.0
 */
public class BricklesGame extends ArcadeGame {
    protected BricklesView _theView;

    /**
     *The constructor sets the Model class to have a reference to the
     * single View class. Since only one View is possible there is no need for a more general facility.
     *@param theView This is the reference to the View object. pre:  An instance of the BricklesView class has been created
     * post: A new instance of BricklesGame exists
     */
    public BricklesGame(BricklesView theView) {
        super("Brickles");
        _theView = theView;
        _match = new BricklesMatch(this);
    }

    /** This method propogates the start message pre:  none post: The match has been started. */
    public void start() {
        _match.start();
    }

    /**
     *This method is a simple accessor to obtain the View reference
     * pre:  The reference to a View has been initialized post: no state changed
     *@return returns the current View reference
     */
    public BricklesView getView() {
        return _theView;
    }

    /**
     *Factory method that produces the correct PlayingField pre:  An instance of ArcadeGameMatch exists
     * post: new instance of BricklesPlayingField exists
     *@param aMatch is the current Match
     *@return the new instance of BricklesPlayingField
     */
    @Override
	public PlayingField newPlayingField(ArcadeGameMatch aMatch) {
        return new BricklesPlayingField(this);
    }

    /** This method is called when the Player has lost pre:  none post: A modal dialog window has been spawned. */
    @Override
	public void lost() {
        GameOverDialog lostDialog = new GameOverDialog(new Frame(), "You Lost!", false);
    }
};
