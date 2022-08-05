package com.venenium.StatisticsService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String start;
    private float capital;
    private float actual;
    private Boolean api = false;
    private String apikey = "";
    private String secret = "";
    private String username;

    public Statistics(String name, String start, float capital, float actual,boolean api, String apikey, String secret, String username) {
        this.name = name;
        this.start = start;
        this.capital = capital;
        this.actual = actual;
        this.api = api;
        this.apikey = apikey;
        this.secret = secret;
        this.username = username;
    }

    public Statistics(String name, String start, float capital, float actual, String username) {
        this.name = name;
        this.start = start;
        this.capital = capital;
        this.actual = actual;
        this.api = false;
        this.apikey = "";
        this.secret = "";
        this.username = username;
    }

}
