package edu.unm.cs583;
/**
 *Velocity is the speed and direction of a moving object
 *@author John D. McGregor
 *@version 1.0
 */
class Velocity {
    protected int xSpeed;
    protected int ySpeed;
    protected int speed;
    protected int direction;

    /** Default Constructor initializes all attributes to zero */
    public Velocity() {
        xSpeed = 0;
        ySpeed = 0;
        speed = 0;
        direction = 0;
    }

    /**
     *Constructor
     *@param newSpeed     Defines the initial speed
     *@param newDirection Defines the initial direction
     */
    public Velocity(int newSpeed, int newDirection) {
        direction = newDirection;
        this.setSpeed(newSpeed);
        decomposeSpeed();
    }

    /** reverses the meaning of the X component of velocity */
    public void reverseX() {
        xSpeed = -xSpeed;
        if (direction < 180) {
            direction = 180 - direction;
        }
        else {
            direction = 540 - direction;
        }
    }

    /** reverses the meaning of the Y component of velocity */
    public void reverseY() {
        ySpeed = -ySpeed;
        direction = (360 - direction) % 360;
    }

    /** Setter for speed Also sets XSpeed and YSpeed */
    public void setSpeed(int newSpeed) {
        speed = newSpeed;
        decomposeSpeed();
    }

    /** Setter for the direction post: direction>=0 && direction<=360 */
    public void setDirection(int newDirection) {
        direction = newDirection % 360;
    }

    /** Simple accessor for direction */
    public int getDirection() {
        return direction;
    }

    /** Simple accessor for X component of speed */
    public int getSpeedX() {
        return xSpeed;
    }

    /** Simple accessor for Y component of speed */
    public int getSpeedY() {
        return ySpeed;
    }

    /** Creates the X and Y components of the speed Should be called every time speed is set */
    void decomposeSpeed() {
        xSpeed = (int)(Math.cos(direction / 57.9) * speed);
        ySpeed = (int)(Math.sin(direction / 57.9) * speed);
    }
}
