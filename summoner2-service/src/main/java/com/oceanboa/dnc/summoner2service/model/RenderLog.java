package com.oceanboa.dnc.summoner2service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class RenderLog {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    public String getAnimType() {
        return animType;
    }

    public void setAnimType(String animType) {
        this.animType = animType;
    }

    private String animType;
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String lastName;

    protected RenderLog() {}

    public RenderLog(String firstName, String lastName, String animType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.animType = animType;
    }

    public RenderLog(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

//    @Override
//    public String toString() {
//        return String.format(
//                "Customer[id=%d, firstName='%s', lastName='%s']",
//                id, firstName, lastName);
//    }

}