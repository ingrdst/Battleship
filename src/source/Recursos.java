package source;

public class Recursos {
	private int fundos;
	
	public Recursos(Tabuleiro tabuleiro, Embarcacao embarcacoes) {
		int embarc[] = (embarcacoes.getEmbarcacoes());
		int qtdEmbarc = 0;
		for(int i = 0; i < 5; i++) {
			qtdEmbarc += embarc[i] * (i+1); 
		}
		int blocosTot = (tabuleiro.getLarguraTab() * tabuleiro.getAlturaTab());
		fundos = (int)(qtdEmbarc + (blocosTot - qtdEmbarc)/3);
		
	}
	
	public int getFundos() {
		return fundos;
	}
	
	public void setFundos(int fundos) {
		this.fundos = fundos;
	}
}
