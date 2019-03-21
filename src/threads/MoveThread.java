package threads;

import model.Move;
import model.PacMan;
import ui.PacManController;

public class MoveThread extends Thread{
	private PacManController pc;
    private PacMan pm;
	private boolean stop;
	public MoveThread(PacManController paco, PacMan pm, boolean stp) {
		this.pc = paco;
        this.pm = pm;
        this.stop=stp;
	}
	
	public void run() {
		Move m=pm.getMove();
		while(stop) {
			if(m==Move.UP ||m==Move.DOWN) {
				pm.move((int)pc.getHeigth());
			}else {
				pm.move((int)pc.getWith());
			}
				try {
					sleep(pm.getSpeed());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		
	}
	public void stopsThread() {
		stop=!stop;
	}
}

