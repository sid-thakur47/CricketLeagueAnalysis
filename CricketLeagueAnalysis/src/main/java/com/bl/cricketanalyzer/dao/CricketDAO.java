package com.bl.cricketanalyzer.dao;

import com.bl.cricketanalyzer.model.BatsMenCSV;
import com.bl.cricketanalyzer.model.BowlerCSV;

public class CricketDAO {
    public  int sumWickets;
    public String player;
    public int runs;
    public double average;
    public double strikeRate;
    public int fours;
    public int sixes;
    public int sumSixFour;
    public double economy;
    public int five;
    public double batsmenAvg;
    public double bowlerAvg;


    public CricketDAO(BatsMenCSV batsmenObj) {
        average = batsmenObj.average;
        fours = batsmenObj.fours;
        player = batsmenObj.player;
        runs = batsmenObj.runs;
        sixes = batsmenObj.sixes;
        strikeRate = batsmenObj.strikeRate;
        sumSixFour = batsmenObj.fours + batsmenObj.sixes;
    }

    public CricketDAO(BowlerCSV bowlerObj) {
        average = bowlerObj.average;
        player = bowlerObj.player;
        strikeRate = bowlerObj.strikeRate;
        economy = bowlerObj.economy;
        fours = bowlerObj.fourWicket;
        five = bowlerObj.fiveWicket;
        sumWickets=bowlerObj.fourWicket + bowlerObj.fiveWicket;
    }
    public CricketDAO(CricketDAO batsMen, CricketDAO bowler) {
        this.player=batsMen.player;
        this.batsmenAvg=batsMen.average;
        this.bowlerAvg=bowler.average;
        this.sumWickets=bowler.sumWickets;
        this.runs=batsMen.runs;
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
    public int getSumWickets() {
        return sumWickets;
    }
    public double getBatsmenAvg() {
        return batsmenAvg;
    }
    public double getBowlerAvg() {
        return bowlerAvg;
    }
}
