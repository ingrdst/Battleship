package source;


public class Tabuleiro {
	private int alturaTab;
	private int larguraTab;
	private int tabela[][];
	private int matrizClick[][];
	private int posX; 
	private int posY; 
	private int celula = 40; 
	private boolean animation[][];
	private int encontrado[][];
	private int animation_counter = 0;
	private boolean animationClick = false;
	private int fundoBloco[][];
	
	private final int ESP = 1;
	
	public Tabuleiro(int larguraTab, int alturaTab, int[][] tabela, Embarcacao embarcacoes){
		this.alturaTab = alturaTab;
		this.larguraTab = larguraTab;
		this.tabela = tabela;
		this.matrizClick = new int[this.alturaTab][this.larguraTab];
		this.animation = new boolean[this.alturaTab][this.larguraTab];
		this.fundoBloco = new int[this.alturaTab][this.larguraTab];
		this.encontrado = new int[this.alturaTab][this.larguraTab];
		this.celula = (tamanhoCelula()-1);
		this.posX = (int)((1000 - (larguraTab * celula) - ESP * larguraTab + Menu.LARG_L)/2);
		this.posY = (int)((645 - (alturaTab * celula) - ESP * alturaTab + Menu.ALT_S)/2);
	}
	
	private int tamanhoCelula() {
		if(larguraTab > alturaTab && ((1000 - Menu.LARG_L)/larguraTab * alturaTab) < (645 - Menu.ALT_S)){
			return (int)Math.floor((1000 - Menu.LARG_L)/ larguraTab);
		}else {
			return (int)Math.floor((645 - Menu.ALT_S)/ alturaTab);
		}
	}
	
	public void desenho(Tela tela) {
		if(animation_counter < 23 && animationClick == true) {
    		animation_counter ++;
		}else{
			animation_counter = 0;
			animationClick = false;
		}
		for (int i = 0; i < alturaTab; i++) {
			for(int j = 0; j < larguraTab; j++) {
				tela.retangulo(j*celula + posX + j*ESP, i*celula + posY + i*ESP, celula, celula, 230,232,250);
				
				if(animation[i][j] == true && fundoBloco[i][j] == 1) {
					tela.imagem("imagens/Flags/flagAcerto.png", j*celula + posX + j*ESP, 
							i*celula + posY + i*ESP, celula, celula, 0);
				}
				
				if(animation[i][j] == true && fundoBloco[i][j] == 2) {
					tela.retangulo(j*celula + posX + j*ESP, i*celula + posY + i*ESP, celula, celula, 50,153,204);
					
				}
				
				if(animation[i][j] == true && fundoBloco[i][j] == 3) {
					switch (encontrado[i][j]) {
						case 1:
							tela.imagem("imagens/Flags/ShipPatrolHull_Cut1.png", j*celula + posX + j*ESP, 
									i*celula + posY + i*ESP, celula, celula, 0);
							break;
					}
					
				}
				
				if(matrizClick[i][j] == 1 &&  tabela[i][j] != 0) {
					tela.imagem("images/fogo.png", 
							j*celula + posX + j*ESP, i*celula + posY + i*ESP, celula, celula ,0);
					if(animation_counter >= 23) {
						animation[i][j] = true;
						if(fundoBloco[i][j] != 3) {
							fundoBloco[i][j] = 1;
						}
					}
				}
					// animacao quando erra:
				if(matrizClick[i][j] == 1 && tabela[i][j] == 0) {
					tela.imagem("images/agua.png", 
						j*celula + posX + j*ESP, i*celula + posY + i*ESP, celula, celula ,0);
					if(animation_counter >= 23) {
						animation[i][j] = true;
						fundoBloco[i][j] = 2;
					}
				
				}
			}
		}
	}
	
	
	
	public int getLarguraTab() {
		return larguraTab;
	}
	public int getAlturaTab() {
		return alturaTab;
	}
	public int[][] getTabela() {
		return tabela;
	}
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	public int getCelula() {
		return celula;
	}
	
	public void setClick(int x, int y, Recursos recursos) {
		if(matrizClick[y][x] != 1 && animationClick == false) {
			matrizClick[y][x] = 1;
			animationClick = true;
			int atual = recursos.getFundos() - 1;
			recursos.setFundos(atual);
		}
	}
	
}
