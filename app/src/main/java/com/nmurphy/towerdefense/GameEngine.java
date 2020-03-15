package com.nmurphy.towerdefense;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

class GameEngine extends SurfaceView implements Runnable, GameStarter {

    private Thread mThread = null;
    private long mFPS;

    private SoundEngine mSoundEngine;

    private GameState mGameState;

    public GameEngine(Context context, Point size) {
        super(context);

        mGameState = new GameState(this, context);

        // Decides the version of SoundEngine to use
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSoundEngine = new SoundEngine(new PostLollipopSoundEngine(context));
        }
        else {
            mSoundEngine = new SoundEngine(new PreLollipopSoundEngine(context));
        }

    }

    @Override
    public void run() {
        while (mGameState.getThreadRunning()) {
            // Initial start time to help calculate the FPS
            long frameStartTime = System.currentTimeMillis();

            if(!mGameState.getPaused()) {
                // Update all Game Objects

            }

            // Draw all the Game Objects

            // Measures the FPS.
            long timeThisFrame = System.currentTimeMillis() - frameStartTime;
            if(timeThisFrame >= 1) {
                final int MILLIS_IN_SECOND = 1000;
                mFPS = MILLIS_IN_SECOND / timeThisFrame;
            }
        }
    }

    @Override
    public boolean onTouchEvent (MotionEvent motionEvent) {
        // Handles the players inputs

        return true;
    }

    public void startThread() {
        // New Code
        mGameState.startThread();

        mThread = new Thread(this);
        mThread.start();
    }

    public void stopThread() {
        // New Code
        mGameState.stopEverything();

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
