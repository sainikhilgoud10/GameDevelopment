package com.pixelblur.DropGame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.pixelblur.DropGame.camera.OrthoCamera;
import java.util.Iterator;


public class CatchDrop implements Screen   {
	float delta;
        private Texture DROPIMAGE;
        private Texture BUCKETIMAGE;
        private Sound DROPSOUND;
        private Music RAINSOUND;
        private OrthoCamera camera;
        private SpriteBatch batch;
        private Rectangle bucket;
        private Vector3 TOUCHPOS;
        private Array<Rectangle> RAINDROPS;
        private long LASTDROPTIME;
        public static int WIDTH =800,HEIGHT = 480;
   
      
        public CatchDrop(Game game){
            
        }
	
	public void create () {
   
            DROPIMAGE = new Texture(Gdx.files.internal("droplet.png"));
            BUCKETIMAGE = new Texture(Gdx.files.internal("bucket.png"));
            DROPSOUND = Gdx.audio.newSound(Gdx.files.internal("water_drop.mp3"));
            RAINSOUND = Gdx.audio.newMusic(Gdx.files.internal("rain_sound.mp3"));
            
            camera = new OrthoCamera();
            camera.setToOrtho(false,WIDTH,HEIGHT);
            batch = new SpriteBatch();
            
            bucket = new Rectangle();
            bucket.x = 800/2 - 480/2;
            bucket.y = 20;
            bucket.width = 64;
            bucket.height = 64;
            
            RAINDROPS = new Array<Rectangle>();
            spawnRainDrops();
            
            RAINSOUND.setLooping(true);
            RAINSOUND.play();
            
        }
        
        public void spawnRainDrops(){
            Rectangle RAINDROP= new Rectangle();
            RAINDROP.x = MathUtils.random(0,800-64);
            RAINDROP.y = 480;
            RAINDROP.width = 64;RAINDROP.height = 64;
            RAINDROPS.add(RAINDROP);
            LASTDROPTIME = TimeUtils.nanoTime();
        }
        
        

    @Override
    public void show() {
        create();
    }

    @Override
    public void render(float delta) {
                
                Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                camera.update();
                batch.setProjectionMatrix(camera.combined);
                batch.begin();
                    batch.draw(BUCKETIMAGE, bucket.x, bucket.y);
                    for(Rectangle raindrop:RAINDROPS){
                        batch.draw(DROPIMAGE,raindrop.x,raindrop.y);
                    }
                batch.end();
                if(TimeUtils.nanoTime() - LASTDROPTIME > 1000000000)
                    spawnRainDrops();
                Iterator<Rectangle> iter = RAINDROPS.iterator();
                while(iter.hasNext()){
                    Rectangle raindrop = iter.next();
                    raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
                    if(raindrop.y + 64 <0 ){
                        iter.remove();
                        if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Vibrator)){
                            Gdx.input.vibrate(100);
                           
                        }
                    }
                    if(raindrop.overlaps(bucket)){
                        DROPSOUND.play();
                        iter.remove();
                    }
                }
               
                float accelY = Gdx.input.getAccelerometerY();
                  
                      if(accelY<-1)      
                        bucket.x -= 9;
                      if(accelY>1)
                          bucket.x += 9;
                 
                if(Gdx.input.isTouched()){
                    TOUCHPOS = new Vector3();
                    TOUCHPOS.set(Gdx.input.getX(),Gdx.input.getY(),0);
                    camera.unproject(TOUCHPOS);
                    bucket.x = TOUCHPOS.x - 64/2;
                }
                if(bucket.x<0)
                    bucket.x = 0;
                if(bucket.x > 800-64)
                    bucket.x = 800-64;
    }

    @Override
    public void resize(int width, int height) {
     }

    @Override
    public void pause() {
        dispose();
    }

    @Override
    public void resume() {
     }

    @Override
    public void hide() {
        
    }
    @Override
        public void dispose(){
            DROPIMAGE.dispose();
            BUCKETIMAGE.dispose();
            DROPSOUND.dispose();
            RAINSOUND.dispose();
            batch.dispose();
            
        }
   
}
