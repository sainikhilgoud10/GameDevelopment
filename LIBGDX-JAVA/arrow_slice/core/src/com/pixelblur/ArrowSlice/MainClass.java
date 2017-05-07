package com.pixelblur.ArrowSlice;


import com.badlogic.gdx.Game;


public class MainClass extends Game{
    

    @Override
    public void create() {
            setScreen(new GameScreen(this));
    }

}
