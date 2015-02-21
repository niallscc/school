package swag;

public class PlateMail extends Armor {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;

	public PlateMail(){
		this.imageFile="./ItemsIcons/PlateMail.png";
		this.itemName="Plate Mail";
		this.encumberanceAmount = 25;
		this.cost = 2000;
		this.buycost = this.cost * 1.5;
		this.vendorcost = this.cost * .5;
		this.defense = 50;
		this.durability = 100;
		this.levelreq = 6;
	}
	
}
