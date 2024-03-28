package com.fyp.codecasterodyssey;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Codecaster Odyssey");
		config.setWindowedMode(1280, 960);
		config.useVsync(true);
		new Lwjgl3Application(new CodecasterOdyssey(), config);
	}
}
