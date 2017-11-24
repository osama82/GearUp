package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Giddy on 23/11/2017.
 */

public class Animator implements ApplicationListener {

    // Constant rows and columns of the sprite sheet
    private static final int FRAME_COLS = 3, FRAME_ROWS = 5;

    private Animation<TextureRegion> walkAnimation; //animation object that animate the player
    private Texture walkSheet; //sprite sheet for the player
    private SpriteBatch spriteBatch; //to draw on screen
    // A variable for tracking elapsed time for the animation (when the player moves)
    private float stateTime;


    //useless atm
    boolean updateAnimationStateTime =false; // keep track of when to update Bob state time
    private static final float MOVE_TO_X = 3f; // unit will move in x direction
    private float originX, originalY;


    @Override
    public void create() {
        // Load the sprite sheet as a Texture
        walkSheet = new Texture(Gdx.files.internal("spritebase_hero.png"));

        // Create a 2D array of TextureRegions by splitting the sheet into separate frames
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);
        //convert 2D array to normal array
        //TODO this part for each type of animation (walkLeftFrames, walkRightFrames, idleFrames, etc.)
        //TODO then put it in a switch statement.
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS];
        //We only want to animate the three images in the first row
        //GO DOWN & IDLE
        for (int i = 0; i < FRAME_COLS; i++) {
            walkFrames[i] = tmp[0][i];
        }
        //GO LEFT
        for (int i = 0; i < FRAME_COLS; i++) {
            walkFrames[i] = tmp[1][i];
        }
        //GO RIGHT
        for (int i = 0; i < FRAME_COLS; i++) {
            walkFrames[i] = tmp[2][i];
        }
        //GO UP
        for (int i = 0; i < FRAME_COLS; i++) {
            walkFrames[i] = tmp[3][i];
        }
        // Initialize the Animation with the frame interval and array of frames
        walkAnimation = new Animation<TextureRegion>(0.5f, walkFrames);

        // Instantiate a SpriteBatch for drawing and reset the elapsed animation time to 0
        spriteBatch = new SpriteBatch();
        stateTime = 0f;

    }

    //TODO make the player move
    public void move(){}

    @Override
    public void render() {
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        // Get current frame of animation for the current stateTime:
        // this method takes an elapsed time parameter and returns the appropriate image for that time. it loops through a series of images and do it again
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, 0, 0); // Draw current frame at (0, 0)
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        walkSheet.dispose();
    }

    @Override
    public void resume(){}
    @Override
    public void pause(){}
    @Override
    public void resize(int x, int y){}
}