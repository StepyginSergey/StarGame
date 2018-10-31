package ru.geekbrains.stargame.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TouchButton extends Sprite {

    private int pointer;
    private boolean pressed;
    private float pressScale;
    private ActionListener actionListener;


    public TouchButton(TextureRegion region, ActionListener actionListener, float pressScale) {
        super(region);
        this.pressScale = pressScale;
        this.pressed = false;
        this.actionListener = actionListener;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (pressed  || !isMe(touch)){
            return false;
        }
        this.pointer = pointer;
        scale = pressScale;
        this.pressed = true;

        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if(this.pointer != pointer || !pressed){
            return false;
        }

        if(isMe(touch)){
            actionListener.actionPerformed(this);
            return true;
        }
        this.pressed = false;
        this.scale = 1f;

        return false;
    }
}
