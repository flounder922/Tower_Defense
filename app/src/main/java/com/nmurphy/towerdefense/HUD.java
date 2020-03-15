package com.nmurphy.towerdefense;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

public class HUD {

    private int mTextFormatting;
    private int mScreenHeight;
    private int mScreenWidth;

    static int towerOne = 0;
    static int towerTwo = 1;

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
    }
}
