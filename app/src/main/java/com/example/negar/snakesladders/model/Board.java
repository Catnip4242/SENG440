package com.example.negar.snakesladders.model;

import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    public int size;
    public List<List<Integer>> boardTilesPoints = new ArrayList<List<Integer>>();
    public List<Integer> boardSnakes=new ArrayList<Integer>();
    public List<Integer> boardLadders=new ArrayList<Integer>();


    public Board(int size){
        this.size=size;
        boardSnakes= Arrays.asList( 2,14,21);
        boardLadders=Arrays.asList(5,6,13);
    }

    int getSize(){
        return this.size;
    }

    void setSize(int newSize){
        this.size=newSize;
    }

    public int pointToTile(int x,int y){
        for(int i=0;i<size*size;i++){
            if (boardTilesPoints.get(i).get(0)<=x & x<=boardTilesPoints.get(i).get(2) & boardTilesPoints.get(i).get(1)<=y & y<=boardTilesPoints.get(i).get(3))
            {
                return i;
            }
        }
        return -1;
    }

    public void showBoardPoints(){
        for(int i=0;i<size*size;i++){
            Log.d("tile"+i,"["+boardTilesPoints.get(i).get(0)+","+boardTilesPoints.get(i).get(2)+"], [" +boardTilesPoints.get(i).get(1)+"," + boardTilesPoints.get(i).get(3)+"]");

        }
    }

    public Point tileToPoit(int t){
        if (t%size==0){
            t=t/size+(size-1)*size-1;
        }
        else{
            t=t/size+((t%size-1)*size);
        }

        int x= boardTilesPoints.get(t).get(0);
        int y=boardTilesPoints.get(t).get(1);
        Point p=new Point(x,y);
        return p;
    }
}
