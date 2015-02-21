package swag;


public class ButterKnife extends Weapon {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ButterKnife(){
		this.imageFile="./ItemsIcons/ButterKnife.png";
		this.itemName="Butter Knife";
		this.minDamage = 75;
		this.maxDamage = 150;
		this.strMultiplier = 2;
		this.cost = 50;
		this.vendorcost = this.cost*.5;
		this.buycost = this.cost*1.5;
		this.encumberanceAmount = 10;
		this.durability = 100;
		this.levelreq = 9;
	}


}
