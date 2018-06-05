package source;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Motor extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private BatalhaNaval jogo;
	private BufferStrategy strategy;
	private Canvas canvas;
	private JogoThread atualizaTela;
	private Tabuleiro tabuleiro;
	
	public Motor(BatalhaNaval jogo) {
		this.jogo = jogo;
		this.tabuleiro = jogo.getTabuleiro();
		this.canvas = jogo.getCanvas();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Batalha Naval");
        setPreferredSize(new Dimension(jogo.getLargura(), jogo.getAltura()));
        getContentPane().setLayout(null);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices(); // pega o tamanho da tela do usuario
        Rectangle bounds = gs[gs.length-1].getDefaultConfiguration().getBounds();
        setResizable(false);
        // centraliza a janela
        setBounds(bounds.x+(bounds.width - jogo.getLargura())/2,
                bounds.y+(bounds.height - jogo.getAltura())/2,
                jogo.getLargura(),jogo.getAltura());
        
        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        canvas.setBounds(0,0,jogo.getLargura(),jogo.getAltura());
        getContentPane().add("Center", canvas);
        canvas.setIgnoreRepaint(true);
        setVisible(true);
        canvas.createBufferStrategy(3);
        strategy = canvas.getBufferStrategy();
        canvas.requestFocus();
//      Sensor de click no mouse
        clickMouse();
        mainLoop();
        
	}
	
	public void reinicio(BatalhaNaval jogo) {
		this.jogo = jogo;
		this.tabuleiro = jogo.getTabuleiro();
		this.canvas = jogo.getCanvas();
		mainLoop();
	}
	
	public void clickMouse() {
		canvas.addMouseListener(new MouseListener() {
        	// implementacao de MouseListener
			@Override
			public void mouseReleased(MouseEvent e) {
				// Controles de click no Canvas
				int x = e.getX();
		        int y = e.getY();
		        switch (jogo.getGameState()) {
		        	case 0:
		        	case 2:
		        	case 3:
					try {
						jogo.getMenuPrincipal().setClick(x, y, jogo);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        		break;
		        	case 1:
		        		// Verifica se clique foi no tabuleiro ou no menu lateral
						if (x > Menu.LARG_L && y > Menu.ALT_S) {
							// Clique no tabuleiro
							int x_pos = ((x - tabuleiro.getPosX())/tabuleiro.getCelula());
							int y_pos = (y - tabuleiro.getPosY())/tabuleiro.getCelula();
							if(y_pos < tabuleiro.getAlturaTab() && y_pos >= 0 && x_pos < tabuleiro.getLarguraTab() && x_pos>= 0) {
								// Funcao na Classe para Tabuleiro setar click
								tabuleiro.setClick(x_pos, y_pos, jogo.getRecursos()); //	x_pos vale a largura, y_pos vale a altura
							}
						} 
		        }
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		
	}
	
	public void mainLoop() {
        atualizaTela = new JogoThread(canvas,strategy,jogo);
        atualizaTela.start();
	}
	public void pararLoop(boolean a) throws InterruptedException {
		if (a == true) {
			atualizaTela.interrupt();
		}
	}
}
