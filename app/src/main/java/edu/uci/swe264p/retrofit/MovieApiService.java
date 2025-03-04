package edu.uci.swe264p.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("movie/{id}")
    Call<Movie> getMovie(@Path("id") int id, @Query("api_key") String apiKey);

    // Get top_rated movies
    @GET("movie/top_rated")
    Call<TopRatedResponse> getTopRatedResponse(@Query("api_key") String apiKey);

}
