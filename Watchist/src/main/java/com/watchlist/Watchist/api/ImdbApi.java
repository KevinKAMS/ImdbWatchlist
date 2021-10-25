package com.watchlist.Watchist.api;

import java.util.ArrayList;
import java.util.List;

import com.watchlist.Watchist.model.Movie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.MovieDb;

@Component
public class ImdbApi {
    
    @Value("${app.imdbApiKey}")
    private String imdbApiKey;
    final static String language = "pt-BR";
    private static TmdbApi apiConnection;

    public TmdbApi connect() {
        if (apiConnection == null) {
            try {
                TmdbApi apiConnection = new TmdbApi(imdbApiKey);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return apiConnection;
    }

    public ArrayList<Movie> search(String movieTitle) {
        TmdbApi connection = this.connect();
        TmdbSearch search = connection.getSearch();
        List<MovieDb> searchList = search.searchMovie(movieTitle, null, language, false, null).getResults();
        ArrayList<Movie> movieList = new ArrayList<>();
        searchList.forEach((movie) -> {
            Movie newMovie = new Movie();
            newMovie = newMovie.saveMovieFromApi(movie);
            movieList.add(newMovie);
        });
        return movieList;
    }
}