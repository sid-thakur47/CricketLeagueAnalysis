package com.bl.cricketanalyzer;

import com.bl.cricketanalyzer.constants.FilePaths;
import com.bl.cricketanalyzer.exception.CricketAnalyserException;
import com.bl.cricketanalyzer.model.BatsMenCSV;
import com.bl.cricketanalyzer.service.CricketAnalyzer;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyzerTest implements FilePaths {
    @Test
    public void givenIPLData_CSVFile_ReturnsCorrectRecords() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            int numOfRecords = cricketAnalyzer.loadCricketData( BATSMEN_DATA );
            Assert.assertEquals( 100, numOfRecords );
        } catch (CricketAnalyserException e) {
        }
    }
    @Test
    public void givenWhenCricketData_ShouldReturn_TopBattingAverage() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( BATSMEN_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( "average" );
            BatsMenCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BatsMenCSV[].class );
            Assert.assertEquals( "MS Dhoni", censusCSV[0].player );
        } catch (CricketAnalyserException | JsonSyntaxException e) {
        }
    }
    @Test
    public void givenWhenCricketData_ShouldReturn_TopStrikeRate() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketData( BATSMEN_DATA );
            String sortedCensusData = cricketAnalyzer.getFieldWiseData( "strikerate" );
            BatsMenCSV[] censusCSV = new Gson().fromJson( sortedCensusData, BatsMenCSV[].class );
            Assert.assertEquals( "Ishant Sharma", censusCSV[0].player );
        } catch (CricketAnalyserException | JsonSyntaxException e) {
        }
    }
}
