package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import com.mygdx.game.Dataload.GameDataController ;
import com.mygdx.game.Dataload.GameInfra ;
import com.mygdx.game.Screens.PlayScreen ;
import com.mega.games.support.MegaGame;
import com.mega.games.support.MegaServices;

public class GameStartingKit extends MegaGame {

	private MegaServices megaServices;

	public GameStartingKit(MegaServices megaServices) {
		super(megaServices);
		this.megaServices = megaServices;
	}

	@Override
	public void create() {
		super.create();

		//setup game infra
		GameInfra.getInstance().setMegaServices(megaServices);
		GameInfra.getInstance().init();

		//start the game
		restartGame();
	}

	@Override
	public void resize(int width, int height) {
		GameInfra.getInstance().resizeScreen(width, height);
	}

	@Override
	public void forceGameOver() {
//        GameDataController.getInstance().setGameOver();
	}

	@Override
	public void restartGame() {
		if (getScreen() != null) {
			getScreen().dispose();
			setScreen(null);
		}

		//set screen to play screen
		setScreen(new PlayScreen());
	}
}
