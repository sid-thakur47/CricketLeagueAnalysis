package com.bl.cricketanalyzer.service;

import com.bl.cricketanalyzer.constants.Contants;
import com.bl.cricketanalyzer.dao.CricketDAO;
import com.bl.cricketanalyzer.exception.CricketAnalyserException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CricketFactory implements Contants {

    public static Map<String, CricketDAO> getCricketData(Contants.Player player,
                                                         String csvFilePath) throws CricketAnalyserException {
        if (player.equals( player.BATSMEN )) {
            return new BatsmenAdapter().loadCricketData( csvFilePath );
        } else if (player.equals( player.BOWLER )) {
            return new BowlerAdapter().loadCricketData( csvFilePath );
        } else {
            throw new CricketAnalyserException( "Incorrect Player",
                    CricketAnalyserException.ExceptionType.WRONG_CLASS );
        }
    }
    public static List<CricketDAO> getFilteredData(List<CricketDAO> cricketList, String fieldType) {
        switch (fieldType) {
            case AVERAGE:
                cricketList = cricketList.stream()
                        .filter( cricket -> cricket.getAverage() > 0 )
                        .collect( Collectors.toList() );
                break;

            case STRIKE_RATE:
                cricketList = cricketList.stream()
                        .filter( cricket -> cricket.getStrikeRate() > 0 )
                        .collect( Collectors.toList() );
                break;

            case STRIKE_AND_SIX_FOUR:
                cricketList = cricketList.stream()
                        .filter( cricket -> cricket.getRuns() > 100 )
                        .collect( Collectors.toList() );
                break;

            case AVG_AND_STRIKE_RATE:
                cricketList = cricketList.stream()
                        .filter( cricket -> cricket.getStrikeRate() > 140 )
                        .collect( Collectors.toList() );
                break;

            case STRIKE_AND_WICKETS:
                cricketList = cricketList.stream()
                        .filter( cricket -> cricket.getSumWickets()
                                > 0 && cricket.getEconomy() < 10 )
                        .collect( Collectors.toList() );
                break;

            case WICKET_AVERAGE:
                cricketList = cricketList.stream()
                        .filter( cricket -> cricket.getMatches() > 15 )
                        .collect( Collectors.toList() );
                break;

            case BOWL_AVG_AND_STRIKE_RATE:
                cricketList = cricketList.stream()
                        .filter( cricket -> cricket.getAverage()
                                > 0 && cricket.getMatches() > 10 )
                        .collect( Collectors.toList() );
                break;
        }
        return cricketList;
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
    public CricketDAO generateCricketDAO(CricketDAO batsMen, CricketDAO bowler, String field) {
        if (field.equals( BATSMEN_BOWLER_AVERAGE )) {
            double batsmenAvg = batsMen.average;
            if (batsmenAvg != 0 && bowler != null) {
                return new CricketDAO( batsMen, bowler );
            }
        }
        if (field.equals( ALL_ROUNDER )) {
            double batsmenRuns = batsMen.runs;
            if (batsmenRuns != 0 && bowler != null) {
                return new CricketDAO( batsMen, bowler );
            }
        }
        return null;
    }
    public  Comparator<CricketDAO> getCurrentSort(String field) {

        Comparator<CricketDAO> comparator = null;
        switch (field) {

            case STRIKE_RATE:
                comparator = Comparator.comparing( cricket ->
                        cricket.strikeRate );
                break;

            case AVERAGE:
                comparator = Comparator.comparing( cricket ->
                        cricket.average );
                break;

            case SIX_FOUR:
                comparator = Comparator.comparing( cricket ->
                        cricket.sumSixFour );
                break;

            case ECONOMY:
                comparator = Comparator.comparing( cricket ->
                        cricket.economy );
                break;

            case STRIKE_AND_SIX_FOUR:
                comparator = Comparator.comparing( CricketDAO::getStrikeRate )
                        .thenComparing( CricketDAO::getSumSixFour );
                break;

            case BOWL_AVG_AND_STRIKE_RATE:
            case AVG_AND_STRIKE_RATE:
                comparator = Comparator.comparing( CricketDAO::getAverage )
                        .thenComparing( CricketDAO::getStrikeRate );
                break;

            case RUNS_AND_AVG:
                comparator = Comparator.comparing( CricketDAO::getRuns )
                        .thenComparing( CricketDAO::getAverage );
                break;

            case STRIKE_AND_WICKETS:
                comparator = Comparator.comparing( CricketDAO::getStrikeRate )
                        .thenComparing( CricketDAO::getSumWickets );
                break;
            case WICKET_AVERAGE:
                comparator = Comparator.comparing( CricketDAO::getSumWickets )
                        .thenComparing( CricketDAO::getAverage );
                break;

            case BATSMEN_BOWLER_AVERAGE:
                comparator = Comparator.comparing( CricketDAO::getBatsmenAvg )
                        .thenComparing( CricketDAO::getBowlerAvg );
                break;

            case ALL_ROUNDER:
                comparator = Comparator.comparing( CricketDAO::getRuns )
                        .thenComparing( CricketDAO::getSumWickets );
                break;
        }
        return comparator;
    }
}
