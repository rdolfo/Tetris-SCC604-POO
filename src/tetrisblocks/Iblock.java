package tetrisblocks;

import tetris.TetrisBlock;

public class Iblock extends TetrisBlock
{
    
    public Iblock()
    {
        super(new int[][]{ {1,1,1,1} });
    }
    
    @Override
    public void rotacionar(){
        super.rotacionar();
        
        if(this.getLargura() == 1)
        {
            this.setX(this.getX()+1);
            this.setY(this.getY()-1);
        }
        else
        {
            this.setX(this.getX()-1);
            this.setY(this.getY()+1);   
        }
        
    }
    
}
