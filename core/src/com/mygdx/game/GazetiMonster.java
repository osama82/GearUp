package com.mygdx.game;

/**
 * Created by Waseem on 12/6/2017.
 */

public class GazetiMonster extends Monster {



    public GazetiMonster() {
        super(Constants.MONSTER1,4,3,1,1);
    }


    @Override
    public void render(float positionX, float positionY) {
        super.render(positionX, positionY);
    }


    public void move(){

        monsterPositionY+=1;





    }
}
