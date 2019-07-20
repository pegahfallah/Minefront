package com.mine.minefront.graphics;

import com.mine.minefront.Game;
import com.mine.minefront.input.Controller;

public class Render3D extends Render{

	public double[] zBuffer; //array that takes on depth
	private double renderDistance = 5000;
	
	public Render3D(int width, int height) {
		super(width, height); 
		zBuffer = new double[width * height];
	}
		
	
	public void floor(Game game) {
		//ROTATION by using sin and cos 
		double floorPosition = 8;
		double ceilingPosition = 20;
		double forward = game.controls.z / 5.0;
		double right = game.controls.x / 5.0;
		double up = game.controls.y; //shift amplitude up when jumping
		double walkingAnimation = Math.sin(game.time / 6.0 )* 0.5;
		
		double rotation = game.controls.rotation; //rotation is animated
		double cosine = Math.cos(rotation); 
		double sine = Math.sin(rotation);
		
		
		for(int y = 0; y < height; y++) {
			double ceiling = (y - height / 2.0) / height;
			double z = (floorPosition + up)/ ceiling; //height of floor
			if (Controller.walk) {
				z = (floorPosition + up + walkingAnimation)/ ceiling; //height of floor
			}
			if(ceiling < 0) {
				z = (ceilingPosition - up)/ -ceiling; // height of ceiling 
				if (Controller.walk) {
					z = (ceilingPosition - up - walkingAnimation)/ -ceiling; //height of floor
				}
			}
			for(int x = 0; x < width; x++) {
				double depth = (x - width / 2.0) / height ;
				depth *= z;
				double xx = depth * cosine + z * sine; 
				double yy = z * cosine - depth * sine; 	//time is changing, animating the screen
				int yPix = (int) (yy + forward);
				int xPix = (int) (xx + right);
				zBuffer[x + y * width] = z;
		//<< shift left, same as multiplying a number by two to the shiftAmount-th power
		//>> shift right dividing it by 2 to the power of shiftAmount
				pixels[x + y * width] = Texture.floor.pixels[(xPix & 7) + (yPix & 7) * 8]; //assign to the xDepth which has been converted to int\
		//RENDER DISTANCE
				
				if(z > 500) {
					pixels[x + y * width] = 0;
				}
			}	
		}
	}
		//FADING	
	public void renderDistanceLimiter() {
		for(int i = 0; i < width * height; i ++) {
			int colour = pixels[i];
			int brightness = (int) (renderDistance / (zBuffer[i]));
		
			//RGB values 0-255 MIN AND MAX
			if (brightness < 0) {
				brightness = 0;
			}
			if (brightness > 255) {
				brightness = 255;
			}
			int r = (colour >> 16) & 0xff;
			int g = (colour >> 8) & 0xff;
			int b = (colour) & 0xff;
			
			r = r * brightness / 255;
			g = g * brightness / 255;
			b = b * brightness / 255;
			
			pixels[i] = r << 16 | g << 8 | b;
		}
	}
	
}

