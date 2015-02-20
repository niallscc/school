package edu.unm.cs583;

/**
 * CSE126 Lecture 10
 * 2/21/07
 * 
 * Rectangle.java
 */

// Class declaration
public class Rectangle {

	// Instance variable declaration
	private int x, y, width, height;
	
	// Constructor declaration
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	// Instance variable accessors
	public int getX() { return x; }
	public int getY() {	return y; }
	public int getWidth() {return width; }
	public int getHeight() { return height; }
	
	// Accessors for computed values
	public int area() {
		return width * height;
	}
	
	public double diagonal() {
		if( width == 0 && height== 0)
			return 0.0;
		else
			return Math.sqrt(width*width + height*height );
	}
	
	// Overriden Object class methods
	public boolean equals(Object obj) {
		if(obj instanceof Rectangle) {
			Rectangle r = (Rectangle)obj;
			return x == r.x
				&& y == r.y
				&& width == r.width
				&& height == r.height;
		}
		return false;
	}
	
	public String toString() {
		return "(" + x + "," + y + "), width = " + width + ", height = " + height; 
	}
	
	// Methods
	public Rectangle union(Rectangle r) {
		int newX = Math.min(x, r.x);
		int newY = Math.min(y, r.y);
		int newWidth = Math.max(x + width, r.x + r.width) - newX;
		int newHeight = Math.max(y + height, r.y + r.height) - newY;
		return new Rectangle(newX, newY, newWidth, newHeight);
	}
	
	public boolean contains(int x, int y) {
		return this.x <= x 
			&& x <= this.x + width
			&& this.y <= y
			&& y <= this.y + height;
	}

/* The following methods were not written in class */	
	
	public boolean intersects( Rectangle r) {
		/**
		 * Incorrect solution based on in class tests
		 * 
		 * This solution will work for the test cases we had
		 * *in class*, but asymmetrically.  Some test cases 
		 * will pass as a.intersects(b) but not as 
		 * b.intersects(a)
		 * 
		 * NOTE: The tests given for this method will fail
		 * with this implementation.
		 */
		/*
		// UL corner of r is in 'this'
		return contains(r.x, r.y) 
				// UR corner of r is in 'this'
			|| contains(r.x, r.y + r.height)
				// LL corner of r is in 'this'
			|| contains(r.x + r.width, r.y)
				// UL corner of r is in 'this'
			|| contains(r.x + r.width, r.y + r.height)
				// At least one extreme x value of r is between
				//  the exteme x values of 'this'
			|| ((x <= r.x && r.x <= x + width 
					|| x <= r.x + r.width && r.x + r.width <= x + width)
					// And at least one extreme y value of 'this' is
					//  is between the exteme values of r
				&&
				(r.y <= y && y <= r.y + r.height
					|| r.y <= y + height && y + height <= r.y + r.height));
		*/
		/**
		 * A correct solution
		 * 
		 * Thinking less about testing to see if the 
		 * coordinate of one rectangle is between the
		 * coordinates of another rectangle, focus on
		 * the opposite problem: when are the rectangles
		 * NOT intersecting
		 * 
		 * To test this, make sure you comment out the
		 * full return statement above before uncommenting
		 * these statements.
		 */
				// 'this' starts further right than r ends
		return !(r.x + r.width <= x 
				// r starts further right than 'this' ends 
		|| x + width <= r.x
				// 'this' starts lower than r ends
			|| r.y + r.height <= y 
				// r starts lower than r ends
			|| y + height <= r.y);		
		
	}
	public Boolean isSquare(){
		if ( this.getWidth() == this.getHeight())
			return true;
		else
			return false;
	}
	public Boolean isRectangle(){
		if ( this.getWidth() == this.getHeight())
			return false;
		else
			return true;
	}
	public Rectangle intersection(Rectangle r) {
		if(intersects(r))  {
			int newX = Math.max(x, r.x);
			int newY = Math.max(y, r.y);
			return new Rectangle(
					newX,
					newY,
					Math.min(x + width - newX, r.x + r.width - newX),
					Math.min(y + height - newY, r.y + r.height - newY));
		}
		
		return null;
	}
}
