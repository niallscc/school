package gameBoard;
import java.awt.Color;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Serializable, Lifeform{
	
	private int pc, g;
	private String n;
	Color cc;
	private int level, strength, speed, magic, exp;
	private int maxHP, curHP, maxMP, curMP;
	//0 is medic, 1 is marine,2 is a new recruit
	public Player(String name, int playerClass, int gend, Color c){
		pc=playerClass;
		n=name;
		g=gend;
		cc=c;
		exp=0;
		//medic
		if( pc==0){
			level=3;
			strength=5;
			speed=6; 
			magic=9;
			
		}
		//marine
		else if( pc==1){
			level=2;
			strength=9;
			speed=4;
			magic=3;
		}
		//new Recruit
		else{
			level=1;
			strength=5;
			speed=5;
			magic=5;
		}
	}
	
	public int getPC(){
		return pc;
	}
	public String getName(){
		return n;
	}
	public Color getCharColor(){
		return cc;
	}
	public int getGender(){
		return g;
	}
	
	
	public void setLevel(int newLevel){
		level= newLevel;
	}
	public void setSpeed(int sp){
		speed=sp;
	}
	public void setMagic(int ma){
		magic=ma;
	}
	public void setStrength(int st){
		strength=st;
	}
	public void setMaxHP(int hp){
		maxHP=hp;
	}
	
	
	
	public int getLevel(){
		return level;
	}
	public int getMagic(){
		return magic;
	}
	public int getSpeed(){
		return speed;
	}
	public int getStrength(){
		return strength;
	}
	public int getMaxHP(){
		return maxHP;
	}
	public int getMaxMP(){
		return maxMP;
	}
	public int getCurMP(){
		return curMP;
	}
	
	
	
	public void setMaxMP(int mp){
		maxMP=mp;
	}
	public void setCurMP(int mp){
		curMP=mp;
	}
	public void setCurHP(int hp){
		curHP=hp;
	}
	public int getCurHP(){
		return curHP;
	}
	public void setExp(int ex){
		exp=ex;
	}
	public int getExp(){
		return exp;
	}

	/**
	 * @return
	 */
	public String getImageFileName() {
		
		return "./playerPic,jpg";
	}
	

}
