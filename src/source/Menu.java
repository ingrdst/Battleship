package source;

public class Menu {
		
	public static final int ALT_S = 100;
	public static final int LARG_L = 10;
	
	public Menu(int x, int y, int larg, int alt, Embarcacao embarcacoes, Recursos recursos,	LerArquivo arquivo){
	}
	public void desenho(Tela tela) {
		tela.texto("BATALHA NAVAL", 200, 80, 70, 250,250,250);
		tela.imagem("images/Display.png",850, 400, 120, 250,0);
		
	}
	
}
