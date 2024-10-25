import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BlockJump extends JPanel implements KeyListener, ActionListener {
	JFrame frame;
	Cube wave;
	Timer time;

	BlockJump(){
		time = new Timer(1000/60, this);
		frame = new JFrame();
		wave = new Cube();
		frame.add(this);
		this.setPreferredSize(new Dimension(1500,1000));
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
		wave.updateObstacles();
		repaint();
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			wave.goingUp = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		wave.goingUp = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.cyan.darker());
		g.fillRect(0,0,1500,1000);
		wave.draw(g);
	
	}
}
