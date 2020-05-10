package com.bl.cricketanalyzer.service;

import com.bl.cricketanalyzer.dao.CricketDAO;
import com.bl.cricketanalyzer.exception.CricketAnalyserException;
import com.bl.cricketanalyzer.model.BatsMenCSV;
import com.csvbuilder.CSVBuilderFactory;
import com.csvbuilder.ICsvBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CricketAdapter {
    Map<String, CricketDAO> cricketMap = new HashMap<>();
    public abstract Map<String, CricketDAO> loadCricketData(String csvFilePath) throws CricketAnalyserException;

    public <E> Map<String, CricketDAO> loadCricketData(Class<E> cricketClass, String csvFilePath)
                                                        throws CricketAnalyserException {
        try {
            Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) );
            ICsvBuilder csvBuilderInterface = CSVBuilderFactory.createCSVBuilder();
            List csvFileList = csvBuilderInterface.getCSVFileList   ( reader, cricketClass );
            csvFileList.forEach( (cricketCSV) -> new CricketFactory().getCricketObject( cricketClass,
                                                                                cricketMap, cricketCSV ) );
            return cricketMap;
        } catch (IOException e) {
            throw new CricketAnalyserException( e.getMessage(),
                    CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM );
        }
    }
}
