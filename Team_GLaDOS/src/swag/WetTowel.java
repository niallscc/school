package swag;

public class WetTowel extends Armor {
	/**
	 * Used for serialization
	 */
	private static final long serialVersionUID = 1L;
	
	public WetTowel(){
		this.imageFile="./ItemsIcons/WetTowel.png";
		this.itemName = "Wet Towel";
		this.encumberanceAmount = 5;
		this.cost = 5;
		this.buycost = this.cost * 1.5;
		this.vendorcost = this.cost * .5;
		this.defense = 5;
		this.durability = 100;
		this.levelreq = 1;
		this.armorClass = 5;
	}
	
}
