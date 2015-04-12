package swag;

public class SnailMail extends Armor {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;

	public SnailMail(){
		this.imageFile="./ItemsIcons/SnailMail.png";
		this.itemName="Snail Mail";
		this.encumberanceAmount = 40;
		this.cost = 20000;
		this.buycost = this.cost * 1.5;
		this.vendorcost = this.cost * .5;
		this.defense = 75;
		this.durability = 100;
		this.levelreq = 8;
	}

}
