package com.mygdx.game.Objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Objects.baseObjects.GameObject;
import com.mygdx.game.Objects.entities.BGController;
import com.mygdx.game.Objects.entities.DotManager;
import com.mygdx.game.Objects.entities.PlayerScoreManager;
import com.mygdx.game.Objects.entities.StageObject ;
import java.util.ArrayList;

public class GameObjectManager {
    //set singleton
    private static final GameObjectManager _myInstance = new GameObjectManager();
    private ArrayList<GameObject> objs;

    private GameObjectManager() {
        objs = new ArrayList<>();
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
    }

    public static GameObjectManager getInstance() {
        return _myInstance;
    }

    public void reset() {
        //on reset, clear the object list and just add a ball
        objs.clear();

        StageObject.getInstance().reset();
        DotManager.getInstance().reset();
        PlayerScoreManager.getInstance().reset();
    }

    public ArrayList<GameObject> getObjs() {
        return objs;
    }

    public void update(float dt) {
        //Automatically called every frame before draw function
        for (GameObject obj : objs) {
            obj.update(dt);
        }
    }

    public void draw(Batch batch) {
        BGController.getInstance().draw(batch);
        //automatically called every frame after update function
        for (GameObject obj : objs) {
            if (obj.visible) {
                obj.draw(batch);
            }
        }
        PlayerScoreManager.getInstance().draw(batch);
    }
}
