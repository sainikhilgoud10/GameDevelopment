package com.pixelblur.ArrowSlice.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pixelblur.ArrowSlice.MainClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.resizable = true;
                config.width = 800;
		new LwjglApplication(new MainClass(), config);
	}
}
