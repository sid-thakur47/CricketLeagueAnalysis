package com.bl.cricketanalyzer.model;

import com.opencsv.bean.CsvBindByName;

public class BowlerCSV {
    @CsvBindByName(column = "Player", required = true)
    public String player;

    @CsvBindByName(column = "Econ", required = true)
    public double economy;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "4w", required = true)
    public int fourWicket;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWickets;
}