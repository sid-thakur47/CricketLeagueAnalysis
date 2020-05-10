package com.bl.cricketanalyzer.service;

import com.bl.cricketanalyzer.dao.CricketDAO;
import com.bl.cricketanalyzer.exception.CricketAnalyserException;
import com.bl.cricketanalyzer.model.BowlerCSV;

import java.util.Map;

public  class BowlerAdapter extends CricketAdapter{
    @Override
    public Map<String, CricketDAO> loadCricketData(String csvFilePath) throws CricketAnalyserException {
        return super.loadCricketData( BowlerCSV.class,csvFilePath );
    }
}
