package source;

import java.awt.Canvas;

public class BatalhaNaval {
	// atributos
	private int largura; 
	private int altura;
	private static Canvas canvas;
	private static Motor motor;
	private Menu menu;
	private MenuPrincipal menuPrincipal;
	private LerArquivo arquivo;
	private int larguraTab;
	private int alturaTab;
	private int tabela[][];
	private Embarcacao embarcacoes;
	private static Tabuleiro tabuleiro;
	private Recursos recursos;
	private int gameState = 0;
	
	public static void main(String[] args) {
		comecar();
	}
	
	private static void comecar() {
		motor = new Motor(new BatalhaNaval(0));
	}
	
	public void reinicioJogo() throws InterruptedException {
		motor.pararLoop(true);
		motor.reinicio(new BatalhaNaval(0));
	}
	
	
	public BatalhaNaval(int gameState) {
		this.gameState = gameState;
		largura = 1000; 
		altura = 675;
		canvas = new Canvas();
		menuPrincipal = new MenuPrincipal(largura, altura, gameState);
		arquivo = new LerArquivo();
		larguraTab = arquivo.getLargura();
		alturaTab = arquivo.getAltura();
		tabela = arquivo.getTabela();
		embarcacoes = new Embarcacao(arquivo.getEmbarcacoes());

		tabuleiro = new Tabuleiro(larguraTab, alturaTab, tabela, embarcacoes);
		recursos = new Recursos(tabuleiro, embarcacoes);
		menu = new Menu(0,0, largura, altura, embarcacoes, recursos, arquivo);
	}
	
	public void desenhar(Tela tela) {
		switch (gameState) {
			case 0:
				menuPrincipal.desenhoMain(tela);
				break;
			case 1:
				menu.desenho(tela);
				tabuleiro.desenho(tela);
				break;
			case 2:
				menuPrincipal.desenhoGameOver(tela);
				break;
		}	
	}
	
	
	// Metodos Gets
	public int getAltura() {
		return altura;
	}
	public int getLargura() {
		return largura;
	}
	public Canvas getCanvas() {
		return canvas;
	}
	public Motor getMotor() {
		return motor;
	}
	public MenuPrincipal getMenuPrincipal() {
		return menuPrincipal;
	}
	public Menu getMenu() {
		return menu;
	}
	public  LerArquivo getLerArquivo () {
		return arquivo;
	}
	public int getLarguraTab() {
		return larguraTab;
	}
	public int getAlturaTab() {
		return alturaTab;
	}
	public int[][] getTabela(){
		return tabela;
	}
	public Embarcacao getEmbarcacoes() {
		return embarcacoes;
	}
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	public Recursos getRecursos() {
		return recursos;
	}
	public int getGameState() {
		return gameState;
	}
	public void setGameState(int gameState) {
		this.gameState = gameState;
	}
}
