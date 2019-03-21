package threads;

import java.io.IOException;

import ui.PacManController;

public class PacManThread extends Thread{
	
	private PacManController pc;

	public PacManThread(PacManController pc){
		this.pc = pc;
	}
	
	@Override
	public void run() {
		try {	
			pc.upThreads();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.err.println("fuck");
		}
		
	}
	
}