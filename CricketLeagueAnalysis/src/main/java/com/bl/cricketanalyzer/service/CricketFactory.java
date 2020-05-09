package com.bl.cricketanalyzer.service;

import com.bl.cricketanalyzer.dao.CricketDAO;

import java.util.Comparator;

public class CricketFactory {
    public Comparator<CricketDAO> getCurrentSort(String field) {

        Comparator<CricketDAO> comparator = null;
        switch (field) {
            case "strikerate":
                comparator = Comparator.comparing( cricket -> cricket.strikeRate );
                break;
            case "sixes":
                comparator = Comparator.comparing( cricket -> cricket.sixes );
                break;
            case "average":
                comparator = Comparator.comparing( CricketDAO::getAverage );
                break;
            case "player":
                comparator = Comparator.comparing( cricket -> cricket.player );
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
}
