package edu.uci.swe264p.retrofit;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    List<Movie> movies;

    MovieListAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    // Declare ViewHolder class to display single movie with a view
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        TextView tvTitle;
        TextView tvReleaseDate;
        TextView tvVote;
        TextView tvOverview;

        ViewHolder(View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.ivMovie);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate);
            tvVote = itemView.findViewById(R.id.tvVote);
            tvOverview = itemView.findViewById(R.id.tvOverview);
        }
    }

    // Create a new ViewHolder with its corresponding layout
    @Override
    public MovieListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);
        return new MovieListAdapter.ViewHolder(view);
    }

    // Bind the info of single movie to a view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        // Load poster to imageView
        String POSTER_PATH = "https://image.tmdb.org/t/p/w500/" + movie.getPosterPath();
        Picasso.get().load(POSTER_PATH).into(holder.ivPoster);

        // Set all textView
        holder.tvTitle.setText(movie.getTitle());
        holder.tvReleaseDate.setText(movie.getReleaseDate());
        holder.tvVote.setText(movie.getVoteAverage().toString());
        holder.tvOverview.setText(movie.getOverview());
    }

    // Get the size of movies list
    @Override
    public int getItemCount() {
        return movies.size();
    }
}
