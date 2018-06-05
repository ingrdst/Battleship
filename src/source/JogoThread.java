package source;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class JogoThread extends Thread{
	private BatalhaNaval jogo;
	BufferStrategy strategy;
	JogoThread(Canvas canvas, BufferStrategy strategy, BatalhaNaval jogo){
		this.strategy = strategy;
		this.jogo = jogo;
	}
	@ Override
	public void run() {
		while(!Thread.interrupted()) {
			try {
				sleep(33); 
				Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
				g.setColor(Color.black);
				g.fillRect(0,0,jogo.getLargura(),jogo.getAltura()); 
				jogo.desenhar(new Tela(g));
				strategy.show();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
