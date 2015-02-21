package swag;


public class Bludgeon extends Weapon {
	
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	
	

	public Bludgeon(){
		this.imageFile="./ItemsIcons/Bludgeon.png";
		this.itemName="Bludgeon";
		this.minDamage = 3;
		this.maxDamage = 7;
		this.strMultiplier = 0.15;
		this.cost = 20;
		this.vendorcost = this.cost*.5;
		this.buycost = this.cost*1.5;
		this.encumberanceAmount = 30;
		this.durability = 100;
		this.levelreq = 2;
	}
}
