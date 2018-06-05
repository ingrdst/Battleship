package source;

import javax.swing.JButton;

/** Classe com o menu Principal*/

public class MenuPrincipal {
	private JButton btnStart;
	private int posButtonX;
	private int posButtonY;
	private int largButton;
	private int altButton;
	private boolean gameWin = false;
	private boolean gameOver = false;
	
	public MenuPrincipal(int largura, int altura, int gameState){
		btnStart = new JButton("");
		btnStart.setBounds(0, 0, 200, 200);
		posButtonX = 0;
		posButtonY = 0;
		largButton = 1010;
		altButton = 1010;
	}
	
	public void desenhoMain(Tela tela) {
		tela.imagem("images/StartGame.gif",posButtonX, posButtonY, largButton, altButton, 0);
		tela.texto("JOGAR", 780, 620, 30, 250,250,250);
		tela.texto("BATALHA NAVAL", 200, 100, 70, 0, 0, 0);
		
	}
	
	public void desenhoGameOver(Tela tela) {
		desenhoMain(tela);
		tela.imagem("images/GameOver.jpeg",posButtonX, posButtonY, largButton, altButton, 0);
	}
	
	
	public void setClick(int x, int y, BatalhaNaval jogo) throws InterruptedException {
		// verifica posicao de click
		if(x >= posButtonX && x <= posButtonX + largButton && y >= posButtonY && y <= posButtonY + altButton) {
			// inicio de jogo
			if (gameWin == false && gameOver == false) {
				jogo.setGameState(1);
			}else {
				//Reinicio
				jogo.setGameState(0);
			}
			
		}
	}
}
