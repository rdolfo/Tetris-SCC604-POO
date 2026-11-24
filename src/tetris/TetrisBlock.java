package tetris;

import java.awt.Color;
import java.util.Random;

public class TetrisBlock {
    private int[][] forma;
    private Color cor;
    private int x, y;
    
    private int[][][] formas;
    private int nrotacao;
    
    private Color[] disponivelCores = {Color.green, Color.red, Color.blue, Color.yellow, Color.magenta};
        
    public TetrisBlock(int[][] forma){
        this.forma = forma; 
        
        iniFormas();
    }
    
    public void iniFormas(){
        formas = new int[4][][];
        
        for(int i = 0; i < 4; i++){
            // Matriz Transposta
            int l = forma[0].length;
            int c = forma.length;
            
            formas[i] = new int[l][c];
            
            for(int y = 0; y < l; y++){
                for(int x = 0; x < c; x++){
                    formas[i][y][x] = forma[c-x-1][y];
                }
            }
            
            forma = formas[i];
        }
    }
    
    public void spawn(int gridColunas){
        Random r = new Random();
        
        nrotacao = r.nextInt(formas.length);
        forma = formas[nrotacao];
        
        
        this.y = -getAltura();
        this.x = r.nextInt(gridColunas -getLargura());
        cor = disponivelCores[r.nextInt(disponivelCores.length)];
    }
    
    public int[][] getForma() {
        return forma;
    }

    public Color getCor() {
        return cor;
    }

    public void setForma(int[][] forma) {
        this.forma = forma;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }
    
   public int getAltura(){
       return forma.length;
   }
   
   public int getLargura(){
       return forma[0].length;
   }
   
   public int getX(){ return x; }
   
   public void setX(int nx){
       this.x = nx;
   }
   
   public int getY(){ return y; }
   
   public void setY(int ny){
       this.y = ny;
   }
   
   public void moverBaixo(){ y++; }
   
   public void moverCima(){ y--; }
   
   public void moverDir(){ x++; }
   
   public void moverEsq(){ x--; }
   
   public void rotacionar(){
       nrotacao++;
       nrotacao = nrotacao%4;
       
       forma = formas[nrotacao];
   }
   
   public int getYMargem() { return y + getAltura(); }
   
   public int getXMargemDir() { return x + getLargura();}
   
   public int getXMargemEsq() { return x; }
   
   
    
}
