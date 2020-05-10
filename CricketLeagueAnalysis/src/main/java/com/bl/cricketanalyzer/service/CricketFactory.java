package com.bl.cricketanalyzer.service;

import com.bl.cricketanalyzer.constants.FilePaths;
import com.bl.cricketanalyzer.dao.CricketDAO;
import com.bl.cricketanalyzer.exception.CricketAnalyserException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Map;

public class CricketFactory implements  FilePaths{

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
            case STRIKE_RATE:
                comparator = Comparator.comparing( cricket -> cricket.strikeRate );
                break;

            case AVERAGE:
                comparator = Comparator.comparing( cricket -> cricket.average );
                break;

            case SIX_FOUR:
                comparator = Comparator.comparing( cricket -> cricket.sumSixFour );
                break;

            case STRIKE_AND_SIX_FOUR:
                comparator = Comparator.comparing( CricketDAO::getStrikeRate )
                                        .thenComparing( CricketDAO::getSumSixFour );
                break;

            case AVG_AND_STRIKE_RATE:
                comparator = Comparator.comparing( CricketDAO::getAverage )
                                        .thenComparing( CricketDAO::getStrikeRate );
                break;

            case RUNS_AND_AVG:
                 comparator = Comparator.comparing( CricketDAO::getRuns )
                                        .thenComparing( CricketDAO::getAverage );
                 break;
            case ECONOMY:
                comparator = Comparator.comparing( cricket -> cricket.economy );
                break;
            case STRIKE_AND_WICKETS:
                comparator = Comparator.comparing( CricketDAO::getStrikeRate )
                                        .thenComparing( CricketDAO::getSumWickets );
                break;
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
