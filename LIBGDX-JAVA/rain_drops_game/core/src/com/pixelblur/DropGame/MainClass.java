
package com.pixelblur.DropGame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;


public class MainClass extends Game implements ApplicationListener{
    
    @Override
    public void create() {
        setScreen(new SplashScreen(this));
        
    }
    @Override
    public void render(){
        super.render();
    }
    @Override
    public void dispose(){
        
    }
}
