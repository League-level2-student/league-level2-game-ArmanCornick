import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
public class Cube extends GameObject {
	Boolean goingUp;
	static BufferedImage waveImage;
	int angle = 45;
	ArrayList<Point> points;
	
	static {
		loadImage("wave.png");
	}
	
	Cube(){	
		super(280,460,70,64);
		goingUp = false;
		points = new ArrayList<Point>();
	}
	void updateMovement() {
		if(goingUp==false) {
			angle = 45;
			y+=8;
		} else {
			angle = -45;
			y-=8;
		}
		points.add(new Point (x+50,y+height/2));
		updateTrail();
		collisionBox.setBounds(x+width/3, y+height/3, width/3, height/3);
	}

	void updateTrail(){
		for(int i=0;i<points.size();i++) {
			points.get(i).x-=8;
		}
		if(points.size()>0 && points.get(0).x<-30) {
			points.remove(0);
		}
	}
	
	static void loadImage(String imageFile) {
		try {
		waveImage = ImageIO.read(Cube.class.getResourceAsStream(imageFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
		for(int i=0;i<points.size();i++) {
			g.fillRect((int)points.get(i).getX(), (int)points.get(i).getY(), 20,4);
		}
		Graphics2D g1 = (Graphics2D)g;
		double radAngle = Math.toRadians(angle);
		g1.rotate(radAngle, x+width/2, y+height/2);
		g.setColor(Color.black);
		g.drawImage(waveImage,280,y,width,height, null);
		g.setColor(Color.red);
		g1.rotate(-radAngle, x+width/2, y+height/2);

	}
}
