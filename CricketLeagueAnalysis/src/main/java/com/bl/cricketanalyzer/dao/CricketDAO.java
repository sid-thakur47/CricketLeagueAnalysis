package com.bl.cricketanalyzer.dao;

import com.bl.cricketanalyzer.model.BatsMenCSV;

public class CricketDAO {
    public String player;
    public int runs;
    public String average;
    public double strikeRate;
    public int fours;
    public int sixes;
    public int sumSixFour;

    public CricketDAO(BatsMenCSV playerObj) {
        average = playerObj.average;
        fours = playerObj.fours;
        player = playerObj.player;
        runs = playerObj.runs;
        sixes = playerObj.sixes;
        strikeRate = playerObj.strikeRate;
        sumSixFour = playerObj.fours + playerObj.sixes;
    }
    public double getStrikeRate() {
        return strikeRate;
    }
    public int getSumSixFour() {
        return sumSixFour;
    }
}
