package com.mygdx.game.Widgets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Dataload.GameAssetManager ;
import com.mygdx.game.Dataload.GameData ;

public class HUD {
    private Label scoreLabel;

    public HUD() {
        scoreLabel = new Label("0", GameAssetManager.getInstance().scoreLabelStyle);
        scoreLabel.setAlignment(Align.center);
        scoreLabel.setSize(GameData._virtualWidth, GameAssetManager.getInstance().scoreFontSize);
        scoreLabel.setPosition(0, GameData._virtualHeight - 2 * scoreLabel.getHeight());
    }

    public void update(float dt) {
        //increase score by 1 every second
//        GameData.getInstance().score += dt;
//
//        scoreLabel.setText(Long.toString((long) GameData.getInstance().score));
    }

    public void draw(Batch batch) {
        scoreLabel.draw(batch, 1);
    }
}
