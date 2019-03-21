package model;

public class PacMan {
   
	public static final int ADVANCE = 5;
	
	//Attributes
	private int radio;
	private int posX;
	private int posY;
	private int direction;
	private int speed;
	private Move ste;
	private boolean state;
	private double length;
	private double angle;
	private boolean flag;
	private int bounces;
	
	//Methods
	public PacMan(int nRadio, int nPosX, int nPosY, int nDirection, int nSpeed, boolean nState, Move st, int nBounces) {
		radio=nRadio;
		posX=nPosX;
		posY=nPosY;
		direction=nDirection;
		speed=nSpeed;
		state=nState;
		ste=st;
		length=270;
		flag=true;
		bounces=nBounces;
	}

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}
    
	public int getBounces() {
		return bounces;
	}

	public void setBounces(int bounces) {
		this.bounces = bounces;
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
	
	public Move getMove() {
		return ste;
	}
	
	public double getLength() {
		return length;
	}
	public double getAngle() {
		return angle;
	}
	public void move(int max) {
		switch(ste) {
			case RIGHT:
				angle=0;
				movement();
				if(posX+ADVANCE+radio>=max) {
					ste = Move.LEFT;
					posX = max-radio;
					bounces++;
				}else {
					posX = posX+ADVANCE;					
				}
			break;
			case LEFT:
				angle=180;
				movement();
				if(posX-ADVANCE-radio<=0) {
					ste = Move.RIGHT;
					posX = radio;
					bounces++;
				}else {
					posX = posX-ADVANCE;					
				}
			break;
			case UP:
				angle=270;
				movement();
				if(posY-ADVANCE-radio<=0) {
					ste = Move.DOWN;
					posY = radio;
					bounces++;
				}else {
					posY = posY-ADVANCE;					
				}
			break;
			case DOWN:
				angle=90;
				movement();
				if(posY+ADVANCE+radio>=max) {
					ste = Move.UP;
					posY = max-radio;
					bounces++;
				}else {
					posY = posY+ADVANCE;					
				}
			break;
		}
	}
	public void movement() {
		
		if(flag) {
			length=length+4;
		}
		if(length>=360) {
			flag=false;
		}
		if(!flag) {
			length=length-4;
		}
		if(length<=270) {
			flag=true;
		}
	}
}
