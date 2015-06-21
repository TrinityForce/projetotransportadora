package br.com.bomtransporte.util;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
public class MainBackground extends JDesktopPane{
    private final Image imagem;
    public MainBackground(String imagem) {
        this.imagem = new ImageIcon(imagem).getImage();
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
    }
}
