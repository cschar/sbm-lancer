
package com.oceanboa.dnc.summoner2service;

import java.util.Comparator;
import java.util.List;
        import java.util.stream.Collectors;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.league.*;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.core.summoner.Summoners;


public class OriTest {

    public OriTest(){};

    public static List<String> getTopSums(){
        Orianna.Configuration config = new Orianna.Configuration();
        Orianna.setRiotAPIKey(System.getenv("RIOT_API_KEY"));
        Orianna.setDefaultPlatform(Platform.NORTH_AMERICA);

        League challenger = League.challengerInQueue(Queue.RANKED_SOLO_5x5).withRegion(Region.NORTH_AMERICA).get();

        LeagueEntry highest = challenger.get(0);
        System.out.println(highest);
        List<LeagueEntry> sorted = challenger.stream()
                .sorted(Comparator.comparing(LeagueEntry::getLeaguePoints).reversed())
                .collect(Collectors.toList());

        highest = sorted.get(0);
        System.out.println(highest);

        for ( LeagueEntry le : challenger){
            if (le.getLeaguePoints() > highest.getLeaguePoints()) {
                highest = le;
            }
        }

        List<String> first5 = sorted
                .stream()
                .limit(5)
                .map( le ->{
                    System.out.println(le.getSummoner().getName());
                    return  le.getSummoner().getName();
                })
                .collect(Collectors.toList());

        return first5;
    }

    public static void main(String[] args) {

        Orianna.Configuration config = new Orianna.Configuration();
        Orianna.setRiotAPIKey(System.getenv("RIOT_API_KEY"));
        Orianna.setDefaultPlatform(Platform.NORTH_AMERICA);

        League challenger = League.challengerInQueue(Queue.RANKED_SOLO_5x5).withRegion(Region.NORTH_AMERICA).get();

        List<League> allChallenger = Leagues.challengerInQueues(Queue.RANKED).withRegion(Region.NORTH_AMERICA).get();

//        LeaguePositions positions = LeaguePositions.forSummoner(summoners.get(1)).get();
//        List<LeaguePositions> manyPositions = LeaguePositions.forSummoners(summoners).get();



        LeagueEntry highest = challenger.get(0);
        System.out.println(highest);
        List<LeagueEntry> sorted = challenger.stream()
                .sorted(Comparator.comparing(LeagueEntry::getLeaguePoints).reversed())
                .collect(Collectors.toList());

        highest = sorted.get(0);
        System.out.println(highest);

        for ( LeagueEntry le : challenger){
            if (le.getLeaguePoints() > highest.getLeaguePoints()) {
                highest = le;
            }
        }

        List first5 = sorted
                .stream()
                .limit(5)
                .map( le ->{
                    System.out.println(le.getSummoner().getName());
                    return  le.getSummoner().getName();
                })
                .collect(Collectors.toList());

        System.out.println(first5);

//        for ( LeagueEntry le : challenger) {
//            System.out.println(le.getSummoner().getName());
//        }


    }
}