package franz.com.pong;

import java.awt.Color;
import java.awt.Graphics2D;

public class Game {
	
	public static Ball ball;
	private Color bgColor = Color.black;
	
	private Paddle paddleLeft;
	private Paddle paddleRight;
	
	private int scoreToWin = 0; //score to win
	private int winner = -1;
	private boolean gameRunning = true;
	
	public int[] score = new int[2];
	
	public Game(){
		ball = new Ball(100,100);
		paddleLeft = new Paddle(20,20,1);
		paddleRight = new Paddle(Display.width - 20 - 15, 20, 2);
		
		score[0] = 0;
		score[1] = 0;
	}

	public void update(){
		if(!gameRunning){
			//Listen for new game
			if(Display.keyboard.anyKey){
				resetGame();
			}
		}
		if(gameRunning){
			ball.update();
			paddleLeft.update();
			paddleRight.update();

			//collision...
			if(ball.speed.x > 0){ //moving to the right
				if(ball.x > paddleRight.x - paddleRight.width - 5 && ball.y > paddleRight.y && ball.y < paddleRight.y + paddleRight.height){
					ball.speed.x *= -1;
				}
			}
			if(ball.speed.x < 0){ //moving to the right
				if(ball.x < paddleLeft.x + paddleLeft.width && ball.y > paddleLeft.y && ball.y < paddleLeft.y + paddleLeft.height){
					ball.speed.x *= -1;
					if(paddleLeft.direction == 1){ //moving up
						if(ball.speed.y < 0){
							ball.speed.y *= -1;
						}
					}
					if(paddleLeft.direction == 2){ //moving up
						if(ball.speed.y > 0){
							ball.speed.y *= -1;
						}
					}
				}
			}

			if(ball.x < 0){ //hits players wall
				score[1]++;
				resetBall();
			}
			if(ball.x > Display.width){
				score[0]++;
				resetBall();
			}

			for(int i = 0; i < score.length; i++){
				if(score[i] > scoreToWin){
					endGame(i);
				}
			}
		}
	}
	
	private void resetGame() {
		score[0] = 0;
		score[1] = 0;
		
		gameRunning = true;
		winner = -1;
		
		resetBall();
	}

	private void endGame(int winner) {
		this.winner = winner;
		gameRunning = false;
		//stop the game...
	}

	public void render(Graphics2D g){
		//render background:
		g.setColor(bgColor);
		g.fillRect(0, 0, Display.width, Display.height);
		
		g.setColor(Color.white);
		g.drawString("Score: " + score[0] + " - " + score[1], Display.width/2 - 40, 20);
		
		paddleLeft.render(g);
		paddleRight.render(g);
		ball.render(g);
		
		if(winner == 0){
			g.drawString("Player wins!!!", Display.width/2 - 20, Display.height / 3);
			g.drawString("Press Any Key To Play a New Game!", Display.width/2 - 50, Display.height / 3 + 30);
		}else if(winner == 1){
			g.drawString("Computer wins!!!", Display.width/2 - 30, Display.height / 3);
			g.drawString("Press Any Key To Play a New Game!", Display.width/2 - 50, Display.height / 3 + 30);
		}
	}
	public void resetBall(){
		ball.x = Display.width/2;
		ball.y = Display.height/2;
	}
}
