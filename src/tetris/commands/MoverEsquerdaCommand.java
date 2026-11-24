/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris.commands;

import tetris.GameArea;

/**
 *
 * @author profb
 */
public class MoverEsquerdaCommand implements GameCommand{
    private final GameArea ga;
    
    public MoverEsquerdaCommand(GameArea ga){
        this.ga = ga;
    }

    @Override
    public void execute() {
        ga.moverBlockEsq();
    }
    
}
