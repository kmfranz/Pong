package franz.com.pong;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Display extends Canvas implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int width = 600;
	public static int height = width / 16 * 11;
	
	public Dimension size = new Dimension(width, height);
	public String title = "Pong!!!";
	
	public JFrame frame;
	
	public Thread gameThread;
	private boolean running;
	
	private Game game;
	public static Keyboard keyboard;
	
	public Display(){
		running = false;
		
		game = new Game();
		keyboard = new Keyboard();
		
		addKeyListener(keyboard);
		
		frame = new JFrame(title);
		frame.setPreferredSize(size);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		Display display = new Display();
		display.start();
	}

	private void start() {
		if(running){
			return;
		}
		running = true;
		gameThread = new Thread(this,"GameThread");
		gameThread.start();
	}

	public void run() {
		//fps counter
		long prevTime = System.nanoTime();
		final double nano = 1000000000.0 / 60.0;
		double dt = 0;
		while(running){
			long newTime = System.nanoTime();
			dt += (newTime - prevTime) / nano;
			prevTime = newTime;
			
			while(dt >1){
				dt--;
				update();
			}
			render();
		}
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(2);
			return;
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		game.render(g);
		g.dispose();
		bs.show();
		
	}

	private void update() {
		// TODO Auto-generated method stub
		game.update();
		keyboard.update();
	}
}
