package edu.uci.swe264p.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class MovieListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    static final String TAG = MovieListActivity.class.getSimpleName();
    private String BASE_URL = "https://api.themoviedb.org/3/";
    static Retrofit retrofit = null;
    final static String API_KEY = "dcdcde5d90ff7ac28e020269684102c5";
    List<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        connect();
        setRecyclerView();
    }

    private void connect(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<TopRatedResponse> call2 = movieApiService.getTopRatedResponse(API_KEY);
        call2.enqueue(new Callback<TopRatedResponse>() {
            @Override
            public void onResponse(Call<TopRatedResponse> call2, Response<TopRatedResponse> response) {
                for (int i = 0; i < 20; i++) {
                    Float voteAverage = response.body().getResult().get(i).getVoteAverage();
                    String posterPath = response.body().getResult().get(i).getPosterPath();
                    String title = response.body().getResult().get(i).getTitle();
                    String releaseDate = response.body().getResult().get(i).getReleaseDate().toString();
                    String overView = response.body().getResult().get(i).getOverview();
                    movieList.add(new Movie(voteAverage, posterPath, title, releaseDate, overView));
                }
                setRecyclerView();
            }

            @Override
            public void onFailure(Call<TopRatedResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }



    private void setRecyclerView(){
        recyclerView = findViewById(R.id.rvMovieList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MovieListAdapter(movieList));
    }
}
