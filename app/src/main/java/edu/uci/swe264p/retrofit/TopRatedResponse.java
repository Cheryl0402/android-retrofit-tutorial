package edu.uci.swe264p.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopRatedResponse {
    @SerializedName("results")
    private List<Movie> movieList;

    public TopRatedResponse(List<Movie> list) {
        movieList = list;
    }

    public List<Movie> getResult(){ return movieList;}
}
