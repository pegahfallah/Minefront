package com.mine.minefront.graphics;

import java.util.Random;

import com.mine.minefront.Game;

public class Screen extends Render {
	private Render test;
	private Render3D render; //object
	
	public Screen (int width, int height) {
		
		super(width, height);
		Random random = new Random();
		render = new Render3D(width, height); //assignment
		test = new Render(256, 256);
		
		for( int i = 0; i < 256*256 ; i++) {
			test.pixels[i]= random.nextInt() * (random.nextInt(5)/4) ;
		}
	}
	
	public void render(Game game) {
		for (int i = 0; i < width * height; i ++) {
			pixels[i] = 0;
			//remove after every frame 
		}
		for (int i = 0; i < 50; i ++) {
			int animate = (int) (Math.sin((game.time+ i * 2) % 1000.0/100) * 100);
			int animate2 = (int) (Math.cos((game.time + i * 2) % 1000.0/100)* 100);
	//	draw(test, (width-256)/2 + animate, (height-256)/2 + animate2);
		}
		render.floor(game);
		render.renderDistanceLimiter();
		draw(render, 0, 0);
	}
}
