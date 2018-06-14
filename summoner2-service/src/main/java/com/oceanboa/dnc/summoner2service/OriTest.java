
package com.oceanboa.dnc.summoner2service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

    public static String runCommand(String command){
        Runtime r = Runtime.getRuntime();
        Process p = null;
        StringBuffer sb = new StringBuffer();

        try {
            p = r.exec(command);
            p.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";


            while ((line = b.readLine()) != null) {
                System.out.println(line);
                sb.append(line);
            }

            b.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        Runtime r = Runtime.getRuntime();
        Process p = null;
        StringBuffer sb = new StringBuffer();

        try {
            p = r.exec("blender scene.blend --background --python blender_script.py -- 7 7 hey ho hey");
            p.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";


            while ((line = b.readLine()) != null) {
                System.out.println(line);
                sb.append(line);
            }

            b.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}