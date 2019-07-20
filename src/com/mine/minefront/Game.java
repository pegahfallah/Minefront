package com.mine.minefront;

import java.awt.event.KeyEvent;

import com.mine.minefront.input.Controller;

public class Game {
//handles time 
	
	public int time;
	public Controller controls;
	
	public Game() {
		controls = new Controller();
	}
	
	public void tick(boolean[] key) {
		time ++;
		//KEYBOARD INPUTS pressed or released
		boolean forward = key[KeyEvent.VK_W];
		boolean back = key[KeyEvent.VK_S];
		boolean right = key[KeyEvent.VK_D];
		boolean left = key[KeyEvent.VK_A];
		boolean jump = key[KeyEvent.VK_SPACE];
		boolean crouch = key[KeyEvent.VK_CONTROL]; 
		boolean sprint = key[KeyEvent.VK_SHIFT];
		
		controls.tick(forward, back, left, right, jump, crouch, sprint);
	}
}
