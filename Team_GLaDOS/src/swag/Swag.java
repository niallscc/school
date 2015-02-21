package swag;

import java.awt.Image;

public interface Swag {
	public int getEncumberance();
	public String getName();
	public Image getImage();
	public double getVendorCost();
	public double getVendorWorth();
	public boolean isWeapon();
	public boolean isArmor();
	
}
