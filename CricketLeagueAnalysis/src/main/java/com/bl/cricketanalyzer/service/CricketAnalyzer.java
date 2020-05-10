package com.bl.cricketanalyzer.service;

import com.bl.cricketanalyzer.constants.FilePaths;
import com.bl.cricketanalyzer.dao.CricketDAO;
import com.bl.cricketanalyzer.exception.CricketAnalyserException;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CricketAnalyzer implements FilePaths {
    List<CricketDAO> cricketList;
    Map<String, CricketDAO> cricketMap;

    public CricketAnalyzer() {
    }
    public int loadCricketData(Player player, String csvFilePath) throws CricketAnalyserException {
        cricketMap = CricketFactory.getCricketData( player, csvFilePath );
        return cricketMap.size();
    }

    public String getFieldWiseData(String fieldType) throws CricketAnalyserException {
        if (cricketMap == null || cricketMap.size() == 0) {
            throw new CricketAnalyserException( "No Cricket Data",
                    CricketAnalyserException.ExceptionType.NO_CRICKET_DATA );
        }
        cricketList = new ArrayList( cricketMap.values() );
        return sortDataJSONFormat( fieldType );
    }

    public String mergeBatsMenBowlerData(String batsMenPath, String bowlerPath, String field)
                                                                            throws CricketAnalyserException {
        Map<String, CricketDAO> batsMenMap =    CricketFactory.getCricketData( Player.BATSMEN, batsMenPath );
        Map<String, CricketDAO> bowlerMenMap = CricketFactory.getCricketData( Player.BOWLER, bowlerPath );
        cricketList = new ArrayList<>();
        batsMenMap.values().stream().forEach( (batsMen) -> {
            CricketDAO bowler = bowlerMenMap.get( batsMen.player );
            CricketDAO dao = new CricketFactory().generateCricketDAO( batsMen, bowler, field );
            if (dao != null) {
                cricketList.add( dao );
            }
        } );
        return sortDataJSONFormat( field );
    }

    private String sortDataJSONFormat(String fieldType) {
        Comparator<CricketDAO> cricketComparator = new CricketFactory().getCurrentSort( fieldType );
        List<CricketDAO> sortedList = cricketList.stream()
                .sorted( cricketComparator.reversed() )
                .collect( Collectors.toList() );
        return new Gson().toJson( sortedList );
    }
}