package com.oceanboa.dnc.summoner2service.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SumBlip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String leaguePoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(String leaguePoints) {
        this.leaguePoints = leaguePoints;
    }
}