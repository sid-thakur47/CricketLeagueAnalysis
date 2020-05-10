package com.bl.cricketanalyzer.constants;

public interface FilePaths {

    String BATSMEN_DATA = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    String BOWLER_DATA="./src/test/resources/IPL2019FactsheetMostWkts.csv";
    String STRIKE_RATE = "strikeRate";
    String AVERAGE="average";
    String SIX_FOUR="sixFour";
    String STRIKE_AND_SIX_FOUR="strikeFourSix";
    String AVG_AND_STRIKE_RATE="avgStrikeRate";
    String RUNS_AND_AVG="runsAverage";
    String ECONOMY="economy";
    String STRIKE_AND_WICKETS="strikeWickets";



    enum Player{
        BATSMEN,BOWLER
    }
}
