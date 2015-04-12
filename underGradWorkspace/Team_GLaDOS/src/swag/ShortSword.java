package swag;

public class ShortSword extends Weapon {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;

	public ShortSword(){
		this.imageFile="./ItemsIcons/ShortSword.png";
		this.itemName = "Short Sword";
		this.minDamage = 6;
		this.maxDamage = 10;
		this.strMultiplier = 0.2;
		this.cost = 30;
		this.vendorcost = this.cost*.5;
		this.buycost = this.cost*1.5;
		this.encumberanceAmount = 25;
		this.durability = 100;
		this.levelreq = 3;
	}


}
