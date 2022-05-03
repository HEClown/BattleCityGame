package com.battlecity.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Map {

    private TextureRegion texture;

    public static final int SIZE_X = 32;
    public static final int SIZE_Y = 16;
    public static final int CELL_SIZE = 40;

    public Map(TextureAtlas atlas) {
        this.texture = atlas.findRegion("ConcreteTile");
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                batch.draw(texture, i * CELL_SIZE, j * CELL_SIZE);
            }
        }
    }

}
