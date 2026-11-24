package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import tetrisblocks.*;

// A classe de JPanel tem varios métodos úteis para a gente desenhar o jogo no Form

public class GameArea extends JPanel
{
    private int gridLinhas;
    private int gridColunas;
    private int tamCelula;
    private Color[][] background;
    
    private TetrisBlock block;
    private TetrisBlock blocks[];
    
    public GameArea(JPanel placeholder, int ncolunas){
    this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
        
        gridColunas = ncolunas;
        tamCelula = this.getBounds().width / gridColunas;
        gridLinhas = this.getBounds().height / tamCelula;
        
                
        blocks = new TetrisBlock[]{ new Iblock(),
                                    new Jblock(),
                                    new Lblock(),
                                    new Oblock(),
                                    new Sblock(),
                                    new Tblock(),
                                    new Zblock()};
    }
    
    public void iniBackground()
    {
        background = new Color[gridLinhas][gridColunas];
    }
    
    
    public void spawnBlock(){
        Random r = new Random();
        block = blocks[r.nextInt(blocks.length)];
        block.spawn(gridColunas);
    }
    
    public boolean isBlockForaLimites()
    {
        if(block.getY() < 0 )
        {
            block = null;
            return true;
        }
        return false;
    }
    
    public boolean moverBlockBaixo(){
        if(block == null) return false;
        if(ChecaMargemBaixo()) {
            return false;
        }
        block.moverBaixo();
        repaint(); // Executa "paint component"
        
        return true;
    }
    
    public boolean moverBlockDir(){
        if(block == null) return false;
        if(ChecaMargemDir()){
            return false;
        }
        
        block.moverDir();
        repaint();
        
        return true;
    }
    
    public boolean moverBlockEsq(){
        if(block == null) return false;
        if(ChecaMargemEsq()){
            return false;
        }
        block.moverEsq();
        repaint();
        
        return true;
    }
    
    public boolean dropBlock(){
        if(block == null) return false;  
        while(!ChecaMargemBaixo()){
            block.moverBaixo();
            
        }
        repaint();
        return true;
    }
    
    public boolean rotacioanrBlock(){
        if(block == null) return false;
        if(ChecaMargemBaixo()){
            return false;
        }
        block.rotacionar();
        
        if(block.getXMargemEsq() < 0) block.setX(0);
        if(block.getXMargemDir() >= gridColunas) block.setX(gridColunas-block.getLargura());
        if(block.getYMargem() >= gridLinhas) block.setY(gridLinhas - block.getAltura());
        repaint();
        return true;     
    }
    
    public void moverBlockBackground(){
        int[][] forma = block.getForma();
        int h = block.getAltura();
        int w = block.getLargura();
        
        Color c = block.getCor();
        
        int xPos = block.getX();
        int yPos = block.getY();
        
        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                if(forma[i][j]==1) background[yPos + i][xPos + j] = c;
            }
        }
    }
    
    private boolean ChecaMargemBaixo(){
        if(block.getYMargem()>=gridLinhas){
            return true;
        }
        else{
            int[][] forma  = block.getForma();
            int h = block.getAltura();
            int w = block.getLargura();
            
            for(int col = 0; col < w; col++){
                for(int lin = h-1; lin >=0; lin--){
                    if(forma[lin][col] != 0){
                        int x = block.getX() + col;
                        int y = block.getY() + lin + 1; // Olha quem ta logo em baixo
                        if(y<0) continue; // Em cima entao nao tem nada
                        if(background[y][x] != null ) return true;
                        break;    
                    }
                    
                }
            }
            
            
            return false;
        }     
    }
    
    private boolean ChecaMargemDir(){
        if(block.getXMargemDir()>=gridColunas){
            return true;
        }
        else{
            int[][] forma  = block.getForma();
            int h = block.getAltura();
            int w = block.getLargura();
            
            for(int lin = 0; lin < h; lin++){
                for(int col = w-1; col >=0; col--){
                    if(forma[lin][col] != 0){
                        int x = block.getX() + col+1;
                        int y = block.getY() + lin; // Olha quem ta logo em baixo
                        if(y<0) continue; // Em cima entao nao tem nada
                        if(background[y][x] != null ) return true;
                        break;    
                    }
                    
                }
            }
            
            return false;
        }      
    }
    
    private boolean ChecaMargemEsq(){
        if(block.getXMargemEsq()<=0){
            return true;
        }
        else{
            int[][] forma  = block.getForma();
            int h = block.getAltura();
            int w = block.getLargura();
            
            for(int lin = 0; lin < h; lin++){
                for(int col = 0; col < w; col++){
                    if(forma[lin][col] != 0){
                        int x = block.getX() + col-1;
                        int y = block.getY() + lin; // Olha quem ta logo em baixo
                        if(y<0) continue; // Em cima entao nao tem nada
                        if(background[y][x] != null ) return true;
                        break;    
                    }
                    
                }
            }
            
            return false;
        }
    }
    
    public int limpaLinhas()
    {
        boolean LinhaCompleta;
        int LinhasLimpas = 0;
        
        for(int i = gridLinhas-1; i>=0; i--)
        {
            LinhaCompleta = true;
            for(int j=0; j<gridColunas; j++)
            {
                if(background[i][j] == null) {
                    LinhaCompleta = false;
                    break;
                }
            }
            
            if(LinhaCompleta)
            {
                LinhasLimpas += 10;
                limpaLinha(i);
                desceLinha(i);
                limpaLinha(0);
                
                i++;
                
                repaint();
            }
            
        }
        return LinhasLimpas;
    }
    
    private void limpaLinha(int i){
        for(int j=0; j<gridColunas; j++)
        {
            background[i][j] = null;
        }
    }
    
    private void desceLinha(int i){
        for(int lin = i; lin>0; lin--)
        {
            for(int col = 0; col < gridColunas; col++)
            {
                background[lin][col] = background[lin-1][col];
            }
        }
    }
    
    private void drawBlock(Graphics g){
        int h = block.getAltura();
        int w = block.getLargura();
        Color c = block.getCor();
        int[][] forma = block.getForma();
        
        for(int i = 0; i < h; i++){
            for(int j=0; j < w; j++){
                if(forma[i][j]==1){
                    int x = block.getX()+j;
                    int y = block.getY()+i;
                    
                    drawGridQuad(g, c, x, y);
                }
            }
        }
    }
    
    private void drawBackground(Graphics g){
        Color color;
        
        for(int i=0; i < gridLinhas; i++){
            for(int j=0; j < gridColunas; j++){
                color = background[i][j];
                
                if(color != null){
                    drawGridQuad(g, color, j, i);
                }
            }
        }
    }
    
    private void drawGridQuad(Graphics g, Color c, int x, int y){
        g.setColor(c);
        g.fillRect(x*tamCelula, y*tamCelula, tamCelula, tamCelula);
        g.setColor(Color.black);
        g.drawRect(x*tamCelula, y*tamCelula, tamCelula, tamCelula);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        drawBackground(g);
        drawBlock(g);
    }
}
