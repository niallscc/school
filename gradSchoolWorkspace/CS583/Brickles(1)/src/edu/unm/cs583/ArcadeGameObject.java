package edu.unm.cs583;
import java.awt.*;

/**
 *This is the top level class for any class participating in the Game framework
 *@author John D. McGregor
 *@version 1.0
 */
public class ArcadeGameObject {
    protected Image _bitmap;
    protected Rectangle _boundingBox;
    protected Dimension _extent;

    ArcadeGameObject() {
    }

    public Image getBitMap() {
        return _bitmap;
    }

    public Rectangle getBoundingBox() {
        return _boundingBox;
    }

    public boolean isContained(Point location) {
        return _boundingBox.contains(location);
    }
}
