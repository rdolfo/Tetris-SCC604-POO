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
public class RotacionarCommand implements GameCommand{
    private final GameArea ga;

    public RotacionarCommand(GameArea ga) {
        this.ga = ga;
    }
    
    @Override
    public void execute() {
        ga.rotacioanrBlock();
    }
}
