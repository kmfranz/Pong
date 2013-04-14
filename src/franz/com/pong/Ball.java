package franz.com.pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Ball {
	
	public int x, y,r;
	public Point speed = new Point(6,2);
	private Color color = Color.white;
	
	public Ball(int x, int y){
		this.x = x;
		this.y = y;
		
		r = 15;
	}
	
	public void update(){
		x+=speed.x;
		y+=speed.y;
		
		if(x < 0 || x > Display.width){
			speed.x *= -1;
		}
		if(y < 0 || y > Display.height){
			speed.y *= -1;
		}
		
		
		
	}
	
	public void render(Graphics2D g){
		g.setColor(color);
		g.fillArc(x, y, r, r, 0, 360);
	}
}
