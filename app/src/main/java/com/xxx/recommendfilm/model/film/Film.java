package com.xxx.recommendfilm.model.film;

import java.util.List;

public class Film {
    private long mid;
    private String name;
    private String director;
    private String scriptWriter;
    private String starring;
    private String releaseTime;
    private int duration;
    private String introduction;
    private String poster;
    private List<String> classifyList;
    private List<FilmComment> movieReviewDetailRspList;

    public Film(long mid, String name, String director, String scriptWriter, String starring, String releaseTime, int duration, String introduction, String poster, List<String> classifyList, List<FilmComment> movieReviewDetailRspList) {
        this.mid = mid;
        this.name = name;
        this.director = director;
        this.scriptWriter = scriptWriter;
        this.starring = starring;
        this.releaseTime = releaseTime;
        this.duration = duration;
        this.introduction = introduction;
        this.poster = poster;
        this.classifyList = classifyList;
        this.movieReviewDetailRspList = movieReviewDetailRspList;
    }

    public List<FilmComment> getMovieReviewDetailRspList() {
        return movieReviewDetailRspList;
    }

    public void setMovieReviewDetailRspList(List<FilmComment> movieReviewDetailRspList) {
        this.movieReviewDetailRspList = movieReviewDetailRspList;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }
    public long getMid() {
        return mid;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setDirector(String director) {
        this.director = director;
    }
    public String getDirector() {
        return director;
    }

    public void setScriptWriter(String scriptWriter) {
        this.scriptWriter = scriptWriter;
    }
    public String getScriptWriter() {
        return scriptWriter;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }
    public String getStarring() {
        return starring;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }
    public String getReleaseTime() {
        return releaseTime;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getDuration() {
        return duration;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public String getIntroduction() {
        return introduction;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
    public String getPoster() {
        return poster;
    }

    public void setClassifyList(List<String> classifyList) {
        this.classifyList = classifyList;
    }
    public List<String> getClassifyList() {
        return classifyList;
    }
}
