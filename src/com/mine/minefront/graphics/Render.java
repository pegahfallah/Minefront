package com.mine.minefront.graphics;

import com.mine.minefront.Display;

public class Render {
	public final int width;
	public final int height;
	public final int[] pixels;
	
	private Display display;
	
	public Render(int width, int height) {
		this.width = width; 
		this.height = height;
		pixels = new int[width * height];
		
	}
	
	public void draw(Render render, int xOffset, int yOffset) {
		for (int y = 0; y < render.height; y++) {
			int yPix = y + yOffset;	
			if (yPix < 0 || yPix >= height){
				continue;
			}
			for (int x = 0; x < render.width; x++) {
				int xPix = x + xOffset;
				if (xPix < 0 || xPix >= width){
					continue;
				}
				int rendering = render.pixels[x + y * render.width];
				//alpha support for creating void pixels with no data
				if(rendering > 0) { //if you want to render something 
					pixels[xPix + yPix * width] = render.pixels[x + y * render.width];			
				}
			}
		}
	}
}
