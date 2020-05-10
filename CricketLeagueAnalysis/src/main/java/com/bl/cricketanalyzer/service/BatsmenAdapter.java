package com.bl.cricketanalyzer.service;

import com.bl.cricketanalyzer.dao.CricketDAO;
import com.bl.cricketanalyzer.exception.CricketAnalyserException;
import com.bl.cricketanalyzer.model.BatsMenCSV;

import java.util.Map;

public  class BatsmenAdapter extends CricketAdapter{
    @Override
    public Map<String, CricketDAO> loadCricketData(String csvFilePath) throws CricketAnalyserException {
        return super.loadCricketData( BatsMenCSV.class,csvFilePath );
    }
}
