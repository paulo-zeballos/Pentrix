package view.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import model.game.PentrixGame;
import view.pieces.CustomPiece;
import view.pieces.Invertable;

public class KeyListenerPentrix implements KeyListener{

	public JPanel boardPnl;
	public CustomPiece current;
	public PentrixGame game;
	
	public KeyListenerPentrix(JPanel boardPnl, CustomPiece current, PentrixGame game){
		this.current= current;
		this.boardPnl = boardPnl;
		this.game = game;
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode()==KeyEvent.VK_PAGE_DOWN ){
			if (!game.detectRotAntiClkCrash()){
				System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
				//repaint(current.x,current.y,current.width,current.height);
				current.rotateAntiClock();
				System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
				//repaint(current.x,current.y,current.width+1,current.height+1);
				boardPnl.repaint();
			}
		}
		
		if (event.getKeyCode()==KeyEvent.VK_PAGE_UP ){
			if (!game.detectRotClkCrash()){
				System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
				//repaint(current.x,current.y,current.width,current.height);
				current.rotateClock();
				System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
				//repaint(current.x,current.y,current.width+1,current.height+1);
				boardPnl.repaint();
			}
		}
		if (event.getKeyCode()==KeyEvent.VK_UP ){
			
			if (current instanceof Invertable){
				System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
				//repaint(current.x,current.y,current.width,current.height);
				((Invertable)current).invertPiece();
				System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
				//repaint(current.x,current.y,current.width+1,current.height+1);
				boardPnl.repaint();
			}
		}	
		if (event.getKeyCode()==KeyEvent.VK_DOWN ){
			if (game.outOfBottomPnl() || game.detectDwnCrash()){
				game.checkGameStatus();
			}else{
				System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
				boardPnl.repaint(current.x,current.y,current.width,current.height);
				current.moveDown();
				System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
				boardPnl.repaint(current.x,current.y,current.width+1,current.height+1);
	
			}
		}
		if (event.getKeyCode()==KeyEvent.VK_LEFT ){
			if (game.outOfLeftPnl() || game.detectLeftCrash()){
			}else{
				System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
				boardPnl.repaint(current.x,current.y,current.width+1,current.height+1);
				current.moveLeft();
				System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
				boardPnl.repaint(current.x,current.y,current.width+1,current.height+1);
			}	
		}
		if (event.getKeyCode()==KeyEvent.VK_RIGHT ){
			if (game.outOfRightPnl() || game.detectRghtCrash()){
				}else{
				System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
				boardPnl.repaint(current.x,current.y,current.width+1,current.height+1);
				current.moveRight();
				System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
				boardPnl.repaint(current.x,current.y,current.width+1,current.height+1);
			}
		}	
		
	}

}
