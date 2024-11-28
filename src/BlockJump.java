import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BlockJump extends JPanel implements KeyListener, ActionListener {
	JFrame frame;
	Cube wave;
	ArrayList<Obstacle> obstacles = CourseGenerator.course(1);
	Timer time;
	int counter = 1;
	Random counterRandom;
	int courseCounter = 0;
	Boolean gameState = true;


	BlockJump() {
		counterRandom = new Random();
		time = new Timer(1000 / 60, this);
		frame = new JFrame();
		wave = new Cube();
		frame.add(this);
		this.setPreferredSize(new Dimension(1500, 1000));
		frame.pack();
		time.start();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);	
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new BlockJump();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
		if(gameState==true) {
			wave.updateMovement();
			updateObstacles();
			checkCollision();
		}
	}

	public void checkCollision() {
		// TODO Auto-generated method stub
		if(wave.y > 940 || wave.y < -65){
			gameState = false;
		}

		for (int e = 0; e < obstacles.size(); e++) {
			if (wave.collisionBox.intersects(obstacles.get(e).collisionBox)) {
				gameState = false;
				
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			wave.goingUp = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER && gameState == false) {
			gameState = true;
			obstacles = CourseGenerator.course(1);
			wave.y = 500;
			courseCounter = 0;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		wave.goingUp = false;
	}

	void updateObstacles() {
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).update();
		}
		if (obstacles.size() > 0 && obstacles.get(0).isActive == false) {
			obstacles.remove(0);
		}
		if (obstacles.size() == 0) {
			counter = counterRandom.nextInt(4);
			obstacles = CourseGenerator.course(counter);
			courseCounter += 1;
			System.out.println(courseCounter);
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.cyan.darker());
		g.fillRect(0, 0, 1500, 1000);
		if(gameState==true) {
			for (Obstacle i : obstacles) {
				i.draw(g);
			}	
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.BOLD, 200));
			g.drawString(String.valueOf(courseCounter), 700,150);
			wave.draw(g);
		}

		if(gameState==false) {
			menuState(g);
		}

	}

	public void menuState(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(350, 200, 800, 600);
		g.setFont(new Font("Arial", Font.BOLD, 200));
		g.setColor(Color.black);
		g.drawString(String.valueOf(courseCounter), 700,365);
		g.setFont(new Font("Arial", Font.BOLD, 120));
		g.drawString("Final Score", 430,500);
		g.setFont(new Font("Arial", Font.BOLD, 60));
		g.drawString("Click ", 350,650);
		g.drawString(" To Restart ", 800,650);
		g.setColor(Color.green);
		g.setFont(new Font("Arial", Font.BOLD, 80));
		g.drawString("ENTER", 520,660);
	}
	
	public void instructionState(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(350, 200, 800, 600);
		g.setFont(new Font("Arial", Font.BOLD, 150));
		g.setColor(Color.black);
		g.drawString("TUTORIAL", 380,365);
		g.setFont(new Font("Arial", Font.BOLD, 45));
		g.drawString("Use the arrow key to go up/down", 390,470);
		g.setColor(Color.red);
		g.drawString("Hitting obstacles = restart", 450,560);
		g.setColor(Color.green);
		g.setFont(new Font("Arial", Font.BOLD, 100));
		g.drawString("Map is Infinite", 400,700);
	}
}

