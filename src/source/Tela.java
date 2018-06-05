package source;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;

public class Tela extends Canvas{
	private static final long serialVersionUID = 1L;
	Graphics2D graphics;
	Tela (Graphics2D graphics){
		this.graphics = graphics;
	}

	public void retangulo(int x, int y, int largura, int altura, int r,int g, int b) {
        graphics.setColor(new Color(r,g,b));
        graphics.fillRect(x, y, largura, altura);
    }

	public void texto(String texto, int x, int y, int tamanho, int r, int g, int b) {
        graphics.setColor(new Color(r, g, b));
        graphics.setFont(new Font("Arial", Font.BOLD, tamanho));
        graphics.drawString(texto, x, y);
    }

	public void imagem(String arquivo, int x, int y, int largura, int altura, double rotacao) {
		ImageIcon icone = new ImageIcon(arquivo);
		Image img = icone.getImage();
		AffineTransform trans = graphics.getTransform();
		graphics.rotate(-rotacao, x + largura, y + altura);
		graphics.drawImage(img, x, y, largura,altura,null); 
		graphics.setTransform(trans);
	}
}
