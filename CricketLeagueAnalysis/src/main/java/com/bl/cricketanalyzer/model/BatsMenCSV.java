package com.bl.cricketanalyzer.model;

import com.opencsv.bean.CsvBindByName;

public class BatsMenCSV {
    @CsvBindByName(column = "Player", required = true)
    public String player;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "4s", required = true)
    public int fours;

    @CsvBindByName(column = "6s", required = true)
    public int sixes;
}

