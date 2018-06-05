package source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class LerArquivo {
	private int larguraTab;
	private int alturaTab;
	private int tabela[][];
	private int embarcacoes[];
	private int numArquivo;
	
	public LerArquivo() {
		File file = new File("maps/");
		int count = file.listFiles().length;
		Random gerador = new Random();
		numArquivo = gerador.nextInt(count)+1;
		String caminho = "maps/map_"+numArquivo+".txt";
		try {
			FileReader arq = new FileReader(caminho);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = "";
			try {
				// ler ate fim de arquivo
				while((linha = lerArq.readLine()) != null) {
					// leitura do numero de linhas e colunas da tabela
					if(linha.contains("# width height")) {
						String aux = lerArq.readLine();
						larguraTab = Integer.parseInt(aux.substring(0 , aux.indexOf(" ")));
						alturaTab = Integer.parseInt(aux.substring(aux.indexOf(" ")+1));//substring do espaco ate o fim da linha
						tabela = new int[alturaTab][larguraTab];// tamanho da tabela
					}
					
					if(linha.contains("# matrix")) {
						for(int i = 0; i < alturaTab; i++) {
							String aux = lerArq.readLine();
							for(int j = 0; j < larguraTab; j++) {
								tabela[i][j] = Integer.parseInt(aux.substring(j, j+1));
							}
						}
					}
					
					if(linha.contains("# number of boats of each size")) {
						embarcacoes = new int[5]; 
						for (int i = 0; i < 5; i++) {
							String aux = lerArq.readLine();
							embarcacoes[i] = Integer.parseInt(aux.substring(aux.indexOf(" ")+1));
						}
					}
				}
			}catch(IOException e){
				e.printStackTrace();
				System.out.println("Erro ao ler arquivo");
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Erro ao ler arquivo");
		}
		file.exists();
	}
	
	public int getLargura() {
		return larguraTab;
	}
	
	public int getAltura() {
		return alturaTab;
	}
	
	public int[][] getTabela() {
		return tabela;
	}
	
	public int[] getEmbarcacoes() {
		return embarcacoes;
	}
	
	public int getNumArquivo() {
		return numArquivo;
	}
}
