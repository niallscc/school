package swag;

public class Misericorde extends Weapon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Misericorde(){
		this.imageFile="./ItemsIcons/Misericorde.png";
		this.itemName="Miseriecorde";
		this.minDamage = 30;
		this.maxDamage = 60;
		this.strMultiplier = .8;
		this.cost = 5000;
		this.vendorcost = this.cost*.5;
		this.buycost = this.cost*1.5;
		this.encumberanceAmount = 10;
		this.durability = 100;
		this.levelreq = 6;
	
	}

}
