package swag;

public class ScaleMail extends Armor {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;

	public ScaleMail(){
		this.imageFile="./ItemsIcons/ScaleMail.png";
		this.itemName="Scale Mail";
		this.encumberanceAmount = 15;
		this.cost = 200;
		this.buycost = this.cost * 1.5;
		this.vendorcost = this.cost * .5;
		this.defense = 30;
		this.durability = 100;
		this.levelreq = 4;
	}
	
}
