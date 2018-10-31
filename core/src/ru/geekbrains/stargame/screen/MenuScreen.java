package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.ActionListener;
import ru.geekbrains.stargame.base.Base2DScreen;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.sprite.Background;
import ru.geekbrains.stargame.sprite.ExitButton;
import ru.geekbrains.stargame.sprite.PlayButton;
import ru.geekbrains.stargame.sprite.Star;

public class MenuScreen extends Base2DScreen implements ActionListener {

    private static final int STAR_COUNT = 256;

    private static final float PRESS_SCALE = 0.9f;
    private static final float EXIT_BUTTON_HEIGHT = 0.15f;
    private static final float PLAY_BUTTON_HEIGHT = 0.2f;

    private Texture bgTexture;
    private Background background;

    private TextureAtlas textureAtlas;
    private Star[] stars;

    private PlayButton btPlay;
    private ExitButton btExit;

    private Game game;


    public MenuScreen(Game game){
        super();
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bgTexture = new Texture("bg.png");
        background = new Background(new TextureRegion(bgTexture));
        textureAtlas = new TextureAtlas("menuAtlas.tpack");
        stars =new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(textureAtlas);
        }

        btExit = new ExitButton(textureAtlas, this, PRESS_SCALE);
        btExit.setHeightProportion(EXIT_BUTTON_HEIGHT);
        btPlay = new PlayButton(textureAtlas, this, PRESS_SCALE);
        btPlay.setHeightProportion(PLAY_BUTTON_HEIGHT);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();

    }

    public void update(float delta) {
        for (int i = 0; i < stars.length; i++) {
            stars[i].update(delta);
        }
    }

    public void draw() {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < stars.length; i++) {
            stars[i].draw(batch);
        }
        btPlay.draw(batch);
        btExit.draw(batch);
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (int i = 0; i < stars.length; i++) {
            stars[i].resize(worldBounds);
        }
        btExit.resize(worldBounds);
        btPlay.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bgTexture.dispose();
        textureAtlas.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        btExit.touchDown(touch,pointer);
        btPlay.touchDown(touch, pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        btExit.touchUp(touch,pointer);
        btPlay.touchUp(touch, pointer);
        return false;
    }

    @Override
    public void actionPerformed(Object src) {
        if(src == btExit){
            Gdx.app.exit();
        }else if(src == btPlay){
            //game.getScreen(new GameScreen());
        }
        else {
            throw new RuntimeException("Unknown source");
        }
    }
}
