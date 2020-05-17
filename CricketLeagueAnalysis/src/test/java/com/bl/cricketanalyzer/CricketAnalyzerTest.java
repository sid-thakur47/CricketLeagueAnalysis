package com.bl.cricketanalyzer;

import com.bl.cricketanalyzer.constants.Contants;
import com.bl.cricketanalyzer.exception.CricketAnalyserException;
import com.bl.cricketanalyzer.model.BatsMenCSV;
import com.bl.cricketanalyzer.model.BowlerCSV;
import com.bl.cricketanalyzer.service.CricketAnalyzer;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyzerTest implements Contants {

    CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();

    @Test
    public void given_When_CricketData_Correct_Should_ReturnsCorrectRecords() {
        try {
            int numOfRecords = 0;
            numOfRecords = cricketAnalyzer.loadCricketData( Player.BATSMEN, BATSMEN_DATA );
            Assert.assertEquals( 100, numOfRecords );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given_WhenCricketData_SortedOnTopAverage_ShouldReturn_TopBattingAverage() {
        try {
            cricketAnalyzer.loadCricketData( Player.BATSMEN, BATSMEN_DATA );
            String sortedCricketData = cricketAnalyzer.getFieldWiseData( AVERAGE );
            BatsMenCSV[] cricketCSV = new Gson().fromJson( sortedCricketData, BatsMenCSV[].class );
            Assert.assertEquals( "MS Dhoni", cricketCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given_WhenCricketData_SortedOnStrikeRate_ShouldReturn_TopStrikeRateOfBatsMan() {
        try {
            cricketAnalyzer.loadCricketData( Player.BATSMEN, BATSMEN_DATA );
            String sortedCricketData = cricketAnalyzer.getFieldWiseData( STRIKE_RATE );
            BatsMenCSV[] cricketCSV = new Gson().fromJson( sortedCricketData, BatsMenCSV[].class );
            Assert.assertEquals( "Ishant Sharma", cricketCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given_WhenCricketData_SortedOnSixAndFour_ShouldReturn_BatsManWithMostSixAndFour() {
        try {
            cricketAnalyzer.loadCricketData( Player.BATSMEN, BATSMEN_DATA );
            String sortedCricketData = cricketAnalyzer.getFieldWiseData( SIX_FOUR );
            BatsMenCSV[] cricketCSV = new Gson().fromJson( sortedCricketData, BatsMenCSV[].class );
            Assert.assertEquals( "Andre Russell", cricketCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given_WhenCricketData_SortedOnSixFourAndStrikeRate_ShouldReturn_BatsManWithStrikeRateAndMostSixFour
            () {
        try {
            cricketAnalyzer.loadCricketData( Player.BATSMEN, BATSMEN_DATA );
            String sortedCricketData = cricketAnalyzer.getFieldWiseData( STRIKE_AND_SIX_FOUR );
            BatsMenCSV[] cricketCSV = new Gson().fromJson( sortedCricketData, BatsMenCSV[].class );
            Assert.assertEquals( "Andre Russell", cricketCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given_WhenCricketData_SortedOnAverageWithStrikeRate_ShouldReturn_BatsManWithBestAvgWithStrikeRate() {
        try {
            cricketAnalyzer.loadCricketData( Player.BATSMEN, BATSMEN_DATA );
            String sortedCricketData = cricketAnalyzer.getFieldWiseData( AVG_AND_STRIKE_RATE );
            BatsMenCSV[] cricketCSV = new Gson().fromJson( sortedCricketData, BatsMenCSV[].class );
            Assert.assertEquals( "David Warner", cricketCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given_WhenCricketData_SortedOnMostRunsWithAverage_ShouldReturn_PlayerWithMostRunsAndAvg() {
        try {
            cricketAnalyzer.loadCricketData( Player.BATSMEN, BATSMEN_DATA );
            String sortedCricketData = cricketAnalyzer.getFieldWiseData( RUNS_AND_AVG );
            BatsMenCSV[] cricketCSV = new Gson().fromJson( sortedCricketData, BatsMenCSV[].class );
            Assert.assertEquals( "David Warner", cricketCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given_WhenCricketData_SortedOnAverage_ShouldReturn_BowlerTopAvg() {
        try {
            cricketAnalyzer.loadCricketData( Player.BOWLER, BOWLER_DATA );
            String sortedCricketData = cricketAnalyzer.getFieldWiseData( AVERAGE );
            BowlerCSV[] cricketCSV = new Gson().fromJson( sortedCricketData, BowlerCSV[].class );
            Assert.assertEquals( "Anukul Roy", cricketCSV[cricketCSV.length - 1].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given_WhenCricketData_SortedOnStrikeRate_ShouldReturn_BowlerTopStrikeRate() {
        try {
            cricketAnalyzer.loadCricketData( Player.BOWLER, BOWLER_DATA );
            String sortedCricketData = cricketAnalyzer.getFieldWiseData( STRIKE_RATE );
            BowlerCSV[] cricketCSV = new Gson().fromJson( sortedCricketData, BowlerCSV[].class );
            Assert.assertEquals( "Alzarri Joseph", cricketCSV[cricketCSV.length - 1].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given_WhenCricketData_SortedEconomyRate_ShouldReturn_BowlerTopEconomyRate() {
        try {
            cricketAnalyzer.loadCricketData( Player.BOWLER, BOWLER_DATA );
            String sortedCricketData = cricketAnalyzer.getFieldWiseData( ECONOMY );
            BowlerCSV[] cricketCSV = new Gson().fromJson( sortedCricketData, BowlerCSV[].class );
            Assert.assertEquals( "Shivam Dube", cricketCSV[cricketCSV.length - 1].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given_WhenCricketData_SortedStrikeRateAndWickets_ShouldReturn_BowlerTopStrikeRateAndWickets() {
        try {
            cricketAnalyzer.loadCricketData( Player.BOWLER, BOWLER_DATA );
            String sortedCricketData = cricketAnalyzer.getFieldWiseData( STRIKE_AND_WICKETS );
            BowlerCSV[] cricketCSV = new Gson().fromJson( sortedCricketData, BowlerCSV[].class );
            Assert.assertEquals( "Kagiso Rabada", cricketCSV[cricketCSV.length - 1].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given_WhenCricketData_SortedBowlingAvgAndStrikeRate_ShouldReturn_BowlerTopBowlingAvgAndStrikeRate() {
        try {
            cricketAnalyzer.loadCricketData( Player.BOWLER, BOWLER_DATA );
            String sortedCricketData = cricketAnalyzer.getFieldWiseData( BOWL_AVG_AND_STRIKE_RATE );
            BowlerCSV[] cricketCSV = new Gson().fromJson( sortedCricketData, BowlerCSV[].class );
            Assert.assertEquals( "Kagiso Rabada", cricketCSV[cricketCSV.length - 1].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given_WhenCricketData_SortedTopWicketsAndAverage_ShouldReturn_BowlerTopWicketsAndAverage() {
        try {
            cricketAnalyzer.loadCricketData( Player.BOWLER, BOWLER_DATA );
            String sortedCricketData = cricketAnalyzer.getFieldWiseData( WICKET_AVERAGE );
            BowlerCSV[] cricketCSV = new Gson().fromJson( sortedCricketData, BowlerCSV[].class );
            Assert.assertEquals( "Imran Tahir", cricketCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given_WhenCricketData_SortedTopBattingAndBowlingAverage_ShouldReturn_CricketerWithTopAverage() {
        try {
            String sortedCricketData = cricketAnalyzer.mergeBatsMenBowlerData( BATSMEN_DATA, BOWLER_DATA, BATSMEN_BOWLER_AVERAGE );
            BowlerCSV[] cricketCSV = new Gson().fromJson( sortedCricketData, BowlerCSV[].class );
            Assert.assertEquals( "Andre Russell", cricketCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given_WhenCricketData_SortedTopBattingAndBowlingAverage_ShouldReturn_AllRounder() {
        try {
            String sortedCricketData = cricketAnalyzer.mergeBatsMenBowlerData( BATSMEN_DATA, BOWLER_DATA, ALL_ROUNDER );
            BowlerCSV[] cricketCSV = new Gson().fromJson( sortedCricketData, BowlerCSV[].class );
            Assert.assertEquals( "Andre Russell", cricketCSV[0].player );
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }
}