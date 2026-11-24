package tetris;

import javax.swing.JOptionPane;

public class Tetris {
    
    private static GameForm gf;
    private static TelaInicialForm inicialf;
    private static PlacarForm pf; 
    
    public static void start(){
        gf.setVisible(true);
        gf.startGame(); // Crio uma nova thread para o jogo
    }
    
    public static void gameOver(int pont){
        String nomePlayer = JOptionPane.showInputDialog("Game Over!\nInisira seu nome.");
        gf.setVisible(false);
        pf.addPlayer(nomePlayer, pont);
    }
    
    public static void mostrarPlacar(){
        pf.setVisible(true);
    }
    
    public static void mostrarInicial(){
        inicialf.setVisible(true);
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() { // Garante que a UI do prgrama seja rodada inteiramente numa threead diferente 
            public void run() {
                gf = new GameForm();
                inicialf = new TelaInicialForm();
                pf = new PlacarForm();
                
                inicialf.setVisible(true);
                
            }
        });
        
        
    }
    
}
