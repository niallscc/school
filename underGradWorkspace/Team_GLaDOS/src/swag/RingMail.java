package swag;

public class RingMail extends Armor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public RingMail(){
		this.imageFile="./ItemsIcons/RingMail.png";
		this.itemName = "Rolled up Sock";
		this.encumberanceAmount = 15;
		this.cost = 20;
		this.buycost = this.cost * 1.5;
		this.vendorcost = this.cost * .5;
		this.defense = 15;
		this.durability = 100;
		this.levelreq = 3;
		this.armorClass = 15;
	}
	//Level 3

}
