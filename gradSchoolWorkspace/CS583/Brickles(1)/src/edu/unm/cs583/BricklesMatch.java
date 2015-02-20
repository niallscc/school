package edu.unm.cs583;
public class BricklesMatch extends ArcadeGameMatch {
    PuckSupply puckSupply;
    Puck currentPuck;
    Timer timer;

    public BricklesMatch(ArcadeGame parentGame) {
        super(parentGame);
        timer = new Timer(((BricklesGame)parentGame).getView());
    }

    @Override
	public void tick() {
        super.tick();
    }

    @Override
	public void deleted(ArcadeGamePiece aPiece) {
        if ((Puck)aPiece == currentPuck) {
            currentPuck = this.getNewPuck();
            ((BricklesPlayingField)_fieldPtr).setPuck(currentPuck);
            timer.register(currentPuck);
        }
    }

    @Override
	public void start() {
        _fieldPtr = (((BricklesGame)_game).newPlayingField(this));
        puckSupply = new PuckSupply(_fieldPtr);
        currentPuck = this.getNewPuck();
        ((BricklesPlayingField)_fieldPtr).setPuck(currentPuck);
        timer.register(currentPuck);
        timer.register(this.getCurrentPaddle());
        timer.start();
    }

    public Puck getNewPuck() {
        try {
            currentPuck = puckSupply.getPuck();
        }
        catch (OutOfPucksException ope) {
            _game.lost(); //game is lost
        }
        return currentPuck;
    }

    public Puck getCurrentPuck() {
        return currentPuck;
    }

    public Paddle getCurrentPaddle() {
        return ((BricklesPlayingField)_fieldPtr).getPaddle();
    }

    public BrickPile getBrickPile() {
        return ((BricklesPlayingField)_fieldPtr).getBrickPile();
    }
}
