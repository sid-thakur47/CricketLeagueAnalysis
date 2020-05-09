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
                comparator = Comparator.comparing( (cricket) -> Double.parseDouble( cricket.average
                        .replace( "-", "0" ) ) );
                break;
            case "player":
                comparator = Comparator.comparing( Cricket -> Cricket.player );
                break;

            case "sixfour":
                comparator = Comparator.comparing( cricket -> cricket.sumSixFour );
                break;
        }
        return comparator;
    }
}
