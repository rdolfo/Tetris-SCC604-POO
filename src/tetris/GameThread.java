package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

// Vamos definir essa Thread para conseguir mover o bloco na interface grafica enquanto ela roda em paralelo captando as entradas
public class GameThread extends Thread
{
    private GameArea ga;
    private GameForm gf;
    private int pontuacao;
    private int nivel = 1;
    private int PontPorNivel = 30;
    
    private int pausa = 1000;
    private int velPorNivel = 100;
    
    public GameThread(GameArea ga, GameForm gf){
        this.ga = ga;
        this.gf = gf;
        
        gf.atualizaNivel(nivel);
        gf.atualizaPont(pontuacao);
    }
    
    @Override
    public void run(){
        
        while(true){
            ga.spawnBlock();
            
            while(ga.moverBlockBaixo() == true){
                try {
                    Thread.sleep(pausa);
                } catch (InterruptedException ex) {
                    return;
                }
            }
            
            if(ga.isBlockForaLimites())
            {
                Tetris.gameOver(pontuacao);
                break;
            }
            
            ga.moverBlockBackground();
            pontuacao += ga.limpaLinhas();
            gf.atualizaPont(pontuacao);
            
            int lvl = pontuacao / PontPorNivel + 1;
            if(lvl > nivel)
            {
                nivel = lvl;
                gf.atualizaNivel(nivel);
                pausa -= velPorNivel;
            }
            
            // Todo vez que essa loop apaga spawno um bloco novo, preciso salvar o bloco antigo para carregar ele
        }   
    }
}
