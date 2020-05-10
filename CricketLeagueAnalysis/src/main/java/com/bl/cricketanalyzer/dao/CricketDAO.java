package com.bl.cricketanalyzer.dao;

import com.bl.cricketanalyzer.model.BatsMenCSV;
import com.bl.cricketanalyzer.model.BowlerCSV;

public class CricketDAO {
    public String player;
    public int runs;
    public double average;
    public double strikeRate;
    public int fours;
    public int sixes;
    public int sumSixFour;
    public double economy;
    public int five;

    public CricketDAO(BatsMenCSV playerObj) {
        average = playerObj.average;
        fours = playerObj.fours;
        player = playerObj.player;
        runs = playerObj.runs;
        sixes = playerObj.sixes;
        strikeRate = playerObj.strikeRate;
        sumSixFour = playerObj.fours + playerObj.sixes;
    }

    public CricketDAO(BowlerCSV bowlerObj) {
        average = bowlerObj.average;
        player = bowlerObj.player;
        strikeRate = bowlerObj.strikeRate;
        economy = bowlerObj.economy;
        fours = bowlerObj.fourWicket;
        five = bowlerObj.fiveWickets;
    }
    public double getStrikeRate() {
        return strikeRate;
    }
    public int getSumSixFour() {
        return sumSixFour;
    }
    public double getAverage() {
        return average;
    }
    public int getRuns() {
        return runs;
    }
}
