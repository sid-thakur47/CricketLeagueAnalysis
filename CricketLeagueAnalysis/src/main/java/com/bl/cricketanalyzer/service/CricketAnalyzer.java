package com.bl.cricketanalyzer.service;

import com.bl.cricketanalyzer.dao.CricketDAO;
import com.bl.cricketanalyzer.exception.CricketAnalyserException;
import com.bl.cricketanalyzer.model.BatsMenCSV;
import com.csvbuilder.CSVBuilderFactory;
import com.csvbuilder.ICsvBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyzer {

    List<CricketDAO> cricketList;
    Map<String, CricketDAO> cricketMap;

    public CricketAnalyzer() {
        this.cricketList = new ArrayList<>();
        this.cricketMap = new HashMap<>();
    }
    public int loadCricketData(String csvFilePath) throws CricketAnalyserException {
        try {
            Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) );
            ICsvBuilder csvBuilderInterface = CSVBuilderFactory.createCSVBuilder();
            List<BatsMenCSV> csvFileList = csvBuilderInterface.getCSVFileList
                    ( reader, BatsMenCSV.class );
            csvFileList.forEach( list -> cricketMap.put( list.player, new CricketDAO( list ) ) );
            return cricketMap.size();
        } catch (RuntimeException e) {
            throw new CricketAnalyserException( e.getMessage(),
                    CricketAnalyserException.ExceptionType.WRONG_DATA );
        } catch (IOException e) {
            throw new CricketAnalyserException( e.getMessage(),
                    CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM );
        }
    }

    public String getFieldWiseData(String fieldType) throws CricketAnalyserException {
        if (cricketMap == null || cricketMap.size() == 0) {
            throw new CricketAnalyserException( "No Cricket Data",
                    CricketAnalyserException.ExceptionType.NO_CRICKET_DATA );
        }
        cricketList = new ArrayList( cricketMap.values() );
        Comparator<CricketDAO> cricketComparator = new CricketFactory().getCurrentSort( fieldType );
        List<CricketDAO> sortedList = cricketList.stream()
                .sorted( cricketComparator.reversed() )
                .collect( Collectors.toList() );
        return new Gson().toJson( sortedList );
    }
}