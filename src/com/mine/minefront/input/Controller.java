package com.mine.minefront.input;

public class Controller {

	public double x, z, y, rotation, xa, za, rotationa;
	public static boolean turnLeft = false;
	public static boolean turnRight = false;
	public static boolean walk = false;
	//horizontal, vertical, jump
	
	public void tick(boolean forward, boolean back, boolean left,boolean right, boolean jump, boolean crouch, boolean sprint) {
		double rotationSpeed = 0.025;
		double walkSpeed = 2.0;
		double crouchHeight = 0.33;
		double jumpHeight = 0.5;
		double xMove = 0;
		double zMove = 0;
	
		if (forward) {
			zMove++;
			//add 1 to z when forward is pressed 
			walk = true;
		}
		if (back) {
			zMove--;
			walk = true;
		}
		if (left) {
			xMove--;
			walk = true;
		}
		if (right) {
			xMove++;
			walk = true;
		}
		if (turnLeft) {
			rotationa -= rotationSpeed;
			walk = true;
		}
		if (turnRight) {
			rotationa += rotationSpeed;
			walk = true;
		}
		
		if(jump) {
			y += jumpHeight;
			sprint = false;
		}
		
		if(crouch) {
			y-= crouchHeight;
			sprint = false;
		}
		
		if(sprint) {
			walkSpeed = 3.4;
			walk = true;
		}
		
		if(!forward && !back && !left && !right && !turnLeft && !turnRight) {
			walk = false;
		}
		
		xa += (xMove * Math.cos(rotation) + zMove * Math.sin(rotation))* walkSpeed;
		za += (zMove * Math.cos(rotation) - xMove * Math.sin(rotation))* walkSpeed;
		
		x += xa;
		z += za;
		y*= 0.9;
		xa *= 0.1;
		za *= 0.1;
		rotation += rotationa;
		rotationa *= 0.8;
	}
}
