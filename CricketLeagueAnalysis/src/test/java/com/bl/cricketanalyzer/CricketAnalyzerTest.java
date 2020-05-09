package com.bl.cricketanalyzer;

import com.bl.cricketanalyzer.constants.FilePaths;
import com.bl.cricketanalyzer.exception.CricketAnalyserException;
import com.bl.cricketanalyzer.model.BatsMenCSV;
import com.bl.cricketanalyzer.service.CricketAnalyzer;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyzerTest implements FilePaths {
    @Test
    public void given_When_CricketData_Correct_Should_ReturnsCorrectRecords() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            int numOfRecords = cricketAnalyzer.loadCricketData( BATSMEN_DATA );
            Assert.assertEquals( 100, numOfRecords );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_EnquiredForTopAverage_ShouldReturn_TopBattingAverage() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( BATSMEN_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( "average" );
            BatsMenCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BatsMenCSV[].class );
            Assert.assertEquals( "MS Dhoni", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_EnquiredForStrikeRate_ShouldReturn_TopStrikeRatePlayer() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( BATSMEN_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( "strikerate" );
            BatsMenCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BatsMenCSV[].class );
            Assert.assertEquals( "Ishant Sharma", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_EnquiredForSixAndFour_ShouldReturn_PlayerWithMostSixAndFour() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( BATSMEN_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( "sixfour" );
            BatsMenCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BatsMenCSV[].class );
            Assert.assertEquals( "Andre Russell", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_EnquiredForSixFourAndStrikeRate_ShouldReturn_PlayerWithStrikeRateAndMostSixFour() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( BATSMEN_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( "strikeandsixfour" );
            BatsMenCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BatsMenCSV[].class );
            Assert.assertEquals( "Ishant Sharma", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void given_WhenCricketData_EnquiredForAverageWithStrikeRate_ShouldReturn_PlayerWithStrikeRateAndMostSixFour() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( BATSMEN_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( "avgandstrike" );
            BatsMenCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BatsMenCSV[].class );
            Assert.assertEquals( "MS Dhoni", censusCSV[0].player );
        } catch (CricketAnalyserException e) {
        }
    }

}
