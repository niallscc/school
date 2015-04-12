package swag;

public class Zweihander extends Weapon {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;

	public Zweihander(){
		this.imageFile="./ItemsIcons/Zweihander.png";
		this.itemName="Zweihander";
		this.minDamage = 40;
		this.maxDamage = 80;
		this.strMultiplier = 1.0;
		this.cost = 7500;
		this.vendorcost = this.cost*.5;
		this.buycost = this.cost*1.5;
		this.encumberanceAmount = 35;
		this.durability = 100;
		this.levelreq = 7;
	}

}
