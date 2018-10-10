package org.lq.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class ImageCodeUtil {
	
	public static void drawImageCode(String word,int width, int height, OutputStream out) throws IOException {

		
		
	
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
	
		Graphics graphics = image.getGraphics();
		
	
		
	
		graphics.setColor(new Color(255,255,255));
		graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
		
		
		
		graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		
		
		int w = width/word.length();
		for(int i=0;i<word.length();i++) {
			graphics.setColor(new Color((i*70)%255, (255+i*50)%255, (128+i*80)%255));
			
			graphics.drawString(word.substring(i, i+1),w*i+w/3, height/2);
			
		}
		
		

		graphics.dispose();
		
	
		ImageIO.write(image, "jpg", out);
		
	}
}