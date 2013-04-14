package franz.com.pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	public boolean[] keys = new boolean[120];
	public boolean up, down, anyKey;
	
	public void update(){
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		keys[event.getKeyCode()] = true;
		anyKey = true;
	}

	@Override
	public void keyReleased(KeyEvent event) {
		keys[event.getKeyCode()] = false;
		anyKey = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}
