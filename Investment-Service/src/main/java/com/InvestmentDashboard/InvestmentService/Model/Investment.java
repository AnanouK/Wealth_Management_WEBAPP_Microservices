package com.InvestmentDashboard.InvestmentService.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
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

public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String start;
    private float capital;
    private float actual;
    private float benefice;
    private Boolean api;
    private String apikey;
    private String secret;
    private String username;

    public Investment(String name, String start, float capital, float actual, float benefice,boolean api, String apikey, String secret, String username) {
        this.name = name;
        this.start = start;
        this.capital = capital;
        this.actual = actual;
        this.benefice = benefice;
        this.api = api;
        this.apikey = apikey;
        this.secret = secret;
        this.username = username;

    }


}
