package com.mygdx.game.Objects.baseObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Dataload.GameAssetManager;

public class Circle extends GameObject {
    //Member variables
    private TextureAtlas.AtlasRegion currReg;

    private float rad;

    public Circle(float radius, Color color, boolean filled) {
        currReg = filled ? GameAssetManager.getInstance().circle : GameAssetManager.getInstance().circleOutline;
        setRadius(radius);
        setColor(color);
    }

    public Circle(float radius, Color color) {
        this(radius, color, true);
    }

    public float getRadius() {
        return rad;
    }

    public void setRadius(float rad) {
        this.rad = rad;
    }

    @Override
    public void setSize(float sizeX, float sizeY) {
        throw new RuntimeException("Set size of a circle using setRadius(float rad)");
    }

    @Override
    public Vector2 getSize() {
        throw new RuntimeException("Get size of a circle using getRadius");
    }

    @Override
    public void onTouchDown(float x, float y) {

    }

    @Override
    public void onTouchUp(float x, float y) {

    }

    @Override
    public void onTouchDragged(float x, float y) {

    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void draw(Batch batch) {
        batch.setColor(getColor());
        batch.draw(currReg, getPos().x - rad, getPos().y - rad, 2 * rad, 2 * rad);
        batch.setColor(new Color(1, 1, 1, 1));
    }
}
