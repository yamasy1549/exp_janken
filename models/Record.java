package models;

import java.io.*;
import java.util.*;
import views.*;
import static original.Constants.*;

public class Record {

    private int WIN, LOSE, DRAW;

    Record() {
        this.WIN = 0;
        this.LOSE = 0;
        this.DRAW = 0;
    }

    public int get(Result result) {
        // TODO: もうちょっとマシな書き方ありそう
        if(result == Result.WIN) {
            return this.WIN;
        } else if(result == Result.LOSE) {
            return this.LOSE;
        } else {
            return this.DRAW;
        }
    }

    public void set(Result result, int count) {
        // TODO: もうちょっとマシな書き方ありそう
        if(result == Result.WIN) {
            this.WIN = count;
        } else if(result == Result.LOSE) {
            this.LOSE = count;
        } else {
            this.DRAW = count;
        }
    }

    public void merge(Result result, int count) {
        // TODO: もうちょっとマシな書き方ありそう
        if(result == Result.WIN) {
            this.WIN += count;
        } else if(result == Result.LOSE) {
            this.LOSE += count;
        } else {
            this.DRAW += count;
        }
    }
}
