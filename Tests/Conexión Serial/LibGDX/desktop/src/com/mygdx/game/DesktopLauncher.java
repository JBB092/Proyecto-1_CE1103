package com.mygdx.game;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.Game;
import com.mygdx.game.Dataload.GameData ;
/*
import com.mega.games.support.stub.MegaServices;
import com.mega.games.support.ui.GameUIManager;
import com.mega.games.support.ui.LibGdxGameUI;
*/
import kotlin.Unit;

public class DesktopLauncher {
	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setTitle("GameStartingKit");
		config.width = GameData._virtualWidth;
		config.height = GameData._virtualHeight;

		config.resizable = true;

		config.samples = 2;

		MegaServices services = new MegaServices();
		Game game = new Game(services);

		GameUIManager gameUI = new LibGdxGameUI(services);
		gameUI.setOnPlayAgainListener(() -> {
			game.restartGame();
			return Unit.INSTANCE;
		});
		gameUI.setOnQuitListener(() -> {
			System.exit(0);
			return Unit.INSTANCE;
		});

		game.setGameUIManager(gameUI);

		new Lwjgl3Application(game, config);
	}
}