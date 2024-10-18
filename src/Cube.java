import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Cube {
	int y;
	Boolean goingUp;
	
	Cube(){
		y = 460;
		goingUp = false;
	}
	void updateMovement() {
		if(goingUp==false) {
			y+=4;
		} else {
			y-=4;
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(280,y,80,40);
	}
}
