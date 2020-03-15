package com.nmurphy.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Collection;

public class HUD {

    private int mTextFormatting;
    private int mScreenHeight;
    private int mScreenWidth;

    static int TOWER_ONE = 0;
    static int TOWER_TWO = 1;
    static int PAUSE = 2;

    private ArrayList<Rect> controls;


    HUD(Point size) {
        mScreenHeight = size.y;
        mScreenWidth = size.x;

        mTextFormatting = size.x / 50;

        prepareControls();
    }

    private void prepareControls() {
        int buttonWidth = mScreenWidth / 15;
        int buttonHeight = mScreenHeight / 12;
        int buttonPadding = mScreenWidth / 95;

        Rect towerOne = new Rect(buttonPadding,
                mScreenHeight - (buttonHeight * 2) - (buttonPadding * 2),
                buttonWidth + buttonPadding,
                mScreenHeight - buttonHeight - (buttonPadding * 2));

        Rect towerTwo = new Rect(
                mScreenWidth - buttonPadding - buttonWidth,
                mScreenHeight - (buttonHeight * 2) - (buttonPadding * 2),
                buttonWidth - buttonPadding,
                mScreenHeight - buttonHeight - (buttonPadding * 2));

        Rect pause = new Rect(
                mScreenWidth - buttonPadding - buttonWidth,
                buttonPadding + 0,
                mScreenWidth - buttonPadding,
                buttonPadding + buttonHeight);

        controls = new ArrayList<>();
        controls.add(TOWER_ONE, towerOne);
        controls.add(TOWER_TWO, towerTwo);
        controls.add(PAUSE, pause);

    }

    public void draw(Canvas canvas, Paint paint, GameState gameState) {
        // Drawing the HUD
        paint.setColor(Color.argb(255, 255, 255, 255));
        paint.setTextSize(mTextFormatting);

        canvas.drawText("Hi: " + gameState.getHighScore(),
                mTextFormatting, mTextFormatting, paint);

        canvas.drawText("Score: " + gameState.getScore(),
                mTextFormatting, mTextFormatting * 2, paint);

        canvas.drawText("Lives: " + gameState.getBaseLife(),
                mTextFormatting, mTextFormatting * 3, paint);

        if(gameState.getGameOver()) {
            paint.setTextSize(mTextFormatting * 5);
            canvas.drawText("PRESS PLAY",
                    mScreenWidth / 4,
                    mScreenHeight / 2, paint);
        }

        if(gameState.getPaused() && !gameState.getGameOver()) {
            paint.setTextSize(mTextFormatting * 5);
            canvas.drawText("PAUSED",
                    mScreenWidth / 3,
                    mScreenHeight / 2, paint);
        }

        drawControls(canvas, paint);
    }

    private void drawControls(Canvas canvas, Paint paint) {
        paint.setColor(Color.argb(100, 255, 255, 255));

        for(Rect r : controls) {
            canvas.drawRect(r.left, r.top, r.right, r.bottom, paint);
        }
        // Sets the colors black
        paint.setColor(Color.argb(255, 255, 255, 255));
    }

    ArrayList<Rect> getControls() {
        return controls;
    }
}
