package com.watchlist.Watchist.api;

import java.util.ArrayList;
import java.util.List;

import com.watchlist.Watchist.model.Movie;

import org.springframework.stereotype.Component;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.MovieDb;

@Component
public class ImdbApi {

    private String imdbApiKey = "676c10a663703153a015781707d8b271";

    public TmdbApi connect() {
        TmdbApi apiConnection = new TmdbApi(imdbApiKey);
        return apiConnection;
    }

    public ArrayList<Movie> search(String movieTitle) {
        TmdbApi connection = this.connect();
        TmdbSearch search = connection.getSearch();
        List<MovieDb> searchList = search.searchMovie(movieTitle, null, "pt-BR", false, null).getResults();
        ArrayList<Movie> movieList = new ArrayList<>();
        searchList.forEach((movie) -> {
            Movie newMovie = new Movie();
            newMovie = newMovie.saveMovieFromApi(movie);
            movieList.add(newMovie);
        });
        return movieList;
    }
}