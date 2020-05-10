package com.bl.cricketanalyzer;

import com.bl.cricketanalyzer.constants.FilePaths;
import com.bl.cricketanalyzer.exception.CricketAnalyserException;
import com.bl.cricketanalyzer.model.BatsMenCSV;
import com.bl.cricketanalyzer.model.BowlerCSV;
import com.bl.cricketanalyzer.service.CricketAnalyzer;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyzerTest implements FilePaths {
    @Test
    public void given_When_CricketData_Correct_Should_ReturnsCorrectRecords() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            int numOfRecords = cricketAnalyzer.loadCricketData( Player.BATSMEN, BATSMEN_DATA );
            Assert.assertEquals( 100, numOfRecords );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_SortedOnTopAverage_ShouldReturn_TopBattingAverage() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( Player.BATSMEN, BATSMEN_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( AVERAGE );
            BatsMenCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BatsMenCSV[].class );
            Assert.assertEquals( "MS Dhoni", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_SortedOnStrikeRate_ShouldReturn_TopStrikeRateOfBatsMan() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( Player.BATSMEN, BATSMEN_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( STRIKE_RATE );
            BatsMenCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BatsMenCSV[].class );
            Assert.assertEquals( "Ishant Sharma", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_SortedOnSixAndFour_ShouldReturn_BatsManWithMostSixAndFour() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( Player.BATSMEN, BATSMEN_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( SIX_FOUR );
            BatsMenCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BatsMenCSV[].class );
            Assert.assertEquals( "Andre Russell", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_SortedOnSixFourAndStrikeRate_ShouldReturn_BatsManWithStrikeRateAndMostSixFour() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( Player.BATSMEN, BATSMEN_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( STRIKE_AND_SIX_FOUR );
            BatsMenCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BatsMenCSV[].class );
            Assert.assertEquals( "Ishant Sharma", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_SortedOnAverageWithStrikeRate_ShouldReturn_BatsManWithBestAvgWithStrikeRate() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( Player.BATSMEN, BATSMEN_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( AVG_AND_STRIKE_RATE );
            BatsMenCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BatsMenCSV[].class );
            Assert.assertEquals( "MS Dhoni", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_SortedOnMostRunsWithAverage_ShouldReturn_PlayerWithMostRunsAndAvg() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( Player.BATSMEN, BATSMEN_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( RUNS_AND_AVG );
            BatsMenCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BatsMenCSV[].class );
            Assert.assertEquals( "David Warner", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_SortedOnAverage_ShouldReturn_BowlerTopAvg() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( Player.BOWLER, BOWLER_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( AVERAGE );
            BowlerCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BowlerCSV[].class );
            Assert.assertEquals( "Krishnappa Gowtham", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_SortedOnStrikeRate_ShouldReturn_BowlerTopStrikeRate() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( Player.BOWLER, BOWLER_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( STRIKE_RATE );
            BowlerCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BowlerCSV[].class );
            Assert.assertEquals( "Krishnappa Gowtham", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_SortedEconomyRate_ShouldReturn_BowlerTopEconomyRate() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( Player.BOWLER, BOWLER_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( ECONOMY );
            BowlerCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BowlerCSV[].class );
            Assert.assertEquals( "Ben Cutting", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_SortedStrikeRateAndWickets_ShouldReturn_BowlerTopStrikeRateAndWickets() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( Player.BOWLER, BOWLER_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( STRIKE_AND_WICKETS );
            BowlerCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BowlerCSV[].class );
            Assert.assertEquals( "Krishnappa Gowtham", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_SortedBowlingAvgAndStrikeRate_ShouldReturn_BowlerTopBowlingAvgAndStrikeRate() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( Player.BOWLER, BOWLER_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData(AVG_AND_STRIKE_RATE);
            BowlerCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BowlerCSV[].class );
            Assert.assertEquals( "Krishnappa Gowtham", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_SortedTopWicketsAndAverage_ShouldReturn_BowlerTopWicketsAndAverage() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( Player.BOWLER, BOWLER_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData(WICKET_AVERAGE);
            BowlerCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BowlerCSV[].class );
            Assert.assertEquals( "Lasith Malinga", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }
}
