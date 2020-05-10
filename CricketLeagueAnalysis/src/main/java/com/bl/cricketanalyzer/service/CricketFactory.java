package com.bl.cricketanalyzer.service;

import com.bl.cricketanalyzer.constants.FilePaths;
import com.bl.cricketanalyzer.dao.CricketDAO;
import com.bl.cricketanalyzer.exception.CricketAnalyserException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Map;

public class CricketFactory {

    public static Map<String, CricketDAO> getCricketData(FilePaths.Player player,
                                                         String csvFilePath) throws CricketAnalyserException {
        if (player.equals( player.BATSMEN )) {
            return new BatsmenAdapter().loadCricketData( csvFilePath );
        } else if (player.equals( player.BOWLER )) {
            return new BowlerAdapter().loadCricketData( csvFilePath );
        } else {
            throw new CricketAnalyserException( "Incorrect Player", CricketAnalyserException.ExceptionType.WRONG_CLASS );
        }
    }
    public Comparator<CricketDAO> getCurrentSort(String field) {

        Comparator<CricketDAO> comparator = null;
        switch (field) {
            case "strikerate":
                comparator = Comparator.comparing( cricket -> cricket.strikeRate );
                break;

            case "average":
                comparator = Comparator.comparing( cricket -> cricket.average );
                break;

            case "sixfour":
                comparator = Comparator.comparing( cricket -> cricket.sumSixFour );
                break;

            case "strikeandsixfour":
                comparator = Comparator.comparing( CricketDAO::getStrikeRate )
                                        .thenComparing( CricketDAO::getSumSixFour );
                break;

            case "avgandstrike":
                comparator = Comparator.comparing( CricketDAO::getAverage )
                                        .thenComparing( CricketDAO::getStrikeRate );
                break;

            case "runsandavg":
                comparator = Comparator.comparing( CricketDAO::getRuns )
                                        .thenComparing( CricketDAO::getAverage );
        }
        return comparator;
    }

    public <E> void getCricketObject(Class<E> cricketClass, Map cricketMap, Object cricketCSV) {
        try {
            Class<?> cricketDAO = Class.forName( "com.bl.cricketanalyzer.dao.CricketDAO" );
            Constructor<?> cricketConstructor = cricketDAO.getConstructor( cricketClass );
            Field field = cricketClass.getDeclaredField( "player" );
            String value = (String) field.get( cricketCSV );
            cricketMap.put( value, cricketConstructor.newInstance( cricketCSV ) );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
