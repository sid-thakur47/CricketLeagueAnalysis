package com.bl.cricketanalyzer.dao;

import com.bl.cricketanalyzer.model.BatsMenCSV;

public class CricketDAO {
    public String player;
    public int runs;
    public String average;
    public double strikeRate;
    public int fours;
    public int sixes;

    public CricketDAO(BatsMenCSV playerObj) {
        this.average = playerObj.average;
        this.fours = playerObj.fours;
        this.player = playerObj.player;
        this.runs = playerObj.runs;
        this.sixes = playerObj.sixes;
        this.strikeRate = playerObj.strikeRate;
    }
}
