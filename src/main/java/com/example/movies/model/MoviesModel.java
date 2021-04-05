package com.example.movies.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "movies")
public class MoviesModel {

    @Id @GeneratedValue
    private Integer id;

    public MoviesModel(Integer id, Integer year, String title, String producer, String winner, String studios) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.producer = producer;
        this.winner = winner;
        this.studios = studios;
    }

    public MoviesModel() { }

    @Column(nullable = false, length = 4 )
    private Integer year;

    @Column(nullable = false, length = 150 )
    private String title;

    @Column(nullable = false, length = 150 )
    private String producer;

    @Column(nullable = false, length = 150 )
    private String winner;

    @Column(nullable = false, length = 150 )
    private String studios;

    public String getStudios() {
        return studios;
    }

    public void setStudios(String studios) {
        this.studios = studios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
