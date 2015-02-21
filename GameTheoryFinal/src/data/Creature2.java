package data;

import java.util.Random;

public class Creature2 implements Species {
	
	int me, mVh, hVm;
	int population;
	int numChildren=1;
	int rT=0;
	public Creature2 (int myself, int meVit, int itVme, int numC, int repThresh){
		me = myself; 
		mVh = meVit;
		hVm = itVme;
		numChildren=numC;
		rT=repThresh;
	}
	@Override
	public int mySelf() {
		return me;
	}

	@Override
	public int meVshim() {

		return mVh;
	}

	@Override
	public int himVsme() {

		return hVm;
	}
	@Override
	public void reproduce(int utility) {
		/*
		 * If the utility is right at the reproduction threshold then you can reproduce but at half the 
		 * max reproduction rate 
		 */
		if(utility== rT ){
			population+= numChildren/2;
			//Random r = new Random ( System.currentTimeMillis());
			//population+=Math.abs(r.nextInt()% (numChildren/2));
		}
		/*
		 * else you can reproduce at the full potential
		 */
		else 
		{
			population+= numChildren/2;
			//Random r = new Random ( System.currentTimeMillis());
			//population+=Math.abs(r.nextInt()%numChildren);
		}
	}
	@Override
	public int getPopulation() {
		
		return population;
	}
}
