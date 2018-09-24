package com.example.negar.snakesladders.utility;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.negar.snakesladders.R;
import com.example.negar.snakesladders.model.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Drawing {
    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint();
    private Bitmap mBitmap;
    private ImageView mImageView;
    private RectF mRect = new RectF();
    public static final int OFFSET = 10;
    private int mColorRectangle;
    public int sqHeight,sqWidth;
    
    public Board board;

    public Drawing( int recColor,int textColor,int boardSize){
        mColorRectangle = recColor;
        mPaintText.setColor(textColor);
        this.board=new Board(boardSize);

    }

    public void drawBoard(View view,Bitmap ladder,Bitmap snake)  {

        int vWidth = view.getWidth();
        int vHeight = view.getHeight();

        mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
        mImageView= (ImageView) view;
        mImageView.setImageBitmap(mBitmap);
        mCanvas = new Canvas(mBitmap);


        // Change the color by subtracting an integer.
        sqWidth = (vWidth - OFFSET) / board.size - OFFSET;
        sqHeight = (vHeight - OFFSET) / board.size - OFFSET;

        mPaintText.setTextSize(sqWidth/3);


        for (int col = 0; col < board.size; col++) {
            for (int row = 0; row < board.size; row++) {
                int cellNumber= col+1+row*board.size;
                mPaint.setColor(mColorRectangle);
                mRect.set(OFFSET + (sqWidth + OFFSET) * col, OFFSET + (sqHeight + OFFSET) * row, OFFSET + (sqWidth + OFFSET) * col + sqWidth, OFFSET + (sqHeight + OFFSET) * row + sqHeight);
                board.boardTilesPoints.add(Arrays.asList(OFFSET + (sqWidth + OFFSET) * col, OFFSET + (sqHeight + OFFSET) * row, OFFSET + (sqWidth + OFFSET) * col + sqWidth, OFFSET + (sqHeight + OFFSET) * row + sqHeight));
                int cornersRadius = 30-board.size+200/sqHeight;
                mCanvas.drawRoundRect(mRect, cornersRadius,cornersRadius,mPaint);
                mCanvas.drawText(String.valueOf(cellNumber), OFFSET + (sqWidth + OFFSET)*col+sqWidth/2, (sqHeight + OFFSET) * row+sqHeight/2, mPaintText);

            }
        }
        //display ladder and snakes
        for (int t=0; t<board.boardLadders.size();t++){
            Point p =board.tileToPoit(board.boardLadders.get(t));
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(ladder, sqWidth/2, sqHeight*2, true);
            mCanvas.drawBitmap(resizedBitmap,p.x+sqWidth/4,p.y+sqHeight/2,mPaint);
        }

        for (int t=0; t<board.boardSnakes.size();t++){
            Point p =board.tileToPoit(board.boardSnakes.get(t));
            Bitmap modifiedBitmap = Bitmap.createScaledBitmap(snake, sqWidth*2, sqHeight/3, true);

            if (t%2==0){
                Matrix matrix = new Matrix();
                matrix.preScale(-1.0f, 1.0f);
                modifiedBitmap = Bitmap.createBitmap(modifiedBitmap,0,0, sqWidth*2, sqHeight/3,matrix, true);
            }
            mCanvas.drawBitmap(modifiedBitmap,p.x,p.y+sqHeight/2,mPaint);
        }
    }

    public void showAvatarInTile(int userTile,Bitmap avatar){
        Point p =board.tileToPoit(userTile);

        Bitmap resizedAvatar = Bitmap.createScaledBitmap(avatar, sqWidth/2, sqHeight/2, true);
        mCanvas.drawBitmap(resizedAvatar,p.x+sqWidth/4,p.y+sqHeight/2,mPaint);
    }
}
