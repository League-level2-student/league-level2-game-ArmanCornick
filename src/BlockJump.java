import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BlockJump extends JPanel implements KeyListener, ActionListener {
	JFrame frame;
	Cube wave;
	ArrayList<Obstacle> obstacles = CourseGenerator.course(1);
	Timer time;
	int counter = 1;

	BlockJump() {
		time = new Timer(1000 / 60, this);
		frame = new JFrame();
		wave = new Cube();
		frame.add(this);
		this.setPreferredSize(new Dimension(1500, 1000));
		frame.setVisible(true);
		frame.pack();
		time.start();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
	}

	public static void main(String[] args) {
		new BlockJump();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		wave.updateMovement();
		updateObstacles();
		repaint();
		checkCollision();
	}

	public void checkCollision() {
		// TODO Auto-generated method stub
		for (int e = 0; e < obstacles.size(); e++) {
			if (wave.collisionBox.intersects(obstacles.get(e).collisionBox)) {
				System.out.println("dead");
				obstacles = CourseGenerator.course(1);
				wave.y = 500;
				counter = 1;
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			wave.goingUp = true;
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
			System.out.println("money");
		}
		if (obstacles.size() == 0) {
			counter++;
			obstacles = CourseGenerator.course(counter);
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
		for (Obstacle i : obstacles) {
			i.draw(g);
		}
		wave.draw(g);

	}
}
