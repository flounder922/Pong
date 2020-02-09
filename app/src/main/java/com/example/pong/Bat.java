package com.example.pong;

import android.graphics.RectF;

class Bat {

    // These are the member variables (fields) they all have the m prefix.
    // They are all private because direct access is not required.
    private RectF mRect;
    private float mLength;
    private float mXCoord;
    private float mBatSpeed;
    private float mScreenX;

    // Keeps track of if an how the ball is moving
    // Starting with STOPPED which is 0
    private int mBatMoving = 0;

    Bat(int sx, int sy){
        // Bat needs to know the screen horizontal resolution outside of this method
        mScreenX = (float) sx;

        // Configure the size of the bat based on the screen resolution
        // one eighth the screen width
        mLength = mScreenX / 8.0f;

        // One fortieth the screen height
        float height = (float)(sy / 40.0);

        // Configure the starting location of the bat roughly the middle horizontally.
        mXCoord = mScreenX / 2.0f;

        // The height of the bat off of the bottom of the screen
        float mYCoord = (float)sy - height;

        // Initialize mRect based on the size and position
        mRect = new RectF(mXCoord, mYCoord,
                mXCoord + mLength,
                mYCoord + height);

        // Configure the speed of the bat this code means the bat can cover the
        // width of the screen in 1 second.
        mBatSpeed = mScreenX;
    }

    // Return a reference to the mRect object
    RectF getRect(){
        return mRect;
    }

    // Update the movement state passed
    // in by the onTouchEvent method
    void setMovementState(int state){
        mBatMoving = state;
    }


    // Update the bat- Called each frame/loop
    void update(long fps){
        // Move the bat based on the mBatMoving variable and the speed of the previous frame.
        // 1 means it is moving to the left.
        // 2 means it is moving to the right.
        if(mBatMoving == 1){
            mXCoord = mXCoord - mBatSpeed / fps;
        }

        if(mBatMoving == 2){
            mXCoord = mXCoord + mBatSpeed / fps;
        }

        // Stop the bat going off the screen
        if(mXCoord < 0){
            mXCoord = 0;
        }

        if(mXCoord + mLength > mScreenX){
            mXCoord = mScreenX - mLength;
        }

        // Update mRect based on the results from
        // the previous code in update
        mRect.left = mXCoord;
        mRect.right = mXCoord + mLength;
    }
}
