package com.nmurphy.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Renderer {

    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;

    Renderer(SurfaceView sh) {
        mSurfaceHolder = sh.getHolder();
        mPaint = new Paint();
    }

    void draw(GameState gameState, HUD hud) {
        if(mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255, 0, 0, 0));

            if (gameState.getDrawing()) {
                // Draw all the game objects
            }

            if (gameState.getGameOver()) {
                // Draw a background graphic
            }

            // Draw a particle system

            // Draw the HUD one top of everything
            hud.draw(mCanvas, mPaint, gameState);

            mSurfaceHolder.unlockCanvasAndPost(mCanvas);

        }
    }
}
