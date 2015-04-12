package swag;

public class Mace extends Weapon {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;

	public Mace(){
		this.imageFile="./ItemsIcons/Mace.png";
		this.itemName="Mace";
		this.minDamage = 10;
		this.maxDamage = 15;
		this.strMultiplier = 0.1;
		this.cost = 50;
		this.vendorcost = this.cost*.5;
		this.buycost = this.cost*1.5;
		this.encumberanceAmount = 40;
		this.durability = 100;
		this.levelreq = 3;
	}

}
