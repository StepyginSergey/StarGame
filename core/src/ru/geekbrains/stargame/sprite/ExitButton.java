package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.geekbrains.stargame.base.ActionListener;
import ru.geekbrains.stargame.base.TouchButton;
import ru.geekbrains.stargame.math.Rect;

public class ExitButton extends TouchButton {

    public ExitButton(TextureAtlas atlas, ActionListener actionListener, float pressScale) {
        super(atlas.findRegion("btExit"), actionListener, pressScale);
    }


    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setRight(worldBounds.getRight());
        //super.resize(worldBounds);
    }
}
