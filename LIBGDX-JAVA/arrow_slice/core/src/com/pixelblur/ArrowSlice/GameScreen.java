
package com.pixelblur.ArrowSlice;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;



public class GameScreen implements Screen {
    
    Game mygame;
    Array<Sprite> spriteimages;
    private SpriteBatch batch;
    private BitmapFont bitmapFont;
    private String string;
    private StringBuilder sb;
    private long load_starttime,progress=100;
    private boolean isRight = true,isLeft = false;
    private ShapeRenderer loadbar;
    public GameScreen(Game g){
        mygame = g;
    }
    
    private void assetLoader(){
        spriteimages = new Array<Sprite>();
        bitmapFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        string = "";
        loadbar = new ShapeRenderer();
        sb = new StringBuilder(string);
        
        
            spriteimages.addAll(new Sprite(new Texture(Gdx.files.internal("arrow_blue.png"))),
                    new Sprite(new Texture(Gdx.files.internal("arrow_green.png"))),
                    new Sprite(new Texture(Gdx.files.internal("arrow_red.png"))));
            spriteimages.get(1).setPosition(Gdx.graphics.getWidth()/2-spriteimages.get(1).getWidth()/2,Gdx.graphics.getHeight()/2-spriteimages.get(1).getHeight()/2);
        
    }
    
    @Override
    public void show() {
        assetLoader();
        batch = new SpriteBatch();
        
        load_starttime = TimeUtils.nanoTime();
    }
   
    public void checkifFlipped(){
        if(isRight == true && isLeft==false){
            spriteimages.get(1).flip(true, false);
            isRight = false;
            isLeft = true;
        }
        else if(isLeft == true && isRight == false){
            spriteimages.get(1).flip(true, false);
            isLeft = false;
            isRight = true;
        }
    }
   
    @Override
    public void render(float delta) {
            Gdx.gl.glClearColor(1, 1, 1, 1);
             Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        long currentTimeStamp = TimeUtils.nanoTime();
        if (currentTimeStamp - load_starttime > TimeUtils.millisToNanos(70)) {
            load_starttime = currentTimeStamp;
            progress = progress - 1;
        }
        float progressBarWidth = (800/100) * progress;
        //if(TimeUtils.millis()>starttime+1000){
            //checkifFlipped();
            if(progress<=0)
                sb.replace(0, sb.length(), "GAMEOVER");
            /*if(counter>0){
                counter-=1;
                sb.delete(0, sb.length());
                sb.append(counter); 
            }*/
          //  starttime = TimeUtils.millis();
        //}
        loadbar.begin(ShapeType.Filled);
        if(progress>50)
            loadbar.setColor(Color.valueOf("3498db"));
        else
            loadbar.setColor(Color.RED);
        loadbar.rect(0, 10, progressBarWidth, 25);
        loadbar.end();
        batch.begin();
        bitmapFont.draw(batch, sb, 100, 100);
        spriteimages.get(1).draw(batch);
        batch.end();
        
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            if(isRight == false && isLeft==true){
                System.out.println("correct");
                if(progress<100){
                    //counter+=1;
                    progress+=5;
                }
                
                checkifFlipped();
            }else{
                progress-=8;
            }
            
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            if(isRight == true && isLeft == false){
                System.out.println("correct");
                if(progress<100){
                    //counter+=1;
                    progress+=5;
                }
                
                checkifFlipped();
            }else{
                progress-=8;
            }
            
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
        System.out.println("dispose callllled");
        bitmapFont.dispose();
        
        for(int i=0;i<=2;i++)
            spriteimages.get(i).getTexture().dispose();
        batch.dispose();
    }

    
    
}
