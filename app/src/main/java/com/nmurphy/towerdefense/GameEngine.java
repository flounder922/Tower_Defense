package com.nmurphy.towerdefense;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

class GameEngine extends SurfaceView implements Runnable, GameStarter {

    private Thread mThread = null;
    private long mFPS;

    private GameState mGameState;

    public GameEngine(Context context, Point size) {
        super(context);

        mGameState = new GameState(this, context);
    }

    @Override
    public void run() {
        long frameStartTime = System.currentTimeMillis();

        // Update all Game Objects

        // Draw all the Game Objects

        // Measures the FPS.
        long timeThisFrame = System.currentTimeMillis() - frameStartTime;
        if(timeThisFrame >= 1) {
            final int MILLIS_IN_SECOND = 1000;
            mFPS = MILLIS_IN_SECOND / timeThisFrame;
        }
    }

    @Override
    public boolean onTouchEvent (MotionEvent motionEvent) {
        // Handles the players inputs

        return true;
    }

    public void startThread() {
        // New Code

        mThread = new Thread(this);
        mThread.start();
    }

    public void stopThread() {
        // New Code

        try {
            mThread.join();
        } catch (InterruptedException e) {
            Log.e("Exception", "stopThread()" + e.getMessage());
        }
    }

    @Override
    public void deSpawnReSpawn() {
        // Will de-spawn and re-spawn game objects.
    }
}
