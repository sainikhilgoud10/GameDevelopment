
package com.pixelblur.DropGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.pixelblur.DropGame.camera.OrthoCamera;


public class SplashScreen implements Screen{
    private final Game mygame;
    private long starttime;
    private Texture texture;
    private final SpriteBatch batch;
    private final OrthoCamera camera;
    
    
      
    public SplashScreen(Game g){
        this.mygame = g;
        batch = new SpriteBatch();
        camera  = new OrthoCamera();
        camera.setToOrtho(false,720,1280);
    }
   
    @Override
    public void show() {
        texture = new Texture(Gdx.files.internal("pixelblur_splasscreen.png"));
        starttime = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(texture, 0, 0);
        batch.end();
      
        if (TimeUtils.millis()>(starttime+5000)) {
            mygame.setScreen(new CatchDrop(mygame));
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
     }

    @Override
    public void resume() {
     }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
       
       texture.dispose();
       batch.dispose();
    }
    
}
