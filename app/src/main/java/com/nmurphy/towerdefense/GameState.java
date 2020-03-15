package com.nmurphy.towerdefense;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

class GameState {
    private static volatile boolean mThreadRunning = false;
    private static volatile boolean mPaused = true;
    private static volatile boolean mGameOver = true;
    private static volatile boolean mDrawing = false;

    // This will have access to the deSpawnReSpawn() method in GameEngine.
    private GameStarter gameStarter;

    private int mScore;
    private int mHighScore;
    private int mBaseLife;

    // This is to make a persistent high score.
    private SharedPreferences.Editor mEditor;

    public GameState(GameStarter gs, Context context) {
        // This initializes the gameStarter reference
        gameStarter = gs;

        // Get the current high score
        SharedPreferences prefs;
        prefs = context.getSharedPreferences("HighScore", Context.MODE_PRIVATE);

        // Initialize the mEditor
        mEditor = prefs.edit();

        // Load high score from a entry in the file labeled "hi_score"
        mHighScore = prefs.getInt("high_score", 0);
    }

    private void endGame() {
        mGameOver = true;
        mPaused = true;
        if(mScore > mHighScore) {
            mHighScore = mScore;
            mEditor.putInt("high_score", mHighScore);
            mEditor.commit();
        }
    }

    void startNewGame() {
        mScore = 0;

        // Stop Drawing so that the game objects can be de-spawned.
        stopDrawing();
        // De-spawn and then re-span needed game objects
        gameStarter.deSpawnReSpawn();
        resume();

        // Start drawing again.
        startDrawing();
    }

    void loseLife(SoundEngine se) {
        se.strategyPlaySound1();
        if(mBaseLife == 0) {
            pause();
            endGame();
        }
    }

    private void resume() {
        mGameOver = false;
        mPaused = false;
    }

    void pause() {
        mPaused = true;
    }

    private void startDrawing() {
        mDrawing = true;
    }

    private void stopDrawing() {
        mDrawing = false;
    }

    int getBaseLife() {
        return mBaseLife;
    }

    void increaseScore() {
        mScore++;
    }

    int getScore() {
        return mScore;
    }

    int getHighScore() {
        return mHighScore;
    }

    void startThread() {
        mThreadRunning = true;
    }

    void stopEverything() {
        mPaused = true;
        mGameOver = true;
        mThreadRunning = false;
    }

    boolean getThreadRunning() {
        return mThreadRunning;
    }

    boolean getDrawing() {
        return mDrawing;
    }

    boolean getPaused() {
        return mPaused;
    }

    boolean getGameOver() {
        return mGameOver;
    }
}
