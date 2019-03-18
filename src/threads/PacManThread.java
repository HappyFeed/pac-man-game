package threads;

import ui.PacManController;

public class PacManThread extends Thread{
	private PacManController pcT;
	private int direction;
	private boolean c;
	
	public PacManThread( int direction) {
		pcT = new PacManController();
		this.direction=direction;
		c=true;
	}
	
	public void run() {
		while(c) {
			if(direction==1){
				pcT.left(true);
				pcT.rigth(true);
			}else if(direction==2) {
				
			}else if(direction==3) {
				
			}else if(direction==4) {
				
			}
		}
		
		
	}
	
	public void s() {
		c = !c;
	}
}

