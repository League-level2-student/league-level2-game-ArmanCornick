import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BlockJump extends JPanel implements KeyListener, ActionListener {
	JFrame frame;
	Cube wave;
	ArrayList<Obstacle> obstacles = CourseGenerator.course(0);
	Timer time;
	int counter = 1;
	Random counterRandom;
	int courseCounter = 0;
	Boolean gameState = false;
	static BufferedImage gameIcon;
	int scoreLetterSpacing = 0;
	int bgX;
	static BufferedImage background;
	Boolean tutorialState = false;
	Boolean startingState = true;


	BlockJump() {
		gameIcon = loadImage("wavedashicon.png");
		background = loadImage("wavedashbg.png");
		bgX = 0;
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
			bgX-=2;
			if(bgX==-1500) {
				bgX = 0;
			}
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
			startingState = false;
			gameState = true;
			tutorialState = false;
			obstacles = CourseGenerator.course(1);
			wave.y = 500;
			courseCounter = 0;
			scoreLetterSpacing = 0;

		}
		if (e.getKeyCode() == KeyEvent.VK_T && startingState == true) {
			tutorialState = true;

		}
	}

	static BufferedImage loadImage(String imageFile) {
		try {
			return ImageIO.read(Cube.class.getResourceAsStream(imageFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
			courseCounter += 1;
		}
		if (obstacles.size() == 0) {
			counter = counterRandom.nextInt(4);
			obstacles = CourseGenerator.course(counter);
			courseCounter += 50;
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
		g.fillRect(0, 0, 3000, 1000);
		if(gameState==true) {
			
			g.drawImage(background, bgX, 0, 3000, 1000, null);
			
			for (Obstacle i : obstacles) {
				i.draw(g);
			}	
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.BOLD, 200));
			if(courseCounter>9) {
				scoreLetterSpacing = 80;
			}
			if(courseCounter>99) {
				scoreLetterSpacing = 160;
			}
			if(courseCounter>999) {
				scoreLetterSpacing = 240;
			}
			if(courseCounter>9999) {
				scoreLetterSpacing = 320;
			}

			g.drawString(String.valueOf(courseCounter), 700 - scoreLetterSpacing,150);
			wave.draw(g);
		}

		if(gameState==false) {
			if(tutorialState==false) {
				if(startingState==false) {
					menuState(g);	
				} else {
					startingState(g);
				}
			} else {
				instructionState(g);
			}		
		} 	

	}

	public void menuState(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(350, 200, 800, 600);
		g.setFont(new Font("Monospaced", Font.BOLD, 200));
		g.setColor(Color.black);
		if(courseCounter>9) {
			scoreLetterSpacing = 80;
		}
		if(courseCounter>99) {
			scoreLetterSpacing = 160;
		}
		if(courseCounter>999) {
			scoreLetterSpacing = 240;
		}
		if(courseCounter>9999) {
			scoreLetterSpacing = 320;
		}

		g.drawString(String.valueOf(courseCounter), 700 - scoreLetterSpacing,365);

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
		g.setFont(new Font("Arial", Font.BOLD, 80));
		g.drawString("Click enter to start", 400,700);
	}

	public void startingState(Graphics g) {	
		g.setColor(Color.gray);
		g.fillRect(350, 200, 800, 600);
		g.setFont(new Font("Arial", Font.BOLD, 150));
		g.setColor(Color.black);
		g.drawString("Wave Dash", 355,565);
		g.setFont(new Font("Arial", Font.BOLD, 45));
		g.setColor(Color.red);
		g.drawString("Click T for tutorial", 550,630);
		g.setColor(Color.green);
		g.setFont(new Font("Arial", Font.BOLD, 80));
		g.drawString("Click enter to start", 400,770);
		g.drawImage(gameIcon, 630, 225, 200, 200, null);
	}
}