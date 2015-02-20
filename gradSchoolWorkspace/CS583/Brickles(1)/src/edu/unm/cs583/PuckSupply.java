package edu.unm.cs583;
public class PuckSupply {
    protected int maxPucks;
    protected int usedPucks;
    protected PlayingField fieldPtr;

    public PuckSupply(PlayingField field) {
        maxPucks = 3;
        usedPucks = 0;
        fieldPtr = field;
    }

    public int numberLeft() {
        return maxPucks - usedPucks;
    }

    public Puck getPuck() throws OutOfPucksException {
        if (usedPucks < maxPucks) {
            usedPucks = usedPucks + 1;
            return new Puck(fieldPtr);
        }
        else {
            throw new OutOfPucksException();
        }
    }
};
