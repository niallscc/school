package swag;


public class ChainMailBikini extends Armor {

	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ChainMailBikini(){
		this.imageFile="./ItemsIcons/ChainMailBikini.png";
		this.itemName = "Chain Mail Bikini";
		this.encumberanceAmount = 5;
		this.cost = 200000;
		this.buycost = this.cost * 1.5;
		this.vendorcost = this.cost * .5;
		this.defense = 200;
		this.durability = 100;
		this.levelreq = 10;
	}




}

