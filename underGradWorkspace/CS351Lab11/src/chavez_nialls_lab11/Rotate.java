package chavez_nialls_lab11;

public class Rotate implements Runnable {
	private Lab11 pane;
	public Rotate(Lab11 p){
		pane=p;
	}
	
	@Override
	public void run() {
	
		
		while(true){

			//TODO Rotate the square		
			
			pane.shape.rotate(5.0);
			
			//TODO repaint();
			pane.repaint();
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
