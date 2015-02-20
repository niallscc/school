package edu.unm.cs583;
public abstract class ArcadeGame extends Object {
    protected ArcadeGameMatch _match;
    protected String _name;

    public ArcadeGame(String name) {
        _name = name;
    }

    public ArcadeGameMatch getMatch() {
        return _match;
    }

    public abstract PlayingField newPlayingField(ArcadeGameMatch aGame);

    public abstract void lost();
};
