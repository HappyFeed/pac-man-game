package model;

public class PacMan {
    //Attributes
	private int radio;
	private int posX;
	private int posY;
	private int direction;
	private int speed;
	private boolean state;
	
	//Methods
	public PacMan(int nRadio, int nPosX, int nPosY, int nDirection, int nSpeed, boolean nState) {
		radio=nRadio;
		posX=nPosX;
		posY=nPosY;
		direction=nDirection;
		speed=nSpeed;
		state=nState;
	}

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
	
}
