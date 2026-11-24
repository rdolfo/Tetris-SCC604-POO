/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetrisblocks;

import java.util.Random;
import tetris.TetrisBlock;

/**
 *
 * @author profb
 */
public class TetrisBlockFactory {
    private final Random random = new Random();
    /*
    No GameArea eu tava misturando a criação de blocos com a logica de preeencher a grid
    Eu crio a blockFactory para tirar a responsabilidade de sorteio do GameArea criação de objetos com a lógica de grid, colisão e desenho.
    
    GameArea: fica focada no tabuleiro
    */
    public TetrisBlock createRandomBlock() {
        switch (random.nextInt(7)) {
            case 0: return new Iblock();
            case 1: return new Jblock();
            case 2: return new Lblock();
            case 3: return new Oblock();
            case 4: return new Sblock();
            case 5: return new Tblock();
            default: return new Zblock();
        }
    }
}
