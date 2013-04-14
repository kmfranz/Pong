package franz.com.pong;

import java.awt.Color;
import java.awt.Graphics2D;

public class Paddle {
	public double x, y;
	public final double speed = 5;
	public final int width = 15, height = 90;
	public int leftOrRight;
	
	public int direction;
	public double prevY, dy;
	
	public Color color = Color.white;
	
	public Paddle(double x, double y, int leftOrRight){
		this.x = x;
		this.y = y;
		this.leftOrRight = leftOrRight;  //indicated which paddle we are using input or computer
		
		prevY = 0;
		dy = 0;
	}
	
	public void render(Graphics2D g){
		g.setColor(color);
		g.fillRect((int)x,(int)y,width,height);
	}
	
	public void update(){
		
		dy = prevY - y;
		prevY = y;
		if(dy > 0){
			direction = 1;
			System.out.println(direction);
		}else if(dy < 0){
			direction = 2;
		}else{
			direction = 0;
		}
		
		//manage input:
		if(leftOrRight == 1){ //player controlled
			if(Display.keyboard.up && Display.keyboard.down){
				//Do nothing...
			}
			else if(Display.keyboard.up){
				y-=speed;
			}else if(Display.keyboard.down){
				y+= speed;
			}
		}
		
		//if its a computer:
		if(leftOrRight == 2){
			//we want to wait until the ball is on this half of the court to start moving
			if(Game.ball.x > Display.width / 2 && Game.ball.speed.x > 0){
				double dx = x - Game.ball.x;
				double ratio = dx / Game.ball.speed.x;
				double dy = Game.ball.speed.y * ratio;
				double contactPoint = Game.ball.y + dy;
				
				if(!(y < contactPoint && y + height > contactPoint)){
					if(y - contactPoint > 0){
						y-=speed;
					}else{
						y+= speed;
					}
				}				
			}
		}
	}
}
