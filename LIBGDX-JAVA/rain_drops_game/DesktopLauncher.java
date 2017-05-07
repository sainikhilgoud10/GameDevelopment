package com.pixelblur.DropGame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pixelblur.DropGame.CatchDrop;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = "Drop";
                config.width = 800;
                config.height = 480;
                config.resizable = false;
		new LwjglApplication(new CatchDrop(), config);
	}
}
